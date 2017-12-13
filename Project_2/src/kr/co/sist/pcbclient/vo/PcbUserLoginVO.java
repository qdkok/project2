package kr.co.sist.pcbclient.vo;

public class PcbUserLoginVO {
	private String userId, userPass;

	public PcbUserLoginVO() {
	}

	public PcbUserLoginVO(String userId, String userPass) {
		this.userId = userId;
		this.userPass = userPass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@Override
	public String toString() {
		return "PcbUserLoginVO [userId=" + userId + ", userPass=" + userPass + "]";
	}
	
}
