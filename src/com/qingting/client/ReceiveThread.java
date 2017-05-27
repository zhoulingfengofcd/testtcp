package com.qingting.client;

import java.io.InputStream;

public class ReceiveThread implements Runnable{
	InputStream is;
	public ReceiveThread(InputStream is){
		this.is=is;
	}
	@Override  
	public void run()  {  
		try{
			while(true){
				int count = 0;
				while (count == 0) {
					count = is.available();
				}
				byte[] b = new byte[count];
				is.read(b);
				for (byte c : b) {
					System.out.print(c+" ");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}  
}
