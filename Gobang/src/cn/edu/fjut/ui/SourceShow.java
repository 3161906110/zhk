package cn.edu.fjut.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import cn.edu.fjut.dao.UserDao;

import javax.swing.JLabel;
import java.awt.Color;

public class SourceShow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SourceShow frame = new SourceShow();
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
	public SourceShow() {
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" \u6218\u7EE9\u699C");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(173, 29, 62, 15);
		contentPane.add(lblNewLabel);
		addTable();
	}
	
	//Ìî³ä±í¸ñ
	void addTable() {
		Vector rowData, columnName;
		JTable jt = null;
		JScrollPane jsp = null;
		columnName = new Vector();
		rowData = new Vector();	
	    UserDao ud=new UserDao();
	    ud.findUser(columnName, rowData);
	    jt = new JTable(rowData, columnName);
	    DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jt.setDefaultRenderer(Object.class,r);
        jt.setRowHeight(20);
		jsp = new JScrollPane(jt);
		jsp.setBounds(50, 50, 300, 100);
        add(jsp,BorderLayout.CENTER);
		contentPane.add(jsp);
	}
}
