package main;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	DatagramSocket sendSocket;
	JTextArea ta = new JTextArea();
	JTextField tf = new JTextField();

	public Client(String title) throws SocketException {
		
		super(title);
		setSize(500,500);
		Container cc = getContentPane();
		JScrollPane sp = new JScrollPane(ta);
		cc.add(sp);
		cc.add(tf, "South");
		
		tf.addActionListener(new ActionListener() {			//监听回车
			public void actionPerformed(ActionEvent e) {
				
				ta.append(tf.getText() + '\n');
				ta.setSelectionEnd(ta.getText().length());	//将发送的信息整理到文本框
				byte[] buffer = tf.getText().getBytes();	//将发送的信息存入
				tf.setText(""); 							//将文本框清空
				
				try {
					sendSocket = new DatagramSocket();
					DatagramPacket dp = new DatagramPacket(buffer, buffer.length, 
							InetAddress.getByName("127.0.0.1"), 12306);
					sendSocket.send(dp);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}	
		});
	}

	public static void main(String[] args) throws IOException {
		Client client = new Client("客户端");
		client.setVisible(true);
	}

}
