package com.assembler.instructions.label;

import static com.assembler.State.current;
import static com.assembler.State.ra;

import com.assembler.Assembler;
import com.assembler.Echo;

public class JumpAndLink extends LabelInst {

	public JumpAndLink() {
		super("jal", "0110");
	}
	@Override
	public void parseLabel(int location) {
		super.parseLabel(location);
		machineValue+=Assembler.toBinary(location, 12);
	}
	@Override
	public void runProcedure() {
		ra=count+1;
		current=value;
		Echo.printV("Jumping To Instruction Number: "+current);
	}

}
