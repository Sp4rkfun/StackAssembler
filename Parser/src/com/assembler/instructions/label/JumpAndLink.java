package com.assembler.instructions.label;

import com.assembler.Assembler;
import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

import static com.assembler.State.*;

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
		ra=count;
		current=value;
		Echo.printV("Jumping To Instruction Number: "+current);
	}

}
