package cn.edu.fjut.user;

/**
 * ”√ªß¿‡
 * 
 * @author Administrator
 *
 */
public class User {
	private String userName;
	private String userPassword;
	private int userVictory;
	private int useJob;
	public User(String userName, String userPassword, int userVictory, int useJob) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userVictory = userVictory;
		this.useJob = useJob;
	}
	public User() {
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserVictory() {
		return userVictory;
	}
	public void setUserVictory(int userVictory) {
		this.userVictory = userVictory;
	}
	public int getUseJob() {
		return useJob;
	}
	public void setUseJob(int useJob) {
		this.useJob = useJob;
	}
	
}
