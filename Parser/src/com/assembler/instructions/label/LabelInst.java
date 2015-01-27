package com.assembler.instructions.label;

import com.assembler.Instruction;

public abstract class LabelInst extends Instruction {
	public String linkLabel;
	public boolean needsLink=true;
	int value;
	public LabelInst(String name, String opcode) {
		super(name, opcode);
	}
	
	@Override
	public Instruction onParse(String[] s) {
		linkLabel = s[0];
		return super.onParse(s);
	}
	
	//TODO: Do address Computation
	public void parseLabel(int location){
		value=location;
	}
}
