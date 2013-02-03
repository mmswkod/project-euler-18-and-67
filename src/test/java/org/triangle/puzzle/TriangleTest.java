package org.triangle.puzzle;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;
import org.triangle.puzzle.util.Stopwatch;

public class TriangleTest extends AbstractTest{

	final char separator = ' ';
	private static Logger LOGGER = Logger.getLogger("TriangleTest");
	@Rule
	public ExpectedException rte = ExpectedException.none();
	
	@Test
	public void longestPathFor10000Rows() throws Exception {

        Stopwatch sw = new Stopwatch();
		
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_10000_rows.txt"), separator);
		
		Assert.assertEquals(74890272d, t.longestPath());
		
		LOGGER.log(Level.INFO, "10,000 rows in "+sw.elapsedTime() + " milliseconds");
	}


	@Test
	public void longestPathFor100Rows() throws Exception {
        Stopwatch sw = new Stopwatch();
		
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_100_rows.txt"), separator);
		
		Assert.assertEquals(7273d, t.longestPath());
		
		LOGGER.log(Level.INFO, "100 rows in "+sw.elapsedTime() + " milliseconds");

	}

	@Test
	public void longestPathFor6Rows() throws Exception {
        Stopwatch sw = new Stopwatch();
		
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_6_rows.txt"), separator);
		
		Assert.assertEquals(10001d, t.longestPath());
		
		LOGGER.log(Level.INFO, "6 rows in "+sw.elapsedTime() + " milliseconds");

	}

	@Test
	public void longestPathFor1Rows() throws Exception {
        Stopwatch sw = new Stopwatch();
		
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_1_rows.txt"), separator);
		
		Assert.assertEquals(5d, t.longestPath());
		
		LOGGER.log(Level.INFO, "1 rows in "+sw.elapsedTime() + " milliseconds");

	}

	@Test
	public void longestPathFor10Rows() throws Exception {
        Stopwatch sw = new Stopwatch();
								
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_10_rows.txt"), separator);
		
		Assert.assertEquals(65595d, t.longestPath());
		
		LOGGER.log(Level.INFO, "10 rows in "+sw.elapsedTime() + " milliseconds");
		
	}
	
	@Test
	public void longestPathFor10RowsWithEmptyLine() throws Exception {
        Stopwatch sw = new Stopwatch();
								
		Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_10_rows_empty_line.txt"), separator);
		
		Assert.assertEquals(65595d, t.longestPath());
		
		LOGGER.log(Level.INFO, "10 rows in "+sw.elapsedTime() + " milliseconds");
		

	}
	

	@Test
	public void longestPathFor4Rows() throws Exception {
        Stopwatch sw = new Stopwatch();
        Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_4_rows.txt"), separator);
		
		Assert.assertEquals(27d, t.longestPath());
		
		LOGGER.log(Level.INFO, "4 rows in "+sw.elapsedTime() + " milliseconds");

	}

	@Test
	public void longestPathForNumbersAndLettersRows() throws Exception {
		rte.expect(RuntimeException.class);
		rte.expectMessage(JUnitMatchers.containsString("An invalid number was found ->"));
        Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_numbers_and_letters_rows.txt"), separator);
		
		Assert.assertEquals(27d, t.longestPath());

	}

	
	@Test
	public void longestPathForNoRows() throws Exception {
		rte.expect(RuntimeException.class);
		rte.expectMessage("This file is empty.");
		
        Stopwatch sw = new Stopwatch();
        Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_no_rows.txt"), separator);
		
		Assert.assertEquals(0d, t.longestPath());
		
		LOGGER.log(Level.INFO, sw.elapsedTime() + " milliseconds");		

	}

	@Test
	public void longestPathFor4RowsDoubleNumbers() throws Exception {
        Stopwatch sw = new Stopwatch();
        Triangle t = new Triangle(getFromPath("org/triangle/puzzle/triangle_4_rows_double.txt"), separator);
		
		Assert.assertEquals(7020.5, t.longestPath());
		
		LOGGER.log(Level.INFO, "4 rows in "+sw.elapsedTime() + " milliseconds");

	}

}
