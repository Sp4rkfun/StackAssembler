package com.assembler.instructions.assertions;

import com.assembler.Echo;
import com.assembler.State;

public class DumpStack extends Assertion {

	public DumpStack() {
		super("Dumpstack", "");
	}
	@Override
	public void runProcedure() {
		Echo.printD("Displaying Stack Contents:");
		for (int i = 0; i < State.stack.stackPointer(); i++) {
			Echo.printD(i+"  "+State.stack.stack.get(i));
		}
		super.runProcedure();
	}
}
