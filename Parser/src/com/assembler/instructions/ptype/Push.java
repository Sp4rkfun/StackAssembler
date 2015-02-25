package com.assembler.instructions.ptype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Push extends Instruction {
	String reg;
	public Push() {
		super("push", "0010");
	}

	@Override
	public Instruction onParse(String[] s) {
		reg=s[0];
		machineValue+=State.regNum(s[0])+"000000000";
		return this;
	}
	
	@Override
	public void runProcedure() {
		Echo.print("Pushed "+State.regContents(reg)+" From Register "+reg);
		State.stack.push(State.regContents(reg));
		super.runProcedure();
	}

}
