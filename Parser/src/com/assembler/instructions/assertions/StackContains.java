package com.assembler.instructions.assertions;

import com.assembler.Assembler;
import com.assembler.Echo;
import com.assembler.Instruction;
import com.assembler.State;

public class StackContains extends Assertion {
	int size;
	String[] s;
	public StackContains() {
		super("Stackcontains", "");
	}
	
	@Override
	public Instruction onParse(String[] s) {
		size=s.length;
		this.s=s;
		return super.onParse(s);
	}
	
	@Override
	public void runProcedure() {
		super.runProcedure();
		if(size!=State.stack.stackPointer()-Assembler.globalPointer+1){
			Echo.printD("Size Mismatch.  Expected: "+size+" Actual: "+(State.stack.stackPointer()-Assembler.globalPointer+1));
		}
		else{
			for(int i=Assembler.globalPointer-1; i<size; i++){
				if(s[i].equals("?")) continue;
				if(Integer.parseInt(s[-Assembler.globalPointer-1+i])!=State.stack.stack.get(i)){
				Echo.printD("Content Mismatch On Element "+i+".  Expected: "+s[i]+" Actual: " + State.stack.stack.get(i));
				return;
				}
			}
			Echo.printD("Assertion Of Stack Contents Correct.");
		}
	}

}
