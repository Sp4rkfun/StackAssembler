package com.assembler;

public abstract class Instruction {
	public String name;
	public Instruction(String name){
		Assembler.instructions.put(name, this);
		this.name=name;
	}
	
	public abstract void runProcedure(String[] s);
}
