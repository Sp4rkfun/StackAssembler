package com.assembler;

import java.util.ArrayList;

public class State {
	
	public class Stack{
		
		private int pointer=0;
		
		public int stackPointer(){
			return pointer+1;
		}
		
		ArrayList<String> stack = new ArrayList<>(50);
		public void push(String s){
			stack.add(s);
			pointer++;
		}
		
		public String pop(){
			if(pointer==0){
			System.err.println("Can't Pop Off An Empty Stack, Exiting!");
			System.exit(0);
			}
			String s = stack.remove(pointer);
			pointer--;
			return s;
		}
		public String peek(int depth){
			depth=pointer-depth;
			if(depth<0){
				System.err.println("Can't Peek Past The Beginning Of The Stack, Exiting!");
				System.exit(0);
			}
			return stack.get(depth);
		}
	}
}
