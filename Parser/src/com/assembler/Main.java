package com.assembler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Assembler.useInstruction(Assembler.ALL);
		try {
			for(String line: Files.readAllLines(Paths.get("asm/euclidTest.asm"), StandardCharsets.US_ASCII)){
				line=line.trim();
				if(line.equals(""))continue;
				String[] in= line.split(" ");
				Assembler.parseInstruction(in);
			}
			Assembler.linkLabels();
			Assembler.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
