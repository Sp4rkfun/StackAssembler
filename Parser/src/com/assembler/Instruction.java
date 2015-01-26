package com.assembler;

public abstract class Instruction {
	public String name;
	public String opcode;
	public Instruction(String name, String opcode){
		Assembler.instructions.put(name, this);
		this.name=name;
		this.opcode=opcode;
	}
	
	public abstract void runProcedure(String[] s);
}
