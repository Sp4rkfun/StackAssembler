package com.assembler.instructions.ptype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Pop extends Instruction {
	String reg;

	public Pop() {
		super("pop", "0011");
	}

	@Override
	public Instruction onParse(String[] s) {
		reg = s[0];
		return super.onParse(s);
	}

	@Override
	public void runProcedure() {
		int value = State.stack.pop();
		Echo.print("Popped "+value+" To Register "+reg);
		State.updateRegister(reg, value);
		super.runProcedure();
	}

}
