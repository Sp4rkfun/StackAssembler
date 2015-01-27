package com.assembler;

import com.assembler.instructions.Jump;
import com.assembler.instructions.JumpAndLink;
import com.assembler.instructions.PushI;

import static com.assembler.Assembler.*;
public abstract class Instruction {
	public String name;
	public String opcode;
	public int count;
	public boolean hasLabel;
	public String label;

	public Instruction(String name, String opcode){
		this.name=name;
		this.opcode=opcode;
	}
	
	public abstract void runProcedure(String[] s);
	
	public Instruction onParse(String[] s){
		return this;
	}
	
	public static Instruction createInst(String s){
		switch (s) {
		case PUSHI:
			return new PushI();
		case J:
			return new Jump();
		case JAL:
			return new JumpAndLink();
		default:
			return null;
		}
	}
	
	public Instruction flagLabel(String name){
		hasLabel=true;
		label=name;
		return this;
	}
	
}
