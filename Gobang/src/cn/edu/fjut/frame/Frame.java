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
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * ������
 * 
 * @author Administrator
 *
 */
public class Frame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private MouseListener fml;
	public Client client;
	public boolean isEnd=false;//�Ƿ����
	public boolean isBlack=true;//��������
	public boolean myColor;//������ɫ
	public Chess[][] chess = new Chess[19][19];
	static int len = 50;
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
	 * ��ʼ�����̲��� Create the frame.
	 */
	public Frame(boolean isWeb,Client client) {
		this.setTitle("������");
		this.setSize(1000, 1000);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);// Ĭ�Ͼ���
		//this.setDefaultCloseOperation(3);// ���²��Ĭ�Ϲر�
		this.getContentPane().setBackground(new Color(240, 233, 217));
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u518D\u6765\u4E00\u5C40");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init();
				
			}
		});
		btnNewButton.setBounds(414, 939, 119, 23);
		getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(236, 939, 168, 23);
		getContentPane().add(lblNewLabel);
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
	}
	//��ʼ��
    private void init() {
    	if(isEnd==false&&client!=null) {
    		return;
    	}
    	if(client!=null)
    		write("����һ��");
    	for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				chess[i][j].setColor(0);
			}
		}
    	isBlack=true;
    	isEnd=false;
    	repaint();
    }
	// ��������
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
					g.fillOval(chess[i][j].getX() - 20, chess[i][j].getY() - 20, 40, 40);
					break;
				case 1:
					g.setColor(Color.WHITE);
					g.fillOval(chess[i][j].getX() - 20, chess[i][j].getY() - 20, 40, 40);
					break;
				}

			}
		}
	}
	//�жϾ����Ƿ�������������һ��
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
    //����
	private boolean dfs(int x,int y,int dx,int dy,int d,int color) {
		if(d==0)
			return true;
		if(x<0||x>=19||y<0||y>=19)
			return false;
		if(chess[x][y].getColor()!=color)
			return false;
		return  dfs(x+dx,y+dy,dx,dy,d-1, color);
	}
	
	//�߼�����
	public void deal(String str) {
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
			 JOptionPane.showMessageDialog(null,"��Ϸ����");
	    	 isBlack=!isBlack;
			}
			catch(Exception e) {
				
			}
		}
		else if(str.equals("����һ��")) {
			init();
		}
		else {
		if(str.indexOf("��")!=-1)
			this.myColor=true;
		else if(str.indexOf("��")!=-1)
			this.myColor=false;	
		  lblNewLabel.setText(str);
		}
	}
	//��������
	public void write(String str) {
		client.write(str);
	}
}
