package cn.edu.fjut.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.fjut.dao.UserDao;
import cn.edu.fjut.frame.Frame;
import cn.edu.fjut.user.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    private UserDao ud;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		ud=new UserDao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setBounds(108, 65, 72, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("             \u4E94\u5B50\u68CB\u7CFB\u7EDF");
		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(132, 5, 176, 50);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(156, 70, 95, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("       \u5BC6\u7801:");
		label.setBounds(102, 105, 54, 15);
		contentPane.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 102, 95, 21);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName=textField.getText();
				String userPassword=passwordField.getText();
				register(userName,userPassword);
			}
		});
		btnNewButton.setBounds(86, 159, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=textField.getText();
				String userPassword=passwordField.getText();
				loginDeal(userName,userPassword);
			}
		});
		btnNewButton_1.setBounds(215, 159, 93, 23);
		contentPane.add(btnNewButton_1);
	}
    
	//登录处理
	private void loginDeal(String userName,String userPassword) {
		 if(userName.equals("")) {
			 JOptionPane.showMessageDialog(null,"用户名不能为空");
		 }
		 else if(userPassword.equals("")) {
			 JOptionPane.showMessageDialog(null,"密码不能为空");
		 }
		 else if(ud.isUser(userName)==false) {
			 JOptionPane.showMessageDialog(null,"用户名不存在");
		 }
		 else if(ud.isUser(userName, userPassword)==false) {
			 JOptionPane.showMessageDialog(null,"密码错误");
		 }
		 else {
		 JOptionPane.showMessageDialog(null,"登录成功");
		 PlayerUi pu=new  PlayerUi(userName);
		 pu.show();
		 this.dispose();
		 }
	 }
	
	//注册处理
	private void register(String userName,String userPassword) {
		 if(userName.equals("")) {
			 JOptionPane.showMessageDialog(null,"用户名不能为空");
		 }
		 else if(userPassword.equals("")) {
			 JOptionPane.showMessageDialog(null,"密码不能为空");
		 }
		 else if(ud.isUser(userName)==true) {
			 JOptionPane.showMessageDialog(null,"用户名已存在");
		 }
		 else {
		 User user=new User(userName,userPassword,0,0);
		 ud.addEmp(user);
		 JOptionPane.showMessageDialog(null,"注册成功");
		 }
		 textField.setText("");
		 passwordField.setText("");
	}
	
}
