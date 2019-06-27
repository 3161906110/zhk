package cn.edu.fjut.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器类
 * 
 * @author Administrator
 *
 */
public class Server {
	static List<Socket> socketList = new ArrayList();
	static ServerSocket serverSocket = null;
	static Map<Socket, Socket> map = new HashMap<Socket, Socket>();
	static Map<Socket, String> mapUser = new HashMap<Socket, String>();
	static Map<Socket, String> mapStatus = new HashMap<Socket, String>();

	public Server() {
		try {
			serverSocket = new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 对list访问的同步
	public static synchronized void visitList(Socket mainSocket, boolean is) {
		if (is == true) {
			for (Socket socket : Server.socketList) {
				if (Server.map.get(socket) == socket && mainSocket != socket) {
					Server.map.put(mainSocket, socket);
					Server.map.put(socket, mainSocket);
					break;
				}
			}
		}
		else {
			for(int i=0;i<Server.socketList.size();i++)
				if(Server.socketList.get(i)==mainSocket) {
					Server.socketList.remove(i);
					break;
				}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("****************服务器启动********************");
		Server server = new Server();
		int count = 0;
		while (true) {
			Socket socket = null;
			try {
				System.out.println("服务器等待连接");
				socket = serverSocket.accept();
				count++;
				System.out.println("第" + count + "个客户以连接");
				socketList.add(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(socket, null);
			mapUser.put(socket, null);
			mapStatus.put(socket, "no");
			Deal deal = new Deal(socket);
			Thread thread = new Thread(deal);
			thread.start();
		}

	}
}
