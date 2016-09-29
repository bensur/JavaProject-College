package io;

import java.io.IOException;
import java.io.InputStream;
/**
 * Read compressed data from given input stream
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class MyDecompressorInputStream extends InputStream {
	private InputStream in;
	/**
	 * C'tor
	 * @param in input stram to use
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {		
		return in.read();
	}
	
	@Override
	public int read(byte[] arr) throws IOException {
		int k = 0;
		while (k < arr.length) {
			byte count = (byte)in.read();
			byte b = (byte)in.read();
			for (int j = 0; j < count; j++) {
				arr[k] = b;
				k++;
			}
		}
		return arr.length;		
	}
}
