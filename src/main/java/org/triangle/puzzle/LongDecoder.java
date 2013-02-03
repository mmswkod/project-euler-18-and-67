package org.triangle.puzzle;

import org.triangle.puzzle.io.Decoder;

/**
 * 
 * Decoder to deal with long numbers as line elements.
 *
 */
public class LongDecoder implements Decoder<long[]> {

	private char separator;

	public LongDecoder(char separator) {
		this.separator = separator;
	}

	public long[] decode(char[] cs) {
		int i = 0;
		int j = cs.length;
		
		while (i < j) {
			char t = cs[i];
			cs[i] = cs[--j];
			cs[j] = t;
			i++;
		}
		
		int totalNumbers = 1;
		for (i = 0; i < cs.length; i++) {
			if (cs[i] == separator){
				totalNumbers++;
			}
		}


		long[] rs = new long[totalNumbers];
		int pos = 0;
		long l = 0;
		for (i = 0; i < cs.length; i++) {

			if (cs[i] == separator) {
				pos++;
				l = 0;
				continue;
			}

			int c = Character.digit(cs[i], 10);
			if( c < 0  && cs[i] != '.') 
				throw new RuntimeException("An invalid number was found -> "+cs[i]);
				
			if (l > 0) {
				l = (l * 10) + c;
			} else {
				l = c;
			}
			rs[pos] = l;
		}
		return rs;
	}

}
