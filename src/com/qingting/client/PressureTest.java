package com.qingting.client;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.plaf.SliderUI;

public class PressureTest {
	public static void main(String[] arg){
		String ipAddr="39.108.52.201";
		String port="40050";
		int count=0;
         
        //输入信息流
        //InputStream is=null; 
        //输出信息流  
        //BufferedOutputStream bos=null;
        try {  
            while(count<10){
            	Socket sk=new Socket(InetAddress.getByName(ipAddr),  
                        Integer.parseInt(port));  
                //输入信息流
            	final InputStream is=sk.getInputStream(); 
                //输出信息流  
                final BufferedOutputStream bos=new BufferedOutputStream(sk.getOutputStream());
            	
                new Thread(new Runnable(){
					@Override
					public void run() {
						new TxThread(is, bos).runFunc();
					}
                }).start();
            	
            	count++;
            	Thread.sleep(2000);
            	System.out.println("====================客户端计数"+count+"==================");
            }
        }catch(Exception e){
        	
        }
	}
}
