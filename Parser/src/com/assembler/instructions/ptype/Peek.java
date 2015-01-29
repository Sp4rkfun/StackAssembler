package com.assembler.instructions.ptype;

import com.assembler.Assembler;
import com.assembler.Instruction;
import com.assembler.State;

public class Peek extends Instruction {
	public int depth;
	String reg;
	public Peek() {
		super("peek", "0100000000000000");
	}
	@Override
	public Instruction onParse(String[] s) {
		reg = s[0];
		if(!s[1].matches("-?\\d+(\\.\\d+)?")){
			//isglobal
			String[] dst=s[1].split("\\[");
			dst[1]=dst[1].substring(0, dst[1].length()-1);
			//depth = Integer.parseInt(dst[1])+Assembler.globals.get(dst[0]);
			if(dst[1].charAt(0)=='$'){
				Assembler.parseInstruction(new String[]{"push",dst[1]});
				Assembler.parseInstruction(new String[]{"pushi", Assembler.globals.get(dst[0])+""});
				Assembler.parseInstruction(new String[]{"add"});
			}
			else
			Assembler.parseInstruction(new String[]{"pushi", Integer.parseInt(dst[1])+Assembler.globals.get(dst[0])+""});
			}
		else{
			Assembler.parseInstruction(new String[]{"pushi", -1*Integer.parseInt(s[1])+""});
			//depth = -1*Integer.parseInt(s[1]);
		}
		
		return super.onParse(s);
	}
	@Override
	public void runProcedure() {
		int value = State.stack.pop();
		value = State.stack.peek(value);
		State.updateRegister(reg, value);
		System.out.println("Peeked Value "+value+" From Stack Into Register "+reg);
		super.runProcedure();
	}

}
