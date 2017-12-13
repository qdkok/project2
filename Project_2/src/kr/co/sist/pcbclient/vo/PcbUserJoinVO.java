package kr.co.sist.pcbclient.vo;

public class PcbUserJoinVO {
	private String id, pass, name, phone, email;

	public PcbUserJoinVO() {
	}

	public PcbUserJoinVO(String id, String pass, String name, String phone, String email) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PcbUserJoinVO [id=" + id + ", pass=" + pass + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ "]";
	}
	
}
