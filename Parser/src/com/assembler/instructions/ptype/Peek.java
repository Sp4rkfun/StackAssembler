package com.assembler.instructions.ptype;

import com.assembler.Assembler;
import com.assembler.Instruction;
import com.assembler.State;

public class Peek extends Instruction {
	public boolean global;
	public int depth;
	String reg;
	String src="$at";
	public Peek() {
		super("peek", "0100");
	}
	@Override
	public Instruction onParse(String[] s) {
		reg = s[0];
		machineValue+=State.regNum(reg);
		if(s[1].charAt(0)=='$'){
			src=s[1];
			machineValue+=State.regNum(s[1])+"000000";
		}
		else if(!s[1].matches("-?\\d+(\\.\\d+)?")){
			global=true;
			//isglobal
			String[] dst=s[1].split("\\[");
			dst[1]=dst[1].substring(0, dst[1].length()-1);
			//depth = Integer.parseInt(dst[1])+Assembler.globals.get(dst[0]);
			if(dst[1].charAt(0)=='$'){
				machineValue+="111000001";
				Assembler.parseInstruction(new String[]{"push",dst[1]});
				Assembler.parseInstruction(new String[]{"pushi", Assembler.globals.get(dst[0])+""});
				Assembler.parseInstruction(new String[]{"add"});
			}
			else{
			Assembler.parseInstruction(new String[]{"pushi", Integer.parseInt(dst[1])+Assembler.globals.get(dst[0])+""});
			machineValue+="111000001";
			}
			Assembler.parseInstruction(new String[]{"pop","$at"});
			}
		else{
			Assembler.parseInstruction(new String[]{"pushi", Integer.parseInt(s[1])+""});
			Assembler.parseInstruction(new String[]{"pop","$at"});
			machineValue+="111000000";
			//depth = -1*Integer.parseInt(s[1]);
		}
		
		return super.onParse(s);
	}
	@Override
	public void runProcedure() {
		int value = State.regContents(src);
		if(!global)value=State.stack.pointer-value-1;
		value = State.stack.peek(value);
		State.updateRegister(reg, value);
		System.out.println("Peeked Value "+value+" From Stack Into Register "+reg);
		super.runProcedure();
	}

}
