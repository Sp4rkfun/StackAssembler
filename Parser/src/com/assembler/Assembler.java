package com.assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.assembler.instructions.label.LabelInst;

import static com.assembler.State.*;
public class Assembler {

	public static final String ALL = "all";
	public static final String ADD = "add";
	public static final String J = "j";
	public static final String JAL = "jal";
	public static final String BEQ = "beq";
	public static final String PUSHI = "pushi";
	public static final String STACKSIZE = "Stacksize";
	public static final String DUMPSTACK = "Dumpstack";
	public static final String STACKCONTAINS = "Stackcontains";
	private static ArrayList<String> loadedInstrucitons = new ArrayList<>();

	public static void parseInstruction(String[] s) {
		if (!isInstruction(s[0])) {
			if (!s[0].endsWith(":")) {
				System.err.println("Invalid Instruction: "+Arrays.toString(s)+", Exiting!");
				System.exit(0);
			}
			if (!isInstruction(s[1])) {
				System.err.println("Invalid Instruction: "+Arrays.toString(s)+" After Label: "+s[0]+", Exiting!");
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
			instructions.add(Instruction.createInst(s[offset - 1]).onParse(inst).flagLabel(
					s[0].substring(0, s[0].length() - 1)));
		else
			instructions.add(Instruction.createInst(s[offset - 1]).onParse(inst));

	}

	private static boolean isInstruction(String instruction) {
		return loadedInstrucitons.contains(instruction);
	}

	public static void useInstruction(final String i) {
		if (i.equals(ALL)) {
			loadedInstrucitons.add(PUSHI);
			loadedInstrucitons.add(JAL);
			loadedInstrucitons.add(J);
			loadedInstrucitons.add(ADD);
			loadedInstrucitons.add(BEQ);
			
			loadedInstrucitons.add(STACKSIZE);
			loadedInstrucitons.add(DUMPSTACK);
			loadedInstrucitons.add(STACKCONTAINS);
			
		} else {
			loadedInstrucitons.add(i);
		}
	}

	public static void linkLabels(){
		HashMap<String, Integer> labels = new HashMap<>();
		ArrayList<LabelInst> links = new ArrayList<>();
		int count = instructions.size();
		for (int i = 0; i < count; i++) {
			Instruction in = instructions.get(i);
			in.count = i;
			if(in instanceof LabelInst&&((LabelInst) in).needsLink)
				links.add((LabelInst) in);
			if(in.hasLabel){
				if(labels.containsKey(in.label)){
						System.err.println("Duplicate Label, Exiting!");
						System.exit(0);				
				}			
				labels.put(in.label, i);
			}
		}
		terminate=count;
		
		for(LabelInst inst: links){
			if(!labels.containsKey(inst.linkLabel)){
				System.err.println("No Label Found For: "+inst.linkLabel+", Exiting!");
				System.exit(0);		
			}
			int i = labels.get(inst.linkLabel);
			inst.parseLabel(i);
			Echo.print("Linked label: "+inst.linkLabel);
		}
	}

	public static void run() {
		while(current<terminate){
			instructions.get(current).runProcedure();
		}
		System.out.println("Exiting");
	}
}
