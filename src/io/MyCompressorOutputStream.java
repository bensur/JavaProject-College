package io;

import java.io.IOException;
import java.io.OutputStream;
/**
 * Write compressed data to given output stream
 * @author Ben Surkiss & Yovel Shchori
 * @version 1.0
 */
public class MyCompressorOutputStream extends OutputStream {
	public static final int MAX_COUNT = 127;
	private OutputStream out;
	/**
	 * C'tor
	 * @param out output stream to use
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte[] arr) throws IOException {
		int arrSize = arr.length;
		int count = (arrSize / MAX_COUNT);
		out.write(count);
		out.write(MAX_COUNT);
		out.write(1);
		out.write(arrSize % MAX_COUNT);
		byte currByte = arr[0];
		count = 1;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != currByte) {
				while (count >= MAX_COUNT) {
					out.write(MAX_COUNT);
					out.write(currByte);
					count -= MAX_COUNT;
				}
				out.write(count);
				out.write(currByte);
				currByte = arr[i];
				count = 1;
			}
			else {
				count++;
			}
		}
		while (count >= MAX_COUNT) {
			out.write(MAX_COUNT);
			out.write(currByte);
			count -= MAX_COUNT;
		}
		out.write(count);
		out.write(currByte);
	}
}
