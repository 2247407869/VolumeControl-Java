package main;

import java.io.IOException;
import java.net.*;

public class Server {


	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		DatagramSocket receiveSocket = new DatagramSocket(12306);			//新建socket
		
		byte[] buffer = new byte[1024];
		
		while(true) {
		
			DatagramPacket dp = new DatagramPacket(buffer, 1024);			//定义数据包长度
			receiveSocket.receive(dp);										//接受数据包
		
			String dataStr = new String(dp.getData(),0,dp.getLength());		//byte转string,0是为了置零缓冲区
			String ip = dp.getAddress().getHostAddress();
		
			System.out.println(dataStr);	
		
			if (dataStr.equals("."))										//java不能用==判断字符串相等
				Runtime.getRuntime().exec("cscript vol.vbs");				//调用静音vbs
		
			System.out.println("IP地址："+ip+"数据是"+dataStr);					//获取客户端的ip和数据
		
		
		}

	}

}
