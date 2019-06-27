package cn.edu.fjut.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import cn.edu.fjut.frame.Frame;
import cn.edu.fjut.ui.PlayerUi;

public class Client {
	 public Socket socket = null;
	 public Client() {
	  try {
		socket = new Socket("192.168.13.101", 9999);
		//System.out.println("�������Ϸ�����");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		socket=null;
		System.out.println("���ӷ�����ʧ��");
	}
	  //System.out.println(socket);
  }
  
  //���������������
  public void write(String msg) {
	  //System.out.println(msg);
		PrintWriter out=null;
	  try {
		out = new PrintWriter(socket.getOutputStream());
		out.println(msg);
		out.flush();
		//System.out.print(msg);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//out.close();
	}
	
  }
  
 
}
