package com.assembler.instructions;

import com.assembler.Instruction;
import com.assembler.State;

public class Push extends Instruction {

	public Push() {
		super("push", "0010");
	}

	@Override
	public Instruction onParse(String[] s) {
		return this;
	}
	
	@Override
	public void runProcedure() {
	}

}
