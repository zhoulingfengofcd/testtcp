package com.qingting.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Calendar;

import com.qingting.server.ServerThread;

public class TestClient {
	private static int clientID=1; 
	public static void main(String[] str) {  
		String ipAddr="192.168.10.20";
		//String port="50021";
		//String ipAddr="39.108.52.201";
		String port="40050";
      
        Socket sk=null; 
        //输入信息流
        InputStream is=null; 
        //输出信息流  
        BufferedOutputStream bos=null;
        try {  
            sk=new Socket(InetAddress.getByName(ipAddr),  
                    Integer.parseInt(port));  
            //输入信息流
            is=sk.getInputStream(); 
            //输出信息流  
            bos=new BufferedOutputStream(sk.getOutputStream());
            
            sendConnTest(is,bos);//连接
            
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
            	
            	/*byte[] tempBytes3=waitReceive(is,"method","control","payload","status","success","metadata","timestamp");//判断响应
            	System.out.println("收到影子文档");
            	while(true);*/
            	
            	//sendPublic(is,bos);//推送消息
            	
            	sendPublicVerify(is, bos);//推送消息
            	//sendPingreq(is,bos);//发送心跳包
            	
            	//while(true);
            	/*sendPublicCurrent(is,bos);//获取实时信息
            	waitAck(is,bos,new byte[]{64,2,0,10});
            	byte[] tempByte=waitReceive(is,new byte[]{'c','u','r','r','e','n','t','/'});
            	
            	//解析包ID
            	p=1;byte1=0;byte2=0;
            	System.out.println("实时信息返回的字节：");
            	for (byte b : tempByte) {
					System.out.print((int)(b&0xff)+" ");
				}
            	if(tempByte[0]==50){
            		while( (tempByte[p] & 0x80)!=0 ){
            			++p;
            		}
            		int length=( (tempByte[++p]<<8) | tempByte[++p] );
            		System.out.println("length:"+length);
            		byte1=tempByte[p+length+1];
            		byte2=tempByte[p+length+2];
            	}else if(tempByte[0]==58){
            		while( (tempByte[p] & 0x80)!=0 ){
            			++p;
            		}
            		int length=( (tempByte[++p]<<8) | tempByte[++p] );
            		byte1=tempByte[p+length+1];
            		byte2=tempByte[p+length+2];
            	}else{
            		System.out.println("不能识别的协议数据.");
            	}
            	System.out.println("包ID:"+(int)(byte1&0xff)+(int)(byte2&0xff));
            	//sendPublicAck(is,bos,byte1,byte2);//应答  
            	
            	
            	
            	sendPublicGet(is,bos);//设备主动获取影子文档
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
        } catch (Exception e){  
            e.printStackTrace();  
        } finally {
        	try {
        		is.close();
        		bos.close();
				sk.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		} 
		/*String string=
				"{"+
					"\"method\": \"update\""+
					"\"state\": {"+
						"\"reported\": {"+
							"\"color\": \"red\""+
						"}"+
					"}"+
					"\"version\": 1"+
				"}";
		byte[] payload = string.getBytes();
		System.out.println("payload_length:"+payload.length);
		System.out.println("payload:");
		for(int i=0;i<payload.length;i++){
			System.out.print(payload[i]+" ");
		}*/
    }
	
	public static void waitAck(InputStream is,BufferedOutputStream bos,byte[] bytes){
		String str=null;
		System.out.println("------------准备接收MQTT协议应答------------");
		
		boolean flag=false;
		int temp=0;
		do{
			byte[] b={};
			try{
				int count = 0;
				while (count == 0) {
					count = is.available();
				}
				b = new byte[count];
				is.read(b);
				for (byte c : b) {
					System.out.print((int)(c&0xff)+" ");
				}
				str=new String(b);
				System.out.println(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(str.contains(new String(bytes)))
				flag=true;
			/*for(int i=0;i<string.length;i++){
				if(str.contains(string[i])){//包含
					temp++;
					System.out.print("包含:"+string[i]);
				}else{//不包含
					System.out.print("不包含:"+string[i]+" ");
				}
			}
			if(temp==string.length)
				flag=true;//全部包含flag=true
			if(flag){
				bytes[0]=b[16];
				bytes[1]=b[17];
			}*/
		}while(!flag);
		System.out.println("------------MQTT协议应答结束------------");
		//return str;
	}
	
	/**
	 * 发送连接
	 */
	
	public static void sendConnOfficial(InputStream is,BufferedOutputStream bos){
		
		try{
			/*//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		12,
            		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		'0',
            		'0',
            		'0',
            		'0',
            		'1',
            		
            		0,//length
            		1,
            		'a',//will topic
            		
            		0,//length
            		1,
            		'b',//will content
            		
            		0,//length
            		10,
            		//username
            		'Y','B','h','B','W','l','j','m','m','b',
            		
            		0,//length
            		10,
            		//password
            		'E','E','5','V','6','O','X','A','z','j'
            };*/
			/*//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		12,
            		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		'0',
            		'0',
            		'0',
            		'0',
            		'2',
            		
            		0,//length
            		1,
            		'a',//will topic
            		
            		0,//length
            		1,
            		'b',//will content
            		
            		0,//length
            		10,
            		//username
            		'H','F','g','Y','6','q','C','Q','6','x',
            		0,//length
            		10,
            		//password
            		'Y','D','D','x','q','h','n','n','c','t'
            };*/
			/*//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		12,
            		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		'0',
            		'0',
            		'0',
            		'0',
            		'5',
            		
            		0,//length
            		1,
            		'a',//will topic
            		
            		0,//length
            		1,
            		'b',//will content
            		
            		0,//length
            		10,
            		//username
            		'e','8','S','O','a','P','5','A','9','m',
            		0,//length
            		10,
            		//password
            		'I','n','C','c','Y','X','N','m','v','g'
            };*/
			//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		50,
            		//有效载荷
            		0,//length
            		12,
            		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		'0',
            		'0',
            		'0',
            		'0',
            		'6',
            		
            		0,//length
            		1,
            		'a',//will topic
            		
            		0,//length
            		1,
            		'b',//will content
            		
            		0,//length
            		10,
            		//username
            		'F','t','p','5','D','F','R','V','t','L',
            		0,//length
            		10,
            		//password
            		'e','7','W','v','k','2','I','z','A','8'
            };
            //修改剩余长度
			ch[1]=(byte)(ch.length-2);
            
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
	/**
	 * 发送连接
	 */
	
	public static void sendConnTest(InputStream is,BufferedOutputStream bos){
		//clientID++;
        byte ge=(byte)(( 0xFF & (clientID%10) )+48);
        byte shi=(byte)(( 0xFF & (clientID%100/10) )+48);
        byte bai=(byte)(( 0xFF & (clientID%1000/100) )+48);
        byte qian=(byte)(( 0xFF & (clientID%10000/1000) )+48);
        byte wan=(byte)(( 0xFF & (clientID%100000/10000) )+48);
		try{
			//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		12,
            		'd',//client ID(必须)(版本)
            		'e',//--ascii码8
            		'f',//(年份)
            		'a',
            		'u',//(批次)
            		'l',
            		't',//(ID序列)
            		' ',
            		'i',
            		'd',
            		' ',
            		'0',
            		
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
	
	/**
	 * 发送连接
	 */
	
	public static void sendConn(InputStream is,BufferedOutputStream bos){
		//clientID++;
        byte ge=(byte)(( 0xFF & (clientID%10) )+48);
        byte shi=(byte)(( 0xFF & (clientID%100/10) )+48);
        byte bai=(byte)(( 0xFF & (clientID%1000/100) )+48);
        byte qian=(byte)(( 0xFF & (clientID%10000/1000) )+48);
        byte wan=(byte)(( 0xFF & (clientID%100000/10000) )+48);
		try{
			//CONNECT
            byte[] ch={
            		//固定头部
            		16,//Connect 
            		42,//remain length
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
            		12,
            		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		wan,
            		qian,
            		bai,
            		shi,
            		ge,
            		
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
	/**
	 *等待接收信息（字节位置定义的信息）
	 */
	public static byte[] waitReceive(InputStream is,byte[] tempByte){
		String str=null;
		System.out.println("------------准备接收服务器推送的消息，应答------------");
		
		boolean flag=false;
		int temp=0;
		byte[] b={};
		do{
			try{
				int count = 0;
				while (count == 0) {
					count = is.available();
				}
				b = new byte[count];
				is.read(b);
				for (byte c : b) {
					System.out.print((int)(c&0xff)+" ");
				}
				str=new String(b);
				System.out.println(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			/*for(int i=0;i<string.length;i++){
				if(str.contains(string[i])){//包含
					temp++;
					System.out.print("包含:"+string[i]);
				}else{//不包含
					System.out.print("不包含:"+string[i]+" ");
				}
			}
			if(temp==string.length)
				flag=true;//全部包含flag=true*/
			System.out.println("tempByte[0]:"+tempByte[0]);
			if(b.length>1){
				for(int i=0;i<b.length;i++){
					if( (b[i]&0xFF)==(tempByte[0]&0xFF) && (i+tempByte.length)<b.length ){
						System.out.println("找到相等的第一次字节"+b[i]);
						for(int j=0;j<tempByte.length;j++){
							if( (b[i+j]&0xFF)!=(tempByte[j]&0xFF) )
								break;
							if(j==(tempByte.length-1)){
								System.out.println("找到要求的字节");
								flag=true;
								break;
							}
						}
					}
					if(flag==true){
						break;
					}
				}
			}
		}while(!flag);
		System.out.println("------------应答结束------------");
		
		return b;
	}
	/**
	 *等待接收信息
	 */
	public static byte[] waitReceive(InputStream is,String ...string){
		String str=null;
		System.out.println("------------准备接收应答------------");
		
		boolean flag=false;
		int temp=0;
		byte[] b={};
		do{
			try{
				int count = 0;
				while (count == 0) {
					count = is.available();
				}
				b = new byte[count];
				is.read(b);
				for (byte c : b) {
					System.out.print((int)(c&0xff)+" ");
				}
				str=new String(b);
				System.out.println(str);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			for(int i=0;i<string.length;i++){
				if(str.contains(string[i])){//包含
					temp++;
					System.out.print("包含:"+string[i]);
				}else{//不包含
					System.out.print("不包含:"+string[i]+" ");
				}
			}
			if(temp==string.length)
				flag=true;//全部包含flag=true
		}while(!flag);
		System.out.println("------------应答结束------------");
		
		return b;
	}
	/**
	 * 推送消息 
	 */
	public static void sendPublic(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
					
	        		//固定头部
	        		50,//publish 
	        		32,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		9,
	        		'm',//topic name
	        		'o',
	        		'n',
	        		'i',
	        		't',
	        		'o',
	        		'r',
	        		'/',
	        		'1',
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
			Calendar time=Calendar.getInstance();
			
			int year = time.get(Calendar.YEAR);    //获取年
		    int month = time.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
		    int day = time.get(Calendar.DAY_OF_MONTH);    //获取当前天数
		   
		    int hour = time.get(Calendar.HOUR_OF_DAY);       //获取当前小时
		    int min = time.get(Calendar.MINUTE);          //获取当前分钟
		    int xx = time.get(Calendar.SECOND);          //获取当前秒
			
		    int start=15;
		    
			publish[start]=(byte)(year%100);
			publish[start+1]=(byte)month;
			publish[start+2]=(byte)day;
			publish[start+3]=(byte)hour;
			publish[start+4]=(byte)min;
			publish[start+5]=(byte)xx;
			
			for (byte b : publish) {
				System.out.print(b+" ");
			}
			
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
	/**
	 * 推送消息 
	 */
	public static void sendPublicVerify(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
					
	        		//固定头部
	        		50,//publish 
	        		32,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		9,
	        		'm',//topic name
	        		'o',
	        		'n',
	        		'i',
	        		't',
	        		'o',
	        		'r',
	        		'/',
	        		'1',
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
	        		23,//--humidity
	        		
	        		
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
	        		23,//--humidity
	        		
	        		0,//和校验
	        		0
	        };
			Calendar time=Calendar.getInstance();
			
			int year = time.get(Calendar.YEAR);    //获取年
		    int month = time.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
		    int day = time.get(Calendar.DAY_OF_MONTH);    //获取当前天数
		   
		    int hour = time.get(Calendar.HOUR_OF_DAY);       //获取当前小时
		    int min = time.get(Calendar.MINUTE);          //获取当前分钟
		    int xx = time.get(Calendar.SECOND);          //获取当前秒
			
		    int start=15;
		    
			publish[start]=(byte)(year%100);
			publish[start+1]=(byte)month;
			publish[start+2]=(byte)day;
			publish[start+3]=(byte)hour;
			publish[start+4]=(byte)min;
			publish[start+5]=(byte)xx;
			//修改剩余长度
			publish[1]=(byte)(publish.length-2);
			
			int sum=0;
			
			for (int i = start; i < publish.length-2; i++) {
				sum+=publish[i];
			}
			System.out.println("sum="+sum);
			publish[publish.length-2] = (byte)((sum >>> 8) & 0xFF);
			publish[publish.length-1] = (byte)(sum & 0xFF);
			
			for (byte b : publish) {
				System.out.print((b&0xFF)+" ");
			}
			
			
			
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
	/**
	 * 推送获取实时消息
	 */
	public static void sendPublicCurrent(InputStream is,BufferedOutputStream bos){
		try{
			byte[] publish={
					
	        		//固定头部
	        		50,//publish 
	        		11,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		7,
	        		'c',//topic name
	        		'u',
	        		'r',
	        		'r',
	        		'e',
	        		'n',
	        		't',
	        		0,//message id
	        		10
	        		
	        		//payload
	        		
	        };
			
			
			for (byte b : publish) {
				System.out.print(b+" ");
			}
			
	        bos.write(publish);
	        bos.flush();
	        
	       
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 推送应答
	 */
	public static void sendPublicAck(InputStream is,BufferedOutputStream bos,byte byte1,byte byte2){
		System.out.println("推送应答");
		try{
			byte[] publish={
	        		//固定头部
	        		(byte)64,//publishAck 
	        		2,//remain length
	        		byte1,
	        		byte2
	        };
	        bos.write(publish);
	        bos.flush();
			
	        System.out.println("推送应答结束.往服务器推送的回复为:");
	        for (byte b : publish) {
	        	System.out.print(b+" ");
			}
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 发送心跳包
	 */
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
	/**
	 * 设备上报最新状态
	 */
	public static void sendPublicUpdate(InputStream is,BufferedOutputStream bos,Long version){
		System.out.println("设备上报最新状态");
		try{
			byte[] subscribe={
					//固定头部
	        		50,//publish 
	        		81,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		6,
	        		'u',//topic name
	        		'p',
	        		'd',
	        		'a',
	        		't',
	        		'e',
	        		0,//message id
	        		10
	        		
	        		//payload
	        		
	        };
			/*String str=
				"{"+
					"\"method\": \"update\","+
					"\"state\": {"+
						"\"reported\": {"+
							"\"color\": \"red\""+
						"}"+
					"},"+
					"\"version\": 1"+
				"}";*/
			String str=
				"{"+
						"\"method\": \"update\","+
						"\"state\": {"+
							"\"reported\": {"+
								"\"color\":\""+version+"\""+
							"}"+
						"},"+
						"\"version\": "+version+""+
					"}";
			byte[] payload = str.getBytes();
			subscribe[1]=(byte)(subscribe.length-2+payload.length);
			
			
	        bos.write(addBytes(subscribe,payload));
	        bos.flush();
	        
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 服务器主动改变设备状态
	 */
	public static void sendPublicDesired(InputStream is,BufferedOutputStream bos,Long version){
		System.out.println("服务器主动改变设备状态");
		try{
			byte[] subscribe={
					//固定头部
	        		50,//publish 
	        		81,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		6,
	        		'u',//topic name
	        		'p',
	        		'd',
	        		'a',
	        		't',
	        		'e',
	        		0,//message id
	        		10
	        		
	        		//payload
	        		
	        };
			/*String str=
				"{"+
					"\"method\": \"update\","+
					"\"state\": {"+
						"\"reported\": {"+
							"\"color\": \"red\""+
						"}"+
					"},"+
					"\"version\": 1"+
				"}";*/
			String str=
				"{"+
						"\"method\": \"update\","+
						"\"state\": {"+
							"\"desired\": {"+
								"\"color\":\""+version+"\""+
							"}"+
						"},"+
						"\"version\": "+version+""+
					"}";
			byte[] payload = str.getBytes();
			subscribe[1]=(byte)(subscribe.length-2+payload.length);
			
			
	        bos.write(addBytes(subscribe,payload));
	        bos.flush();
	        
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 获取影子文档
	 */
	public static void sendPublicGet(InputStream is,BufferedOutputStream bos){
		System.out.println("获取影子文档");
		try{
			byte[] subscribe={
					//固定头部
	        		50,//publish 
	        		81,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		6,
	        		'u',//topic name
	        		'p',
	        		'd',
	        		'a',
	        		't',
	        		'e',
	        		0,//message id
	        		10
	        		
	        		//payload
	        		
	        };
			String str=
				"{"+
					"\"method\": \"get\""+
				"}";
			byte[] payload = str.getBytes();
			subscribe[1]=(byte)(subscribe.length-2+payload.length);
			
			
	        bos.write(addBytes(subscribe,payload));
	        bos.flush();
	        
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 状态已经改变，汇报
	 */
	public static void sendPublicUpdateAck(InputStream is,BufferedOutputStream bos,Long version){
		System.out.println("状态已经改变，汇报");
		try{
			byte[] subscribe={
					//固定头部
	        		50,//publish 
	        		81,//remain length
	        		
	        		//可变头部
	        		0,//topic name length
	        		6,
	        		'u',//topic name
	        		'p',
	        		'd',
	        		'a',
	        		't',
	        		'e',
	        		0,//message id
	        		10
	        		
	        		//payload
	        		
	        };
			/*String str=
				"{"+
					"\"method\": \"update\","+
					"\"state\": {"+
						"\"reported\": {"+
							"\"color\": \"red\""+
						"}"+
					"},"+
					"\"version\": 1"+
				"}";*/
			String str=
				"{"+
						"\"method\": \"update\","+
						"\"state\": {"+
							"\"desired\": null"+
						"},"+
						"\"version\": "+version+""+
					"}";
			byte[] payload = str.getBytes();
			subscribe[1]=(byte)(subscribe.length-2+payload.length);
			
			
	        bos.write(addBytes(subscribe,payload));
	        bos.flush();
	        
	       /* System.out.print("状态已经改变，汇报.");
	        
	        int count = 0;
			while (count == 0) {
				count = is.available();
			}
			byte[] b = new byte[count];
			is.read(b);
			for (byte c : b) {
				System.out.print(c+" ");
			}
			System.out.println(new String(b));
	        System.out.println("状态已经改变，汇报....");*/
		} catch (Exception e){  
            e.printStackTrace();  
        } 
	}
	/**
	 * 订阅主题
	 */
	public static void sendSubscribe(InputStream is,BufferedOutputStream bos){
		try{
			byte[] subscribe={
	        		//固定头部
	        		(byte)130,//subscribe
	        		21,//remain length
	        		//可变头部
	        		0, //报文标示符-即包ID
	        		10,
	        		//payload
	        		0, //主题过滤器长度
	        		16,
	        		'g',
	        		'e',
	        		't',
	        		'/',
	        		'2',//client ID(必须)(版本)
            		'1',//--ascii码8
            		'1',//(年份)
            		'7',
            		'0',//(批次)
            		'1',
            		'0',//(ID序列)
            		'0',
            		'0',
            		'0',
            		'2',
            		'4',
            		1//服务质量要求（Requested QoS）
	        };
	        bos.write(subscribe);
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
	
	/**
	 * 关闭连接
	 */
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
	/** 
	 *  
	 * @param data1 
	 * @param data2 
	 * @return data1 与 data2拼接的结果 
	 */  
	public static byte[] addBytes(byte[] data1, byte[] data2) {  
	    byte[] data3 = new byte[data1.length + data2.length];  
	    System.arraycopy(data1, 0, data3, 0, data1.length);  
	    System.arraycopy(data2, 0, data3, data1.length, data2.length);  
	    return data3;  
	  
	} 
}
