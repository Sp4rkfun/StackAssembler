package com.assembler;

import com.assembler.instructions.assertions.DumpStack;
import com.assembler.instructions.assertions.StackContains;
import com.assembler.instructions.assertions.StackSize;
import com.assembler.instructions.itype.Add;
import com.assembler.instructions.itype.And;
import com.assembler.instructions.itype.Jump;
import com.assembler.instructions.itype.Negate;
import com.assembler.instructions.itype.Or;
import com.assembler.instructions.itype.SetLessThan;
import com.assembler.instructions.itype.ShiftLeftLogical;
import com.assembler.instructions.label.BranchOnEqual;
import com.assembler.instructions.label.JumpAndLink;
import com.assembler.instructions.label.PushI;
import com.assembler.instructions.pseudo.JumpRegister;
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
	
	public Instruction flagLabel(String name){
		hasLabel=true;
		label=name;
		return this;
	}
	
}
