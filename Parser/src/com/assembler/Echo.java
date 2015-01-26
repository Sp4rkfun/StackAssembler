package com.assembler;

public class Echo {
	public static boolean verbose = true;
	public static void print(Object o){
		if(verbose)
			System.out.println(o);
	}
}
