package com.qingting.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket client ;  
    public ServerThread(Socket client){  
    	this.client = client;  
    }  

    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用 
    public static void execute(Socket client){
    	//获取Socket的输入流，用来接收从客户端发送过来的数据 
    	InputStream is=null;
    	BufferedInputStream bi=null;
    	BufferedReader buf=null;
    	
    	//获取Socket的输出流，用来向客户端发送数据 
    	OutputStream os=null;
    	BufferedOutputStream bos=null;
    	//文件流
    	File file=null;
    	FileWriter writer=null;
    	
	    try{  
	    	//获取Socket的输入流，用来接收从客户端发送过来的数据  
	    	is=client.getInputStream();
	    	bi=new BufferedInputStream(is);
	    	buf = new BufferedReader(new InputStreamReader(is)); 
	    	
	    	//获取Socket的输出流，用来向客户端发送数据  
	    	os=client.getOutputStream();
	    	bos=new BufferedOutputStream(os);
	    	
	    	//接收数据写入文本
	    	file=new File("data.log");
	    	if(file.createNewFile()){
	    		System.out.println("Create file successed.");  
	    	}
	    	writer=new FileWriter("data.log", true);
	    	
	    	//准备接收数据  
	    	boolean flag =true;  
	    	System.out.println("server prepare accept message:");
	    	while(flag){  
	    		//接收从客户端发送过来的数据  
	    		int count = 0;
	    		while (count == 0) {
	    			count = is.available();
	    		}
	    		byte[] b = new byte[count];
	    		is.read(b);
	    		for (int i=0;i<b.length;i++) {
	    			System.out.print(b[i]+" ");
	    			b[i]+=1;
				}
	    		//writer.write(new String(b));
	    		//writer.flush();
	    		bos.write(b);
	    		bos.write('A');
	    		bos.write('C');
	    		bos.write('K');
	    		bos.flush();/**/
	    	} 
	    	System.out.println("server stop.");
	   }catch(Exception e){  
	       e.printStackTrace();  
	   } finally {
		   try {
			   if(is!=null) is.close();
			   if(bi!=null) bi.close();
			   if(buf!=null) buf.close();
			   
			   if(os!=null) os.close();
			   if(bos!=null) bos.close();
			   
			   if(writer!=null) writer.close(); 
			   
			   if(client!=null)client.close();
		   } catch (IOException e) {
			   e.printStackTrace();
		   } 
		   
	   } 
    }

    @Override  
    public void run() {  
    	execute(client);
    }  
}
