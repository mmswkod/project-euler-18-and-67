package org.triangle.puzzle.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * Util class to produce triangle files
 *
 */
public class TriangleFileGenerator {

	public static void generate(int lines, int randomMax, File path) throws IOException{
		
		if(randomMax < 1) randomMax = Integer.MAX_VALUE;
		
		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < lines; i++){
			for(int j = 0; j <= i; j++){
				builder.append(random.nextInt(randomMax)).append(" ");
			}
			builder.deleteCharAt(builder.length() - 1);
			builder.append("\n");
			bw.append(builder);
			builder.setLength(0);
		}
		
		bw.close();

	}
	
}
