package com.assembler.instructions.assertions;

import com.assembler.State;

public class DumpStack extends Assertion {

	public DumpStack() {
		super("Dumpstack", "");
	}
	@Override
	public void runProcedure() {
		System.out.println("Displaying Stack Contents:");
		for (int i = 0; i < State.stack.stackPointer(); i++) {
			System.out.println(i+"  "+State.stack.stack.get(i));
		}
		super.runProcedure();
	}
}
