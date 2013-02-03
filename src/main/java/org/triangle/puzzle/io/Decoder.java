package org.triangle.puzzle.io;

/**
 * 
 * Interface for decoder. Deals with a file line in char[] and returns a <T>. Example: returns a double[] 
 *
 * @param <T>
 */
public interface Decoder<T> {

	public T decode(char[] cs);
}
