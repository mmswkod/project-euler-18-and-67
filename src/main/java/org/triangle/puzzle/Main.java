package org.triangle.puzzle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.triangle.puzzle.util.TriangleFileGenerator;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("(1) Find the longest path for an existing file");
		System.out.println("(2) Generate a triangle file and get the longest path");
		System.out.println("Option: ");
		while (sc.hasNext()) {

			String cmd = sc.next();
			if (!"1".equals(cmd) && !"2".equals(cmd)) {
				System.out.println("You should type 1 or 2");
				System.exit(0);
			}

			if ("1".equals(cmd)) {
				System.out.println("Please provide the absolute path to triangle file:");
				cmd = sc.next();
				if (!Files.exists(Paths.get(cmd))) {
					System.out.println("Invalid file...");
					System.exit(0);
				}
				Triangle triangle = new Triangle(new File(cmd), ' ');
				System.out.println("The longest path is " + triangle.longestPath());
				

			} else {
				System.out.println("Inform how many lines for this triangle file:");
				cmd = sc.next();
				try {
					int i = Integer.parseInt(cmd);
					String currentDirectoryName = System.getProperty("user.dir");
					Path triangleAreaPath = Paths.get(currentDirectoryName, "trianglearea");
					if(!Files.exists(triangleAreaPath)){
						Files.createDirectory(triangleAreaPath);	
					}
					
					Path triangleFilePath = Files.createFile(Paths.get(triangleAreaPath.toString(), System.currentTimeMillis()+"_.txt"));
					System.out.println("A file was create: "+triangleFilePath);
					
					TriangleFileGenerator.generate(i, 10000, triangleFilePath.toFile());
					Triangle triangle = new Triangle(triangleFilePath.toFile(), ' ');
					System.out.println("The longest path is " + triangle.longestPath());
					
					
				} catch (NumberFormatException e) {
					System.out.println("Invalid number..");
					System.exit(0);

				}
			}
			System.exit(0);

		}

	}

}
