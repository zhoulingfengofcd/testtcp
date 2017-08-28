package com.qingting.client;

import java.io.BufferedOutputStream;
import java.io.InputStream;

public class TxThread{
	private InputStream is;
	private BufferedOutputStream bos;
	public TxThread(final InputStream is,BufferedOutputStream bos){
		this.is=is;
		this.bos=bos;
	}
	public void runFunc()  {  
		TestClient.sendConn(is,bos);//连接
        
        int count=0;
        long tmp=5l;
        String string=null;
        byte[] bytes=new byte[2];
        
        
        int p=1;
    	byte byte1=0;
    	byte byte2=0;
        
    	
    	
    	
    	/*sendPublicGet(is,bos);//设备主动获取影子文档
    	waitAck(is,bos,new byte[]{64,2,0,10});
    	
    	byte[] tempBytes=waitReceive(bytes,is,"method","control","payload","status","success","metadata","timestamp");//判断响应
    	string=new String(tempBytes);
    	int i =string.indexOf("version");
    	//System.out.println("版本字段位置："+i);
    	int j = string.indexOf(":", i);
    	int k = string.indexOf(",", j);
    	String substring = string.substring(j+1, k);
    	tmp=Long.parseLong(substring);
    	System.out.println("服务器影子文档版本:"+tmp);
    	//解析包ID
    	p=1;byte1=0;byte2=0;
    	System.out.println("返回的字节");
    	for (byte b : tempBytes) {
			System.out.print((int)(b&0xff)+" ");
		}
    	if(tempBytes[0]==50){
    		while( (tempBytes[p] & 0x80)!=0 ){
    			++p;
    		}
    		int length=( (tempBytes[++p]<<8) | tempBytes[++p] );
    		System.out.println("length:"+length);
    		byte1=tempBytes[p+length+1];
    		byte2=tempBytes[p+length+2];
    	}else if(tempBytes[0]==58){
    		while( (tempBytes[p] & 0x80)!=0 ){
    			++p;
    		}
    		int length=( (tempBytes[++p]<<8) | tempBytes[++p] );
    		byte1=tempBytes[p+length+1];
    		byte2=tempBytes[p+length+2];
    	}else{
    		System.out.println("不能识别的协议数据.");
    	}
    	System.out.println("包ID:"+(int)(byte1&0xff)+(int)(byte2&0xff));
    	sendPublicAck(is,bos,byte1,byte2);//应答
    	
    	System.out.println("\n------------------------1-------------------------\n");*/
    	
    	
        while(true){
        	//Thread.sleep(2000);
        	/*//sendPublic(is,bos);//推送消息
        	//sendPingreq(is,bos);//发送心跳包
        	
        	count++;
        	if(count==2){
        		//Thread.sleep(1000);
        		
        		
        		sendClose(is,bos);
        		
				sk=new Socket(InetAddress.getByName(ipAddr),  
				        Integer.parseInt(port));  
				//输入信息流
				is=sk.getInputStream(); 
				//输出信息流  
				os=sk.getOutputStream();
				bos=new BufferedOutputStream(os);
        		sendConn(is,bos);
        	}
        	if(count==5)
        		while(true);*/
        	
        	
        	
        	TestClient.sendPublic(is,bos);//推送消息
        	TestClient.sendPingreq(is,bos);//发送心跳包
        	
        	
        	
        	
        	TestClient.sendPublicGet(is,bos);//设备主动获取影子文档
        	TestClient.waitAck(is,bos,new byte[]{64,2,0,10});
        	
        	byte[] tempBytes=TestClient.waitReceive(is,"method","control","payload","status","success","metadata","timestamp");//判断响应
        	string=new String(tempBytes);
        	int i =string.indexOf("version");
        	//System.out.println("版本字段位置："+i);
        	int j = string.indexOf(":", i);
        	int k = string.indexOf(",", j);
        	String substring = string.substring(j+1, k);
        	tmp=Long.parseLong(substring);
        	System.out.println("服务器影子文档版本:"+tmp);
        	//解析包ID
        	p=1;byte1=0;byte2=0;
        	System.out.println("返回的字节");
        	for (byte b : tempBytes) {
				System.out.print((int)(b&0xff)+" ");
			}
        	if(tempBytes[0]==50){
        		while( (tempBytes[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes[++p]<<8) | tempBytes[++p] );
        		System.out.println("length:"+length);
        		byte1=tempBytes[p+length+1];
        		byte2=tempBytes[p+length+2];
        	}else if(tempBytes[0]==58){
        		while( (tempBytes[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes[++p]<<8) | tempBytes[++p] );
        		byte1=tempBytes[p+length+1];
        		byte2=tempBytes[p+length+2];
        	}else{
        		System.out.println("不能识别的协议数据.");
        	}
        	System.out.println("包ID:"+(int)(byte1&0xff)+(int)(byte2&0xff));
        	TestClient.sendPublicAck(is,bos,byte1,byte2);//应答
        	
        	System.out.println("\n------------------------1-------------------------\n");
        	
        	
        	/*sendPublicUpdate(is,bos,++tmp);//设备上报最新状态
        	waitAck(is,bos,new byte[]{64,2,0,10});
        	byte[] tempBytes2=waitReceive(bytes,is,"method","reply","status","success");//判断响应
        	
        	//解析包ID
        	p=1;byte1=0;byte2=0;
        	if(tempBytes2[0]==50){
        		while( (tempBytes2[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes2[++p]<<8) | tempBytes2[++p] );
        		byte1=tempBytes2[p+length+1];
        		byte2=tempBytes2[p+length+2];
        	}else if(tempBytes2[0]==58){
        		while( (tempBytes2[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes2[++p]<<8) | tempBytes2[++p] );
        		byte1=tempBytes2[p+length+1];
        		byte2=tempBytes2[p+length+2];
        	}else{
        		System.out.println("不能识别的协议数据.");
        	}
        	
        	sendPublicAck(is,bos,byte1,byte2);//应答
        	
        	System.out.println("\n------------------------2-------------------------\n");
        	
        	
        	
        	sendPublicDesired(is,bos,++tmp);//服务器主动改变设备状态
        	waitAck(is,bos,new byte[]{64,2,0,10});
        	byte[] tempBytes3=waitReceive(bytes,is,"method","control","payload","status","success","metadata","timestamp");//判断响应
        	
        	//解析包ID
        	p=1;byte1=0;byte2=0;
        	if(tempBytes3[0]==50){
        		while( (tempBytes3[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes3[++p]<<8) | tempBytes3[++p] );
        		byte1=tempBytes3[p+length+1];
        		byte2=tempBytes3[p+length+2];
        	}else if(tempBytes3[0]==58){
        		while( (tempBytes3[p] & 0x80)!=0 ){
        			++p;
        		}
        		int length=( (tempBytes3[++p]<<8) | tempBytes3[++p] );
        		byte1=tempBytes3[p+length+1];
        		byte2=tempBytes3[p+length+2];
        	}else{
        		System.out.println("不能识别的协议数据.");
        	}
        	
        	sendPublicAck(is,bos,byte1,byte2);//应答
        	sendPublicUpdateAck(is,bos,++tmp);//状态已经改变，汇报
        	waitAck(is,bos,new byte[]{64,2,0,10});
        	
        	System.out.println("\n------------------------3-------------------------\n");*/
        }
	}  
}
