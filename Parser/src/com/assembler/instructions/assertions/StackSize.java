package com.assembler.instructions.assertions;

import com.assembler.Assembler;
import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class StackSize extends Assertion {
	int check;
	public StackSize() {
		super("Stacksize", "");
	}
	@Override
	public Instruction onParse(String[] s) {
		check=Integer.parseInt(s[0]);
		return this;
	}
	@Override
	public void runProcedure() {
		if(check!=State.stack.stackPointer()-Assembler.globalPointer){
			Echo.printD("Assertion That "+check+" Items Are In The Stack Incorrect.  Contains "+(State.stack.stackPointer()-Assembler.globalPointer));
		}
		else
			Echo.printD("Assertion That "+check+" Items Are In The Stack Correct.");
		super.runProcedure();
	}
}
