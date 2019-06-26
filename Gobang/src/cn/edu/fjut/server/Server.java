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
 * ��������
 * @author Administrator
 *
 */
public class Server {
    static List<Socket> socketList= new ArrayList();
    static ServerSocket serverSocket=null;
    static Map<Socket,Socket> map=new HashMap<Socket,Socket>();
    public Server() {
    	try {
			serverSocket=new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("****************����������********************");
        Server server=new Server();
        int count=0;
        while(true) {
        	Socket socket=null;
        	try {
        		System.out.println("�������ȴ�����");
        		socket=serverSocket.accept();
        		count++;
        		System.out.println("��"+count+"���ͻ�������");
        		socketList.add(socket);
        	} catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        	map.put(socket, null);
        	Deal deal=new Deal(socket);
        	Thread thread=new Thread(deal);
        	thread.start();
        }
   
        
	}
}
