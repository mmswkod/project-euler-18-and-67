package org.triangle.puzzle;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.triangle.puzzle.io.BackwardsReader;

public class Triangle {

	private BackwardsReader<double[]> reader;

	public Triangle(File file, char separator) throws IOException {
		DoubleDecoder decoder = new DoubleDecoder(separator);
		reader = BackwardsReader.create(decoder, file);

	}

	/**
	 * Iterates over a triangle file and finds the longest path
	 * @return
	 */
	public double longestPath(){

		Iterator<double[]> iterator = reader.iterator();

		if (!iterator.hasNext())
			throw new RuntimeException("This file is empty.");

		double[] bottomLine = iterator.next();

		while (iterator.hasNext()) {
			double[] upLine = iterator.next();

			for (int i = 0; i < bottomLine.length - 1; i++) {
				upLine[i] += Math.max(bottomLine[i], bottomLine[i + 1]);
			}

			bottomLine = upLine;
		}

		return bottomLine[0];

	}

}
