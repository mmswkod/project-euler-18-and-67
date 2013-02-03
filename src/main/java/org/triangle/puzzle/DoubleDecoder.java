package org.triangle.puzzle;

import org.triangle.puzzle.io.Decoder;

/**
 * 
 * Decoder to deal with double numbers as line elements.
 *
 */
public class DoubleDecoder implements Decoder<double[]> {

	private char separator;

	public DoubleDecoder(char separator) {
		this.separator = separator;
	}

	public double[] decode(char[] cs) {
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


		double[] rs = new double[totalNumbers];
		int pos = 0;
		double l = 0;
		char last = ' ';
		for (i = 0; i < cs.length; i++) {

			if (cs[i] == separator) {
				pos++;
				l = 0;
				continue;
			}

			if(cs[i] == ',')continue;
			if(cs[i] == '.'){
				last = cs[i];
				continue;
			}
			
			int c = Character.digit(cs[i], 10);
			if( c < 0) 
				throw new RuntimeException("An invalid number was found -> "+cs[i]);
				
			if (l > 0) {
				l = (l * 10) + c;
				if(last == '.'){
					l /= 10; 
				}
			} else {
				l = c;
			}
			rs[pos] = l;
			last = cs[i];
		}
		return rs;
	}

}
