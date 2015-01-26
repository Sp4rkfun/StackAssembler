package com.assembler;

import com.assembler.instructions.PushI;

public abstract class Instruction {
	public String name;
	public String opcode;
	public boolean hasLabel;
	public String label;

	public Instruction(String name, String opcode){
		this.name=name;
		this.opcode=opcode;
	}
	
	public abstract void runProcedure(String[] s);
	
	public static Instruction createInst(String s){
		switch (s) {
		case Assembler.PUSHI:
			return new PushI();
		default:
			return null;
		}
	}
	
	public Instruction flagLabel(String name){
		hasLabel=true;
		label=name;
		return this;
	}
	
}
