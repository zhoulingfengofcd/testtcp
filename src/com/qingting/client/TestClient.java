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
		String ipAddr="39.108.52.201";
		String port="40040";
      
        Socket sk=null; 
        //输入信息流
        InputStream is=null; 
        //输出信息流  
        OutputStream os=null;
        BufferedOutputStream bos=null;
        try {  
            sk=new Socket(InetAddress.getByName(ipAddr),  
                    Integer.parseInt(port));  
            //输入信息流
            is=sk.getInputStream(); 
            //输出信息流  
            os=sk.getOutputStream();
            bos=new BufferedOutputStream(os);
            
            sendConn(is,bos);
            
            int count=0;
            while(true){
            	sendPublic(is,bos);
            	sendPingreq(is,bos);
            	count++;
            	if(count==2){
            		Thread.sleep(16000);
            		
            		
            		/*sendClose(is,bos);
            		
					sk=new Socket(InetAddress.getByName(ipAddr),  
					        Integer.parseInt(port));  
					//输入信息流
					is=sk.getInputStream(); 
					//输出信息流  
					os=sk.getOutputStream();
					bos=new BufferedOutputStream(os);
            		sendConn(is,bos);*/
            	}
            	if(count==5)
            		while(true);
            }
        } catch (Exception e){  
            e.printStackTrace();  
        } finally {
        	try {
        		is.close();
        		bos.close();
				os.close();
				sk.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
  
    }
	public static void sendConn(InputStream is,BufferedOutputStream bos){
		try{
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
            		55,//client ID(必须)
            		56,//--ascii码8
            		
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
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	public static void sendPublic(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
					
	        		//固定头部
	        		50,//publish 
	        		29,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		6,
	        		'm',//topic name
	        		'o',
	        		'n',
	        		't',
	        		'o',
	        		'r',
	        		0,//message id
	        		10,
	        		
	        		//payload
	        		17,//--year
	        		1, //--month
	        		1, //--day
	        		12, //--hour
	        		0, //--minute
	        		0, //--second
	        		
	        		15,//--relay
	        		0, //--flow
	        		1,
	        		1,
	        		1,
	        		1,
	        		1,
	        		
	        		12,//--rawTds
	        		12,
	        		0,//--purTds
	        		19,
	        		75,//--temp
	        		23//--humidity
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
	public static void sendPingreq(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
	        		//固定头部
	        		(byte)192,//publish 
	        		0//remain length
	        };
	        bos.write(publish);
	        bos.flush();
	        
	        System.out.print("准备接收心跳应答信息.");
	        
	        int count = 0;
			while (count == 0) {
				count = is.available();
			}
			byte[] b = new byte[count];
			is.read(b);
			for (byte c : b) {
				System.out.print(c+" ");
			}
			
	        System.out.println("接收心跳应答信息结束.");
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	public static void sendClose(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
	        		//固定头部
	        		(byte)224,//disconnect 
	        		0//remain length
	        };
	        bos.write(publish);
	        bos.flush();
	        
	        System.out.println("连接断开.");
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
}
