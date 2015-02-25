package com.assembler.instructions.label;

import com.assembler.Assembler;
import com.assembler.Echo;

import static com.assembler.State.*;
public class BranchOnEqual extends LabelInst {

	public BranchOnEqual() {
		super("beq", "0111");
	}

	@Override
	public void runProcedure() {
		int a = stack.pop();
		int b = stack.pop();
		if(a==b){
			current=value;
			Echo.printD(a+"=="+b+" Jumping To Instruction Number: "+current);
		}
		else {
			Echo.printD(a+"!="+b);
			super.runProcedure();
		}
	}
	
	@Override
	public void parseLabel(int location) {
		super.parseLabel(location);
		machineValue+=Assembler.toBinary(location, 12);
	}

}
