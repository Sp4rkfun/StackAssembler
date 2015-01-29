package com.assembler.instructions.itype;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Jump extends Instruction {

	public Jump() {
		super("j", "0101000000000000");
	}
	
	@Override
	public void runProcedure() {
		State.current=State.stack.pop();
		Echo.print("Jumping To Instruction Number: "+State.current);
	}
}
