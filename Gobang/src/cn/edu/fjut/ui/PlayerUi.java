package cn.edu.fjut.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.fjut.client.Client;
import cn.edu.fjut.client.ClientRead;
import cn.edu.fjut.frame.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 用户界面类
 * @author Administrator
 *
 */
public class PlayerUi extends JFrame {

	private JPanel contentPane;
    public Frame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerUi frame = new PlayerUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
	/**
	 * Create the frame.
	 */
	public PlayerUi() {
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u6218\u7EE9\u699C");
		btnNewButton.setBounds(167, 80, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u672C\u5730\u5BF9\u6218");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new Frame(false,null);
				frame.show();
				
			}
		});
		btnNewButton_1.setBounds(167, 130, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u7F51\u7EDC\u5BF9\u6218");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client c=new Client();
				frame = new Frame(true,c);
				frame.show();
				c.write("网络对战");
				ClientRead cr=new ClientRead(c.socket,frame);
				Thread thread=new Thread(cr);
				thread.start();
				
			}
		});
		btnNewButton_2.setBounds(167, 185, 93, 23);
		contentPane.add(btnNewButton_2);
	}
}
