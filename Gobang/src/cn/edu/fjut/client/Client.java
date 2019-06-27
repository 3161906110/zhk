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
		//System.out.println("已连接上服务器");
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		socket=null;
		System.out.println("连接服务器失败");
	}
	  //System.out.println(socket);
  }
  
  //向服务器发送数据
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
