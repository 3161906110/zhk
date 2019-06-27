package cn.edu.fjut.dao;

import java.util.List;
import java.util.Vector;



import cn.edu.fjut.user.User;
import cn.edu.fjut.util.DBUtil;




/**
 * 针对用户的数据访问
 * @author Administrator
 *
 */
public class UserDao {
  public UserDao() {
	  
  }
   
  //用户名是否存在
  public boolean isUser(String userName) {
	  List list=(List) DBUtil.executeQuery("select * from User where userName = ?",User.class,userName);
  	if(list.isEmpty()==true) {
  		return false;
  	}
  	else {
  	return true;
  	}
  }
  //用户密码是否正确
  public boolean isUser(String userName,String userPassword) {
	  List list=(List) DBUtil.executeQuery("select * from User where userName = ? and userPassword = ?",User.class,userName,userPassword);
  	if(list.isEmpty()==true) {
  		return false;
  	}
  	else {
  	return true;
  	}
  }
  //增加用户
  public void addEmp(User user) {
		// 这里要有条件判断
		// 判断员工编号是否已经存在，如果存在，提示已经存在
		if(isUser(user.getUserName())) {
			System.out.println("员工号已存在");
		}
		else {
			boolean ok=DBUtil.execUpdate("insert into user values (?,?,?,?)",user.getUserName(),user.getUserPassword(),user.getUserVictory(),user.getUseJob());
			 if(ok==true) {
				 System.out.println("增加成功");
			 }
		}
	}
  
  //查询所有
  public List findUser() {
		List list=(List) DBUtil.executeQuery("select * from User order by userVictory", User.class, null);
		return list;
	}
  
  //修改用户数据
  public void updateUser(String userName,int dv) {
	  DBUtil.execUpdate("update user set userVictory = userVictory + ? where userName = ?",dv,userName); 
  }
  
  //查询战绩
  public void findUser(Vector columnName,Vector rowData) {
	  List list=(List) DBUtil.executeQuery("select * from User order by userVictory desc", User.class,null);
		columnName.add("用户名");
		columnName.add("战绩分数");;
		for(int i=0;i<list.size();i++) {
			User user=(User)list.get(i);
			Vector line1 = new Vector();
		    line1.add(user.getUserName());
		    line1.add(user.getUserVictory());
			rowData.add(line1);
		}
  }
}
