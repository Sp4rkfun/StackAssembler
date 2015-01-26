package com.assembler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Assembler.useInstruction(Assembler.ALL);
		try {
			for(String line: Files.readAllLines(Paths.get("asm/test.asm"), StandardCharsets.US_ASCII)){
				String[] in= line.split(" ");
				Assembler.parseInstruction(in);
				//System.out.println(in[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
