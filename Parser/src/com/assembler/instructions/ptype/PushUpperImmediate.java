package com.assembler.instructions.ptype;

import com.assembler.Assembler;
import com.assembler.Instruction;
import com.assembler.State;

public class PushUpperImmediate extends Instruction {
	int value;
	public PushUpperImmediate() {
		super("pui", "0001");
	}
	@Override
	public Instruction onParse(String[] s) {
		machineValue+=Assembler.toBinary(Integer.parseInt(s[0]),12);
		value=Integer.parseInt(s[0])<<8;
		return super.onParse(s);
	}
	@Override
	public void runProcedure() {
		State.stack.push(value);
	}

}
