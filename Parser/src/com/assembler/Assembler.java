package com.assembler;

import java.util.ArrayList;
import java.util.HashMap;

import com.assembler.instructions.PushI;

public class Assembler {

	public static final String ALL = "all";
	public static final String PUSHI = "pushi";

	private static int counter;
	protected static ArrayList<Instruction> instructions = new ArrayList<>();
	private static ArrayList<String> loadedInstrucitons = new ArrayList<>();

	public static void parseInstruction(String[] s) {
		if (!isInstruction(s[0])) {
			if (!s[0].endsWith(":")) {
				System.out.println("Invalid Instruction, Exiting!");
				System.exit(0);
			}
			if (!isInstruction(s[1])) {
				System.out.println("Invalid Instruction After Label, Exiting!");
				System.exit(0);
			}
			addInst(s, 2);
		} else {
			addInst(s, 1);
		}
		counter++;
	}

	private static void addInst(String[] s, int offset) {
		String[] inst = new String[s.length - offset];
		for (int i = offset; i < s.length; i++) {
			inst[i - offset] = s[i];
		}
		if (offset==2)
			instructions.add(Instruction.createInst(s[offset - 1]).flagLabel(s[0].substring(0, s[0].length()-1)));
		else 
			instructions.add(Instruction.createInst(s[offset - 1]));
		
	}

	private static boolean isInstruction(String instruction) {
		return loadedInstrucitons.contains(instruction);
	}

	public static void useInstruction(final String i) {
		if (i.equals(ALL)) {
			loadedInstrucitons.add(PUSHI);
		} else {
			loadedInstrucitons.add(i);
		}
	}
	
	public static void linkLabels(){
		
	}
}
