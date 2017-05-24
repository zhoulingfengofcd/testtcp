package com.qingting.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	public static void main(String[] args) throws IOException {
		//服务端在8086端口监听客户端请求的TCP连接  
        ServerSocket server = new ServerSocket(8088);  
        int count=0;
        Socket client = null;  
        boolean f = true;  
        while(f){  
            //等待客户端的连接，如果没有获取连接  
            client = server.accept();  
            count++;
            System.out.println("connect client,"+count);  
            //为每个客户端连接开启一个线程  
            new Thread(new ServerThread(client)).start();  
        }  
        server.close();  
	        
	}

}
