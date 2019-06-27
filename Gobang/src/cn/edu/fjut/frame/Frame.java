package cn.edu.fjut.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.fjut.chess.Chess;
import cn.edu.fjut.client.Client;
import cn.edu.fjut.listenner.FrameMouseListener;
import cn.edu.fjut.listenner.WebFrameMouseListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * 棋盘类
 * 
 * @author Administrator
 *
 */
public class Frame extends JFrame {

	private JPanel contentPane;
	private MouseListener fml;
	private JTextArea textArea;
	public Client client;
	public boolean isEnd=false;//是否结束
	public boolean isBlack=true;//黑棋先手
	public boolean myColor;//棋子颜色
	public boolean isInit;//是否正在对战
	public Chess[][] chess = new Chess[19][19];
	static int len = 25;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  */
	/**
	 * 初始化棋盘参数 Create the frame.
	 */
	public Frame(boolean isWeb,Client client) {
		this.isInit=false;
		this.setTitle("五子棋");
		this.setSize(700, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);// 默认居中
		//this.setDefaultCloseOperation(3);// 按下叉号默认关闭
		this.getContentPane().setBackground(new Color(240, 233, 217));
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u5F00\u59CB\u6E38\u620F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isInit==true) {
					return ;
				}
				if(client==null) {
					init();
				}
				else {
		    	write("开始游戏");
				}
			}
		});
		btnNewButton.setBounds(575, 76, 119, 23);
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(586, 109, 98, 332);
		textArea.setLineWrap(true);
		getContentPane().add(textArea);
		this.setVisible(true);
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				chess[i][j] = new Chess(i, j);
			}
		}
		if(isWeb==false) {
		fml = new FrameMouseListener(this);
		}
		else {
			fml = new WebFrameMouseListener(this);	
		}
		this.addMouseListener(fml);
        this.client=client;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              write("离开房间");
              close();
            }
          });
	}
	//初始化
    private void init() {
    	for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				chess[i][j].setColor(0);
			}
		}
    	isBlack=true;
    	isEnd=false;
    	repaint();
    }
    
    //关闭
    private void close() {
    	this.dispose();
    	try {
			client.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	// 绘制棋盘
	public void paint(Graphics g) {
		super.paint(g);
		drawChessTable(g);
		
	}

	public void drawChessTable(Graphics g) {
		for (int i = 0; i < 19; i++)
			g.drawLine(len, len + i * len, len + (19 - 1) * len, len + i * len);
		for (int j = 0; j < 19; j++)
			g.drawLine(len + j * len, len, len + j * len, len + (19 - 1) * len);
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				switch (chess[i][j].getColor()) {
				case -1:
					g.setColor(Color.BLACK);
					g.fillOval(chess[i][j].getX() - len/2+2, chess[i][j].getY()-len/2+2, len-5, len-5);
					break;
				case 1:
					g.setColor(Color.WHITE);
					g.fillOval(chess[i][j].getX() - len/2+2,chess[i][j].getY()-len/2+2, len-5,len-5);
					break;
				}

			}
		}
	}
	//判断局面是否存在五颗棋连成一线
	public boolean isEnd(int row,int col,int color) {
		int x[]= {0,-1,-1,-1,0,1,1,1};
		int y[]= {-1,-1,0,1,1,1,0,-1};
		for(int i=0;i<8;i++) {
			if(dfs(row,col,x[i],y[i],5,color)==true) {
				return true;
			}
		}
		return false;
	}
    //深搜
	private boolean dfs(int x,int y,int dx,int dy,int d,int color) {
		if(d==0)
			return true;
		if(x<0||x>=19||y<0||y>=19)
			return false;
		if(chess[x][y].getColor()!=color)
			return false;
		return  dfs(x+dx,y+dy,dx,dy,d-1, color);
	}
	
	//逻辑处理
	public void deal(String str) {
		if(str==null)
			return ;
		if('0'<=str.charAt(0)&&str.charAt(0)<='9') {
			try {
			int result=Integer.valueOf(str);
			 int col=result%100;
			 int row =result/100;
			 int color=1;
			 if(this.myColor==false)
				 color=-1;
			chess[row][col].setColor(color);
			 repaint();
			 isEnd=isEnd(row, col, color);
			 if(isEnd==true)
			 JOptionPane.showMessageDialog(null,"游戏结束");
	    	 isBlack=!isBlack;
			}
			catch(Exception e) {
				
			}
		}
		else if(str.equals("+")) {
			this.isInit=false;
			this.myColor=false;
		}
		else if(str.equals("-")) {
			this.isInit=false;
			this.myColor=false;
		}
		else {
		if(str.indexOf("黑")!=-1) {
			init();
			this.isInit=true;
			this.myColor=true;
		}
		else if(str.indexOf("白")!=-1) {
			init();
			this.isInit=true;
			this.myColor=false;
		}
		 textArea.append(str+"\n");
		 textArea.setEditable(false);
		}
	}
	//发送数据
	public void write(String str) {
		if(client!=null)
		client.write(str);
	}
}
