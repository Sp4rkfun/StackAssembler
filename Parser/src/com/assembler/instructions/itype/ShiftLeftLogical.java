package com.assembler.instructions.itype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class ShiftLeftLogical extends Instruction {
	int value=0;
	public ShiftLeftLogical() {
		super("sll", "1001000000000000");
	}
	@Override
	public Instruction onParse(String[] s) {
		value=Integer.parseInt(s[0]);
		return super.onParse(s);
	}
	@Override
	public void runProcedure() {
		int a = State.stack.pop();
		State.stack.push(a<<value);
		Echo.print("Performing Left Shift On Stack: "+a+"<<"+value+"="+(a<<value));
		super.runProcedure();
	}

}
