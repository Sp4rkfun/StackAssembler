package com.assembler.instructions.itype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Add extends Instruction {

	public Add() {
		super("add", "1011000000000000");
	}

	@Override
	public void runProcedure() {
		int a = State.stack.pop();
		int b = State.stack.pop();
		State.stack.push(a+b);
		Echo.print("Performing Add On Stack: "+a+"+"+b+"="+(a+b));
		super.runProcedure();
	}

}
