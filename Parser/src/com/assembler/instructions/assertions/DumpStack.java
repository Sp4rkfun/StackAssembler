package com.assembler.instructions.assertions;

import com.assembler.Assembler;
import com.assembler.Echo;
import com.assembler.State;

public class DumpStack extends Assertion {

	public DumpStack() {
		super("Dumpstack", "");
	}
	@Override
	public void runProcedure() {
		Echo.printD("Displaying Stack Contents:");
		Echo.printD("Globals");
		for (int i = 0; i < Assembler.globalPointer-1; i++) {
			Echo.printD(i+"  "+State.stack.stack.get(i));
		}
		Echo.printD("///////////");
		Echo.printD("User Stack");
		for (int i = Assembler.globalPointer-1; i < State.stack.stackPointer(); i++) {
			Echo.printD(i-Assembler.globalPointer+1+"  "+State.stack.stack.get(i));
		}
		super.runProcedure();
	}
}
