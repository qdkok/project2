package kr.co.sist.pcbmaster.vo;

public class PcbMasterLoginVO {
	String id,Pass;

	public PcbMasterLoginVO(String id, String pass) {
		super();
		this.id = id;
		Pass = pass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}
	
}
