package com.assembler.instructions;

import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;


public class PushI extends JumpInst {
	int value;
	public PushI() {
		super("pushi", "0000");
	}

	@Override
	public void runProcedure() {
		Echo.print("Pushing To Stack: "+value);
		State.stack.push(value);
		super.runProcedure();
	}
	@Override
	public Instruction onParse(String[] s) {
		if(!s[0].matches("-?\\d+(\\.\\d+)?")){
		return super.onParse(s);
		}
		needsLink=false;
		value=Integer.parseInt(s[0]);
		return this;
	}
	@Override
	public void parseLabel(int location) {
		value=location;
	}

	
}
