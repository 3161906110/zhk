package cn.edu.fjut.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import cn.edu.fjut.dao.UserDao;

/**
 * socket处理类
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
		System.out.println("客户端退出");
		Server.visitList(mainSocket, false);
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
	//逻辑处理
	private void dealStr(String str) {
		System.out.println(str);
		if(Server.mapUser.get(mainSocket)==null) {
			Server.mapUser.put(mainSocket,str);
			return ;
		}
		if(str.equals("网络对战")) {
			Server.visitList(mainSocket, true);
			Socket socket=Server.map.get(mainSocket);
			if(socket!=null) {
			write(socket,Server.mapUser.get(mainSocket)+"加入房间");
			write(mainSocket,Server.mapUser.get(socket)+"加入房间");
			}	
			else{
				write(mainSocket,"等待对手");
				Server.map.put(mainSocket, mainSocket);
			}
		}
		else if(str.equals("开始游戏")) {
			Socket socket=Server.map.get(mainSocket);		
			Server.mapStatus.put(mainSocket, "yes");
			if(socket!=null) {
				if(Server.mapStatus.get(socket).equals("no")) {
					write(mainSocket,"对手还没有准备好");
				}
				if(Server.mapStatus.get(socket).equals("yes")) {
					Random ra =new Random();
					int x=ra.nextInt(2);
					if(x==0) {
					 write(mainSocket,"你是黑棋先手");
					 write(socket,"你是白棋后手");
					}
					else {
				     write(socket,"你是黑棋先手");
					 write(mainSocket,"你是白棋后手");
					}
				}
			}
			else {
				write(mainSocket,"等待对手");
			}
		}
		else if(str.equals("win")) {
		  Socket socket=Server.map.get(mainSocket);
		  if(socket!=null) {
			  write(socket, "您输了");
			  write(socket,"-");
			  update(Server.mapUser.get(socket),-1);
			  Server.mapStatus.put(socket, "no"); 
		  }
		  write(mainSocket, "您赢了");
		  write(mainSocket,"+");
		  update(Server.mapUser.get(mainSocket),1);
		  Server.mapStatus.put(mainSocket, "no"); 
		}
		else {
			if(str.equals("离开房间")) {
				Socket socket=Server.map.get(mainSocket);
				if(socket!=null) {
					if(Server.mapStatus.get(mainSocket).equals("yes")&&Server.mapStatus.get(socket).equals("yes")) {
						write(socket, "您获胜");
						write(socket,"+");
						update(Server.mapUser.get(socket),1);
						write(mainSocket,"-");
						update(Server.mapUser.get(mainSocket),-1);
					}
				Server.map.put(socket, socket);
				Server.mapStatus.put(socket, "no");
				str=Server.mapUser.get(mainSocket)+str;
				write(socket,str);
				}
				Server.map.put(mainSocket, null);
				Server.mapStatus.put(mainSocket, "no");
			}
			if(Server.map.get(mainSocket)!=null)
			write(Server.map.get(mainSocket),str);
			
		}
	}
	
	//写数据给客户端
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
	
	//更新数据库
	private void update(String userName,int dv) {
		UserDao ud=new UserDao();
		ud.updateUser(userName, dv);
	}

}
