package com.qingting.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.qingting.server.ServerThread;

public class TestClient {
	public static void main(String[] str) {  
		//String ipAddr="192.168.10.20";
		//String port="50021";
		String ipAddr="119.29.225.162";
		String port="40040";
      
        Socket sk=null; 
        //输入信息流
        InputStream is=null; 
        BufferedInputStream bi = null;
        //输出信息流  
        OutputStream os=null;
        BufferedOutputStream bos=null;
        try {  
            sk=new Socket(InetAddress.getByName(ipAddr),  
                    Integer.parseInt(port));  
            //输入信息流
            is=sk.getInputStream(); 
            bi = new BufferedInputStream(is);
            //输出信息流  
            os=sk.getOutputStream();
            bos=new BufferedOutputStream(os);
            
            //CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		32,//remain length
            		//可变头部
            		0,//Protocol name Length
            		4,
            		'M',//Protocol name
            		'Q',
            		'T',
            		'T',
            		4,//Protocol version
            		(byte)206,//Connect flags(username flag(1),password flag(1),will Retain(0),will Qos(01),will flag(1),clean session(1))
            		0,//Keep Alive timer(s)
            		10,
            		//有效载荷
            		0,//length
            		2,
            		0,//client ID(必须)
            		1,
            		
            		0,//length
            		1,
            		'a',//will topic
            		
            		0,//length
            		1,
            		'b',//will content
            		
            		0,//length
            		4,
            		't',//username
            		'e',
            		's',
            		't',
            		
            		0,//length
            		4,
            		't',//password
            		'e',
            		's',
            		't'
            };
            bos.write(ch);
            bos.flush();
            System.out.print("准备接收连接应答信息.");
            
    		//new Thread(new ReceiveThread(is)).start(); 
            int count = 0;
			while (count == 0) {
				count = is.available();
			}
			byte[] b = new byte[count];
			is.read(b);
			for (byte c : b) {
				System.out.print(c+" ");
			}
    		
            System.out.println("连接应答结束，开始推送信息.");
            
            count=0;
            while(true){
            	sendPublic(is,bos);
            	count++;
            }
        } catch (Exception e){  
            e.printStackTrace();  
        } finally {
        	try {
        		bi.close();
        		is.close();
        		bos.close();
				os.close();
				sk.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
  
    }
	
	public static void sendPublic(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
	        		//固定头部
	        		50,//publish 
	        		13,//remain length
	        		//可变头部
	        		0,//topic name length
	        		3,
	        		'a',//topic name
	        		'/',
	        		'b',
	        		0,//message id
	        		10,
	        		1,//payload
	        		0,
	        		2,
	        		65,
	        		'o',
	        		'k'
	        };
	        bos.write(publish);
	        bos.flush();
	        
	        System.out.print("准备接收推送应答信息.");
	        
	        int count = 0;
			while (count == 0) {
				count = is.available();
			}
			byte[] b = new byte[count];
			is.read(b);
			for (byte c : b) {
				System.out.print(c+" ");
			}
			
	        System.out.println("接收推送应答信息结束.");
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
}
