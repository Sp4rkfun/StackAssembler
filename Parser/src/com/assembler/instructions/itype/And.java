package com.assembler.instructions.itype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class And extends Instruction {

	public And() {
		super("and", "1101000000000000");
	}

	@Override
	public void runProcedure() {
		int a = State.stack.pop();
		int b = State.stack.pop();
		State.stack.push(a&b);
		Echo.print("Performing And On Stack: "+a+"&"+b+"="+(a&b));
		super.runProcedure();
	}

}
