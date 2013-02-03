package org.triangle.puzzle.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TriangleFileGeneratorTest {

	@Test
	public void testGenerate() throws IOException {
		String currentDirectoryName = System.getProperty("user.dir");
		Path triangleAreaPath = Paths.get(currentDirectoryName, "trianglearea");
		Path triangleAreaDirPath = Files.createDirectory(triangleAreaPath);
		Path triangleFilePath = Files.createFile(Paths.get(triangleAreaDirPath.toString(), "rows.txt"));
		
		TriangleFileGenerator.generate(30, 100, triangleFilePath.toFile());
		System.out.println("Generated file under "+triangleFilePath.toString());
	}

}
