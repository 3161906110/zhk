package cn.edu.fjut.dao;

import java.util.List;
import java.util.Vector;



import cn.edu.fjut.user.User;
import cn.edu.fjut.util.DBUtil;




/**
 * ����û������ݷ���
 * @author Administrator
 *
 */
public class UserDao {
  public UserDao() {
	  
  }
   
  //�û����Ƿ����
  public boolean isUser(String userName) {
	  List list=(List) DBUtil.executeQuery("select * from User where userName = ?",User.class,userName);
  	if(list.isEmpty()==true) {
  		return false;
  	}
  	else {
  	return true;
  	}
  }
  //�û������Ƿ���ȷ
  public boolean isUser(String userName,String userPassword) {
	  List list=(List) DBUtil.executeQuery("select * from User where userName = ? and userPassword = ?",User.class,userName,userPassword);
  	if(list.isEmpty()==true) {
  		return false;
  	}
  	else {
  	return true;
  	}
  }
  //�����û�
  public void addEmp(User user) {
		// ����Ҫ�������ж�
		// �ж�Ա������Ƿ��Ѿ����ڣ�������ڣ���ʾ�Ѿ�����
		if(isUser(user.getUserName())) {
			System.out.println("Ա�����Ѵ���");
		}
		else {
			boolean ok=DBUtil.execUpdate("insert into user values (?,?,?,?)",user.getUserName(),user.getUserPassword(),user.getUserVictory(),user.getUseJob());
			 if(ok==true) {
				 System.out.println("���ӳɹ�");
			 }
		}
	}
  
  //��ѯ����
  public List findUser() {
		List list=(List) DBUtil.executeQuery("select * from User order by userVictory", User.class, null);
		return list;
	}
  
  //�޸��û�����
  public void updateUser(String userName,int dv) {
	  DBUtil.execUpdate("update user set userVictory = userVictory + ? where userName = ?",dv,userName); 
  }
  
  //��ѯս��
  public void findUser(Vector columnName,Vector rowData) {
	  List list=(List) DBUtil.executeQuery("select * from User order by userVictory desc", User.class,null);
		columnName.add("�û���");
		columnName.add("ս������");;
		for(int i=0;i<list.size();i++) {
			User user=(User)list.get(i);
			Vector line1 = new Vector();
		    line1.add(user.getUserName());
		    line1.add(user.getUserVictory());
			rowData.add(line1);
		}
  }
}
