package main;

import java.io.IOException;
import java.net.*;

public class Server {


	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		DatagramSocket receiveSocket = new DatagramSocket(12306);			//�½�socket
		
		byte[] buffer = new byte[1024];
		
		while(true) {
		
			DatagramPacket dp = new DatagramPacket(buffer, 1024);			//�������ݰ�����
			receiveSocket.receive(dp);										//�������ݰ�
		
			String dataStr = new String(dp.getData(),0,dp.getLength());		//byteתstring,0��Ϊ�����㻺����
			String ip = dp.getAddress().getHostAddress();
		
			System.out.println(dataStr);	
		
			if (dataStr.equals("."))										//java������==�ж��ַ������
				Runtime.getRuntime().exec("cscript vol.vbs");				//���þ���vbs
		
			System.out.println("IP��ַ��"+ip+"������"+dataStr);					//��ȡ�ͻ��˵�ip������
		
		
		}

	}

}
