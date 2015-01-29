package com.assembler.instructions.itype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Negate extends Instruction {

	public Negate() {
		super("neg", "1000000000000000");
	}

	@Override
	public void runProcedure() {
		int value = State.stack.pop()*-1;
		State.stack.push(value);
		Echo.print("Performing Negate On Top Of Stack: "+value*-1+"*-1="+value);
		super.runProcedure();
	}

}
