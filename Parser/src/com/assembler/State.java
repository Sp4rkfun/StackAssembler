package com.assembler;

import java.util.ArrayList;

public class State {
	
	protected static ArrayList<Instruction> instructions = new ArrayList<>();
	public static int terminate;
	public static int current;
	public static Stack stack = new Stack();
	public static int ra;
	public static class Stack{
		
		private int pointer=0;
		
		public int stackPointer(){
			return pointer;
		}
		
		public ArrayList<Integer> stack = new ArrayList<>(50);
		public void push(Integer s){
			stack.add(s);
			pointer++;
		}
		
		public Integer pop(){
			pointer--;
			if(pointer<0){
			System.err.println("Can't Pop Off An Empty Stack, Exiting!");
			System.exit(0);
			}
			Integer s = stack.remove(pointer);
			return s;
		}
		public Integer peek(int depth){
			depth=pointer-depth;
			if(depth<0){
				System.err.println("Can't Peek Past The Beginning Of The Stack, Exiting!");
				System.exit(0);
			}
			return stack.get(depth);
		}
	}
}
