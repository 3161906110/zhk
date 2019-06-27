package cn.edu.fjut.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.fjut.client.Client;
import cn.edu.fjut.client.ClientRead;
import cn.edu.fjut.frame.Frame;
import cn.edu.fjut.user.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
/**
 * 用户界面类
 * @author Administrator
 *
 */
public class PlayerUi extends JFrame {

	private JPanel contentPane;
	private String userName;//用户名
    public Frame frame;
    private Client client;//客户端
    ClientRead cr;//监听事件

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	*/
  
	/**
	 * Create the frame.
	 */
	public PlayerUi(String  userName) {
		this.userName=userName;
		client=null;
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u6218\u7EE9\u699C");
		btnNewButton.setBounds(167, 80, 93, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SourceShow ss=new  SourceShow();
				 ss.show();
				
			}
		});
		JButton btnNewButton_1 = new JButton("\u672C\u5730\u5BF9\u6218");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame frame1 = new Frame(false,null);
				frame1.show();
				
			}
		});
		btnNewButton_1.setBounds(167, 130, 93, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u7F51\u7EDC\u5BF9\u6218");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(client==null) {
					client=new Client();
					if(client.socket==null) {
						client=null;
						return ;
					}
					cr=new ClientRead(client.socket,null);
					Thread thread=new Thread(cr);
					thread.start();
				}
				frame = new Frame(true,client);
				frame.show();
				cr.setFrame(frame);
				client.write(userName);
				client.write("网络对战");
			}
		});
		btnNewButton_2.setBounds(167, 185, 93, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("欢迎您,"+userName);
		lblNewLabel.setBounds(353, 25,100, 15);
		contentPane.add(lblNewLabel);
	}
}
