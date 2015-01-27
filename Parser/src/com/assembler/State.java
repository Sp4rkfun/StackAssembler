package com.assembler;

import java.util.ArrayList;

public class State {
	
	protected static ArrayList<Instruction> instructions = new ArrayList<>();
	public static int terminate;
	public static int current;
	public static Stack stack = new Stack();
	public static int ra;
	public static int f0=0;
	public static int f1=0;
	public static int a0=0;
	public static int a1=0;
	public static int v0=0;
	public static void updateRegister(String reg, int value){
		switch (reg) {
		case "$f0":
			f0=value;
			return;
		case "$f1":
			f1=value;
			return;
		case "$a0":
			a0=value;
			return;
		case "$a1":
			a1=value;
			return;
		case "$v0":
			v0=value;
			return;
		default:
			return;
		}
	}
	public static int regContents(String reg){
		switch (reg) {
		case "$f0":
			return f0;
		case "$f1":
			return f1;
		case "$a0":
			return a0;
		case "$a1":
			return a1;
		case "$v0":
			return v0;
		default:
			return 0;
		}
	}
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
			if(depth>0){
			 return stack.get(depth-1);
			}
		else
		{
			depth=pointer-depth;
			if(depth<0){
				System.err.println("Can't Peek Past The Beginning Of The Stack, Exiting!");
				System.exit(0);
			}
			return stack.get(depth-1);
		}
		}
	}
}
