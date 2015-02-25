package com.assembler;

public class Echo {
	public static boolean verbose = true;
	public static boolean debug = true;
	
	/**
	 * Prints output from Assembler.
	 * 
	 * @param o Object to print.
	 */
	public static void print(Object o){
		if(verbose)
			System.out.println(o);
	}
	
	/**
	 * Prints output from the general instructions.
	 * 
	 * @param o Object to print.
	 */
	public static void printV(Object o){
		if(verbose)
			System.out.println(o);
	}
	
	/**
	 * Prints output from Assertion type instructions.
	 * 
	 * @param o Object to print.
	 */
	public static void printD(Object o){
		if(debug)
			System.out.println(o);
	}
}
