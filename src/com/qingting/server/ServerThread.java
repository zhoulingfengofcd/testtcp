package com.qingting.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket client ;  
    public ServerThread(Socket client){  
    	this.client = client;  
    }  

    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用 
    public static void execute(Socket client){
	    try{  
	    	//BufferedReader input = new BufferedReader(new InputStreamReader(System.in));  
	
	    	//获取Socket的输出流，用来向客户端发送数据  
	    	OutputStream os=client.getOutputStream();
	    	//PrintStream out = new PrintStream(os);
	    	BufferedOutputStream bos=new BufferedOutputStream(os);
	    	//接收数据写入文本
	    	File file=new File("data.log");
	    	if(file.createNewFile()){
	    		System.out.println("Create file successed.");  
	    	}
	    	//BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
	    	FileWriter writer=new FileWriter("data.log", true);
	    	//获取Socket的输入流，用来接收从客户端发送过来的数据  
	    	InputStream is=client.getInputStream();
	    	BufferedInputStream bi=new BufferedInputStream(is);
	    	BufferedReader buf = new BufferedReader(new InputStreamReader(is));  
	    	boolean flag =true;  
	    	int a;
	    	System.out.println("server accept message:");
	    	while(flag){  
	    		//接收从客户端发送过来的数据  
	    		a=bi.read();
	    		//a=buf.read();
	    		
	    		if(a!=-1 && a!=105){
	    			System.out.print(a+" ");
	    		}else{
	    			System.out.print("应答："+a+" ");
	    			bos.write(1);
	    	    	bos.flush();
	    		}
	    		
	    		/*String str =  buf.readLine();  
	    		System.out.println("server accept message:"+str);
	    		System.out.println();
	    		byte[] bytes=str.getBytes();
	    		for(int i=0;i<bytes.length;i++){
	    			System.out.print(bytes[i]+" ");
	    		}
	    		if(str == null || "".equals(str)){  
	    			flag = false;  
	    		}else{  
	    			if("bye".equals(str)){  
	    				flag = false;  
	    			}else{  
	    				//将接收到的字符串前面加上echo，发送到对应的客户端  
	    				// out.println("echo:" + str);  
	    				//System.out.println("服务端输入信息：");
	    				//String outStr = input.readLine();
	    				//out.println(outStr);
	    				out.println(1);
	    				out.flush();
	    				//outFile.write(str);
	    				writer.write(str+"\n");
	    				
	    				writer.flush();
	    			}  
	    		} */ 
	    	} 
	    	System.out.println("..........");
	    	
	    	
	    	bos.close(); 
	    	//outFile.close();
	    	writer.close();
	    	buf.close();
	    	client.close();  
	   }catch(Exception e){  
	       e.printStackTrace();  
	   }  
    }

    @Override  
    public void run() {  
    	execute(client);
    }  
}
