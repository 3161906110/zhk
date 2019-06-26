package cn.edu.fjut.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import cn.edu.fjut.frame.Frame;

public class ClientRead implements Runnable{
   private Socket socket;
   private Frame frame;
   public ClientRead(Socket socket,Frame frame) {
	   this.socket=socket;
	   this.frame=frame;
   }
   public void run() {
	   BufferedReader in=null;
	   try {
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		while(true) {
			String str=in.readLine();
			frame.deal(str);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			if(in!=null)
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   }
   
   
}
