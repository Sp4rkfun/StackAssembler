package com.assembler.instructions;

import com.assembler.Instruction;

public abstract class JumpInst extends Instruction {
	public String linkLabel;
	public boolean needsLink=true;
	public JumpInst(String name, String opcode) {
		super(name, opcode);
	}
	
	@Override
	public Instruction onParse(String[] s) {
		linkLabel = s[0];
		return super.onParse(s);
	}
	
	//TODO: Do address Computation
	public void parseLabel(int location){
		
	}
}
