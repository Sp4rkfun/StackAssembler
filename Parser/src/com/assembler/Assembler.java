package com.assembler;

import static com.assembler.State.current;
import static com.assembler.State.instructions;
import static com.assembler.State.stack;
import static com.assembler.State.terminate;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.assembler.instructions.label.LabelInst;

public class Assembler {

	public static final String ALL = "all";
	public static final String ADD = "add";
	public static final String AND = "and";
	public static final String OR = "or";
	public static final String NEG = "neg";
	public static final String SLT = "slt";
	public static final String SLL = "sll";
	public static final String J = "j";
	public static final String JAL = "jal";
	public static final String BEQ = "beq";
	public static final String PUSH = "push";
	public static final String POP = "pop";
	public static final String PUSHI = "pushi";
	public static final String PEEK = "peek";

	public static final String JR = "jr";

	public static final String STACKSIZE = "Stacksize";
	public static final String DUMPSTACK = "Dumpstack";
	public static final String STACKCONTAINS = "Stackcontains";
	private static ArrayList<String> loadedInstrucitons = new ArrayList<>();
	public static HashMap<String, Integer> globals = new HashMap<>();
	public static int globalPointer = 0;
	public static boolean toMachine = false;

	public static void parseInstruction(String[] s) {
		if (s[0].equals(".globl")) {
			String[] dst = s[1].split("\\[");
			dst[1] = dst[1].substring(0, dst[1].length() - 1);
			globals.put(dst[0], globalPointer);
			globalPointer += Integer.parseInt(dst[1]);
			if (toMachine) {
				for (int i = 0; i < Integer.parseInt(dst[1]); i++) {
					parseInstruction(new String[] { "pushi", s[3 + i] });
				}
			} else {
				for (int i = 0; i < Integer.parseInt(dst[1]); i++) {
					stack.push(Integer.parseInt(s[3 + i]));
				}
			}
		} else if (!isInstruction(s[0])) {
			if (!s[0].endsWith(":")) {
				System.err.println("Invalid Instruction: " + Arrays.toString(s)
						+ ", Skipping!");
				return;
			}
			if (!isInstruction(s[1])) {
				System.err.println("Invalid Instruction: " + Arrays.toString(s)
						+ " After Label: " + s[0] + ", Exiting!");
				System.exit(0);
			}
			addInst(s, 2);
		} else {
			addInst(s, 1);
		}
	}

	private static void addInst(String[] s, int offset) {
		String[] inst = new String[s.length - offset];
		for (int i = offset; i < s.length; i++) {
			inst[i - offset] = s[i];
		}
		if (offset == 2)
			instructions.add(Instruction.createInst(s[offset - 1])
					.onParse(inst)
					.flagLabel(s[0].substring(0, s[0].length() - 1)));
		else
			instructions.add(Instruction.createInst(s[offset - 1])
					.onParse(inst));

	}

	private static boolean isInstruction(String instruction) {
		return loadedInstrucitons.contains(instruction);
	}

	public static void useInstruction(final String i) {
		if (i.equals(ALL)) {
			loadedInstrucitons.add(ADD);
			loadedInstrucitons.add(AND);
			loadedInstrucitons.add(OR);
			loadedInstrucitons.add(NEG);
			loadedInstrucitons.add(SLT);
			loadedInstrucitons.add(SLL);
			loadedInstrucitons.add(PUSH);
			loadedInstrucitons.add(PUSHI);
			loadedInstrucitons.add(POP);
			loadedInstrucitons.add(JAL);
			loadedInstrucitons.add(J);
			loadedInstrucitons.add(ADD);
			loadedInstrucitons.add(BEQ);
			loadedInstrucitons.add(PEEK);
			loadedInstrucitons.add(JR);
			if (!toMachine) {
				loadedInstrucitons.add(STACKSIZE);
				loadedInstrucitons.add(DUMPSTACK);
				loadedInstrucitons.add(STACKCONTAINS);
			}

		} else {
			loadedInstrucitons.add(i);
		}
	}

	public static void linkLabels() {
		HashMap<String, Integer> labels = new HashMap<>();
		ArrayList<LabelInst> links = new ArrayList<>();
		int count = instructions.size();
		for (int i = 0; i < count; i++) {
			Instruction in = instructions.get(i);
			in.count = i;
			if (in instanceof LabelInst && ((LabelInst) in).needsLink)
				links.add((LabelInst) in);
			if (in.hasLabel) {
				if (labels.containsKey(in.label)) {
					System.err.println("Duplicate Label, Exiting!");
					System.exit(0);
				}
				labels.put(in.label, i*2);
			}
		}
		terminate = count;

		for (LabelInst inst : links) {
			if (!labels.containsKey(inst.linkLabel)) {
				System.err.println("No Label Found For: " + inst.linkLabel
						+ ", Exiting!");
				System.exit(0);
			}
			int i = labels.get(inst.linkLabel);
			inst.parseLabel(i);
			Echo.print("Linked label: " + inst.linkLabel);
		}
	}

	public static void run() {
		State.stack.pointer = globalPointer;
		if (toMachine) {
			try {
				PrintWriter writer = new PrintWriter("asm/out.mc", "UTF-8");
				writer.println("MEMORY_INITIALIZATION_RADIX=16;");
				writer.println("MEMORY_INITIALIZATION_VECTOR=");
				for (int i = 0; i < terminate - 1; i++) {
					writer.println(toHex(instructions.get(i).machineValue)
							+ ",");
				}
				writer.println(toHex(instructions.get(terminate - 1).machineValue)
						+ ";");
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			while (current < terminate) {
				/*
				 * try { System.in.read(); } catch (IOException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); }
				 */
				instructions.get(current).runProcedure();
			}
		System.out.println("Exiting");
	}

	public static String toBinary(int n, int places) {
		String binary = "";
		if (n != 0) {
			while (n > 0) {
				places--;
				int rem = n % 2;
				binary = rem + binary;
				n = n / 2;
			}
		}
		for (int i = 0; i < places; i++) {
			binary = "0" + binary;
		}
		return binary;
	}

	public static String toHex(String binary) {
		String s = "";
		for (int i = 0; i < binary.length(); i += 4) {

			switch (binary.substring(i, i + 4)) {
			case "0000":
				s += 0;
				break;
			case "0001":
				s += 1;
				break;
			case "0010":
				s += 2;
				break;
			case "0011":
				s += 3;
				break;
			case "0100":
				s += 4;
				break;
			case "0101":
				s += 5;
				break;
			case "0110":
				s += 6;
				break;
			case "0111":
				s += 7;
				break;
			case "1000":
				s += 8;
				break;
			case "1001":
				s += 9;
				break;
			case "1010":
				s += "a";
				break;
			case "1011":
				s += "b";
				break;
			case "1100":
				s += "c";
				break;
			case "1101":
				s += "d";
				break;
			case "1110":
				s += "e";
				break;
			case "1111":
				s += "f";
				break;
			}
		}
		return s;
	}
}
