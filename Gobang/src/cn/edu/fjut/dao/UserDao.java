package cn.edu.fjut.dao;

import java.util.List;



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
}
