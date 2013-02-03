package org.triangle.puzzle.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.triangle.puzzle.AbstractTest;
import org.triangle.puzzle.LongDecoder;

public class BackwardTest extends AbstractTest{

	@Test
	public void checkLinesComparingBackwardsReaderAndForwardReader() throws Exception{
		       		
		BufferedReader reader = new BufferedReader(new FileReader(getFromPath("org/triangle/puzzle/triangle_100_rows.txt")));
		
		List<Long[]> lines = new ArrayList<Long[]>();
		String line;
		while((line = reader.readLine()) != null){
			String []numbers = line.split(" ");
			Long ns[] = new Long[numbers.length];
			for(int i = 0; i < numbers.length; i++){
				ns[i] = Long.parseLong(numbers[i]);
			}
			lines.add(ns);
		}

		reader.close();
		
		LongDecoder decoder = new LongDecoder(' ');
		BackwardsReader<long []> backwardsReader = BackwardsReader.create(decoder, getFromPath("org/triangle/puzzle/triangle_100_rows.txt"));
				
		int size = lines.size();
		
		Iterator<long[]> iter = backwardsReader.iterator();
		while(iter.hasNext()){
			long l1[] = iter.next();
			
			Long l2[] = lines.get(--size);
			for(int i = 0; i < l2.length; i++ ){
				Assert.assertEquals(l1[i], l2[i].longValue());
			}
			
		}
		
		backwardsReader.close();

	}

	
}
