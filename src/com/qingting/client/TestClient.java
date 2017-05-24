package com.qingting.client;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TestClient {
	public static void main(String[] str) {  
		String ipAddr="192.168.10.20";
		//String ipAddr="123.207.38.80";
		String port="8088";
        /*if(args.length<2)  
        {  
            System.out.println("注意：请添加参数：<Server Ip> <Server Port>");  
            return;  
        } */ 
        Socket sk=null;  
        try {  
            sk=new Socket(InetAddress.getByName(ipAddr),  
                    Integer.parseInt(port));  
            InputStream ips=sk.getInputStream();  
            OutputStream ops=sk.getOutputStream();  
            //从服务器读取信息的包装类  
            //BufferedReader bfr=new BufferedReader(  
            //        new InputStreamReader(ips));  
            //输出信息流  
            PrintWriter pw=new PrintWriter(ops,true);  
            BufferedOutputStream bos=new BufferedOutputStream(ops);
            
            
            /*while(true)  
            {  
                String strWord =keyBoard.readLine(); 
                
                //消息发送到服务器端  
                //pw.println(strWord);  
                //char[] ch={0,1,1,2};
                //pw.write(ch);
                if(strWord.equalsIgnoreCase("quit"))  
                {  
                    System.out.println("客户端退出！");  
                    break;  
                }  
                System.out.println(bfr.readLine());  
            }*/  
            
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
            //ops.write(ch);
            //ops.flush();
            //CONNACK
            System.out.println("准备接收连接应答信息.");
            int result=0;
            while(true){
            	if((result =ips.read())!=-1){
            		System.out.println("返回值："+result); 
            	}else{
            		break;
            	}
            };/**/
            System.out.println("连接应答结束，开始推送信息.");
            
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
            //ops.write(publish);
            //ops.write(-1);
            //ops.flush();
            
            //response
            /*result=0;
            while(true){
            	if((result =ips.read())!=-1&&result!=null){
            		System.out.println("返回值："+result); 
            	}else{
            		break;
            	}
            };*/
            System.out.println("准备接收推送应答信息.");
            while(true){
            	int res =ips.read();
            	System.out.println("返回值："+res); 
            	if(res==-1) break;
            };/**/
            System.out.println("接收推送应答信息结束.");
            //pw.close();  
            //bfr.close();  
            //keyBoard.close();
            
            sk.close();  
        } catch (Exception e){  
            e.printStackTrace();  
        }   
  
    } 
}
