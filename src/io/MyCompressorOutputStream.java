package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	public static final int MAX_COUNT = 127;
	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException {
		out.write(b);
	}
	
	@Override
	public void write(byte[] arr) throws IOException {
		byte currByte = arr[0];
		int count = 1;
		
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
