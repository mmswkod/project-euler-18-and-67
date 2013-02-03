package org.triangle.puzzle.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Iterator;

/**
 * 
 * Reads a file in a reverse order, it means getting it line from bottom to top.
 * Each line comes in an array of <T>. This provides an iterator to make easy to deal with.
 *
 * @param <T> it is the decoder responsible for parsing each element in a line and returns a set of <T>
 * @param file Triangle file
 */
public class BackwardsReader<T> implements Iterable<T> {

	private Decoder<T> decoder;
	private BufferedReader reader;

	private BackwardsReader(Decoder<T> decoder, File file) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(new BackwardsInputStream(file)));
			this.decoder = decoder;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> BackwardsReader<T> create(Decoder<T> decoder, File file) {
		assert decoder != null && file != null;
		return new BackwardsReader<T>(decoder, file);

	}
	
	public void close(){
		try {
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private T entry;
			private String backwardLine;

			public boolean hasNext() {
				try {
					
					while(true){
						backwardLine = reader.readLine();
						if(backwardLine == null) {close(); return  false;}
						char cs[] = backwardLine.trim().toCharArray();
						if(cs.length > 0){
							entry = decoder.decode(cs);
							return true;							
						}
					}																							

				} catch (IOException e) {
					close();
					throw new RuntimeException(e);
				}
																
			}
			
			public T next() {
				T r = entry;
				entry = null;
				return r;				
			}

			public void remove() {
				throw new UnsupportedOperationException();
				
			}
		};
		
	}
	
	
	public class BackwardsInputStream extends InputStream {

		private final RandomAccessFile randomAccessFile;
		
		//considering a good filesystem page size for big files
		private final byte[] inputBuffer = new byte[8 * 1024];

		private long posInFile;
		private int posInBuffer = 0;
		
		public BackwardsInputStream(File file) throws IOException{
			randomAccessFile = new RandomAccessFile(file, "r");
			this.posInFile = randomAccessFile.length();
		}
		
		
		/**
		 * the next byte of data, or -1 if the end of the stream has been reached.
		 */
		@Override
		public int read() throws IOException {
		
			//check if reach the eof
			if(posInFile <= 0) return -1;
			
			//check if buffer is empty and then fill in
			if(--posInBuffer < 0){
				posInBuffer = inputBuffer.length;
				long posInDiskBlock = posInFile - inputBuffer.length;
				if(posInDiskBlock < 0 ){
					posInBuffer = inputBuffer.length + (int)posInDiskBlock;
					posInDiskBlock = 0;
				}
				randomAccessFile.seek(posInDiskBlock);
				randomAccessFile.readFully(inputBuffer, 0, posInBuffer);
				return read();
			}
			
			posInFile--;
			return inputBuffer[posInBuffer];
		}
		
		public void close() throws IOException {
			randomAccessFile.close();
		}

	}


}
