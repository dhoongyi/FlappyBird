package FlappyBird;

public class LoggedUserInfo {
	private static LoggedUserInfo instance;
	
	private int userId;
	private String userEmail;
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	// not allowed LoggedUserInfo a = new LoggedUserInfo();
	private LoggedUserInfo() {
//		super();
//		this.userId = userId;
//		this.userEmail = userEmail;
	}
	
	public static LoggedUserInfo getInstance() {
		if(instance == null) {
			instance = new LoggedUserInfo();
		}
		return instance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
