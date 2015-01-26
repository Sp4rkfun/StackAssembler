package com.assembler;

import java.util.ArrayList;
import java.util.HashMap;

import com.assembler.instructions.PushI;

public class Assembler {
	public static final String ALL = "all";
	public static final String PUSHI = "pushi";
	
	private static int counter;
	protected static HashMap<String, Instruction> instructions = new HashMap<>();
	private static ArrayList<String> loadedInstrucitons = new ArrayList<>();
	static {
		new PushI();
	}

	public static void parseInstruction(String[] s) {
		if (!isInstruction(s[0])) {
			if(!s[0].endsWith(":")){
				System.out.println("Invalid Instruction, Exiting!");
				System.exit(0);
			}
			if (!isInstruction(s[1])) {
				System.out.println("Invalid Instruction After Label, Exiting!");
				System.exit(0);
			}
			runProc(s, 2);	
		} else {
			runProc(s, 1);
		}
		counter++;
	}
	
	private static void runProc(String[] s, int offset){
		String[] inst = new String[s.length-offset];
		for (int i = offset; i < s.length; i++) {
			inst[i-offset]=s[i];
		}
		instructions.get(s[offset-1]).runProcedure(inst);
	}

	private static boolean isInstruction(String instruction) {
		return loadedInstrucitons.contains(instruction);
	}

	public static void useInstruction(final String i) {
		if (i.equals(ALL)) {
			for(String s: instructions.keySet()){
				loadedInstrucitons.add(s);
			}
		} else {
			loadedInstrucitons.add(i);
		}
	}
}
