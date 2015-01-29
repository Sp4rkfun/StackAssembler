package com.assembler;

import com.assembler.instructions.assertions.DumpStack;
import com.assembler.instructions.assertions.StackContains;
import com.assembler.instructions.assertions.StackSize;
import com.assembler.instructions.itype.Add;
import com.assembler.instructions.itype.Jump;
import com.assembler.instructions.label.BranchOnEqual;
import com.assembler.instructions.label.JumpAndLink;
import com.assembler.instructions.label.PushI;
import com.assembler.instructions.ptype.Peek;
import com.assembler.instructions.ptype.Pop;
import com.assembler.instructions.ptype.Push;

import static com.assembler.Assembler.*;
public abstract class Instruction {
	public String name;
	public String opcode;
	public String machineValue;
	public int count;
	public boolean hasLabel;
	public String label;

	public Instruction(String name, String opcode){
		this.name=name;
		this.opcode=opcode;
		this.machineValue=opcode;
	}
	
	public void runProcedure(){
		State.current++;
	}
	
	public Instruction onParse(String[] s){
		return this;
	}
	
	public static Instruction createInst(String s){
		switch (s) {
		case ADD:
			return new Add();
		case PUSHI:
			return new PushI();
		case PUSH:
			return new Push();
		case POP:
			return new Pop();
		case J:
			return new Jump();
		case JAL:
			return new JumpAndLink();
		case BEQ:
			return new BranchOnEqual();		
		case PEEK:
			return new Peek();
			
			
		case STACKSIZE:
			return new StackSize();
		case DUMPSTACK:
			return new DumpStack();
		case STACKCONTAINS:
			return new StackContains();
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
