package cn.edu.fjut.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * socket������
 * 
 * @author Administrator
 *
 */
public class Deal implements Runnable {
	private Socket mainSocket;

	public Deal(Socket socket) {
		this.mainSocket = socket;
	}

	public void run() {
	  BufferedReader in=null;
	try {
		in = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
		while (true) {
			//if(in.readLine()!=null)
           //String choose=in.readLine();
			String str=in.readLine();
           //System.out.println(str);
			dealStr(str);
          }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("�ͻ����˳�");
		Socket socket=Server.map.get(mainSocket);
		if(socket!=null)
			Server.map.put(socket, null);
		for(int i=0;i<Server.socketList.size();i++)
			if(Server.socketList.get(i)==mainSocket) {
				Server.socketList.remove(i);
			}
	}finally {
    	try {
    		if(in!=null)
			in.close();
    	    mainSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
  }
	//�߼�����
	private void dealStr(String str) {
		System.out.println(str);
		if(str.equals("�����ս")) {
			boolean isOk=false;
			for(Socket socket : Server.socketList) {
				if(Server.map.get(socket)==null&&mainSocket!=socket) {
					Server.map.put(mainSocket, socket);
					Server.map.put(socket, mainSocket);
					write(mainSocket,"��ս��ʼ,���Ǻ�ɫ��");
					write(socket,"��ս��ʼ,���ǰ�ɫ��");
					isOk=true;
				}
			}
			if(isOk==false) {
				write(mainSocket,"�ȴ�����");
			}
		}
		else {
			write(Server.map.get(mainSocket),str);
		}
	}
	
	//д���ݸ��ͻ���
	public void write(Socket socket,String str) {
		PrintWriter out=null;
		  try {
			out = new PrintWriter(socket.getOutputStream());
			out.println(str);
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
