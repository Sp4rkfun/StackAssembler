package com.assembler.instructions.itype;

import com.assembler.Assembler;
import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class Jump extends Instruction {

	public Jump() {
		super("j", "0101000000000000");
	}
	
	@Override
	public Instruction onParse(String[] s) {
		if(s.length==1){
		if(s[0].charAt(0)=='$')Assembler.parseInstruction(new String[]{"push",s[0]});
		else Assembler.parseInstruction(new String[]{"pushi",s[0]});
			}
		
		
		return super.onParse(s);
	}
	
	@Override
	public void runProcedure() {
		State.current=State.stack.pop();
		Echo.print("Jumping To Instruction Number: "+State.current);
	}
}
