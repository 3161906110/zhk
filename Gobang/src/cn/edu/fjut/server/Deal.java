package cn.edu.fjut.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

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
		if(Server.mapUser.get(mainSocket)==null) {
			Server.mapUser.put(mainSocket,str);
			return ;
		}
		if(str.equals("�����ս")) {
			Server.visitList(mainSocket, true);
			Socket socket=Server.map.get(mainSocket);
			if(socket!=null) {
			write(socket,Server.mapUser.get(mainSocket)+"���뷿��");
			write(mainSocket,Server.mapUser.get(socket)+"���뷿��");
			}	
			else{
				write(mainSocket,"�ȴ�����");
			}
		}
		else if(str.equals("��ʼ��Ϸ")) {
			Socket socket=Server.map.get(mainSocket);		
			Server.mapStatus.put(mainSocket, "yes");
			if(socket!=null) {
				if(Server.mapStatus.get(socket).equals("no")) {
					write(mainSocket,"���ֻ�û��׼����");
				}
				if(Server.mapStatus.get(socket).equals("yes")) {
					Random ra =new Random();
					int x=ra.nextInt(2);
					if(x==0) {
					 write(mainSocket,"���Ǻ�������");
					 write(socket,"���ǰ������");
					}
					else {
				     write(socket,"���Ǻ�������");
					 write(mainSocket,"���ǰ������");
					}
				}
			}
			else {
				write(mainSocket,"�ȴ�����");
			}
		}
		else if(str.equals("win")) {
		  Socket socket=Server.map.get(mainSocket);
		  if(socket!=null) {
			  write(socket, "������");
			  write(socket,"-");
			  Server.mapStatus.put(socket, "no"); 
		  }
		  write(mainSocket, "��Ӯ��");
		  write(mainSocket,"+");
		  Server.mapStatus.put(mainSocket, "no"); 
		}
		else {
			if(str.equals("�뿪����")) {
				Socket socket=Server.map.get(mainSocket);
				if(socket!=null) {
					if(Server.mapStatus.get(mainSocket).equals("yes")&&Server.mapStatus.get(socket).equals("yes")) {
						write(socket, "����ʤ");
						write(socket,"+");
						write(mainSocket,"-");
					}
				Server.map.put(socket, null);
				Server.mapStatus.put(socket, "no");
				str=Server.mapUser.get(mainSocket)+str;
				write(socket,str);
				}
				Server.map.put(mainSocket, null);
				Server.mapStatus.put(mainSocket, "no");
				Server.visitList(mainSocket, false);
				try {
					mainSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(Server.map.get(mainSocket)!=null)
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
