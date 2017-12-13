package kr.co.sist.pcbclient.vo;

public class PcbNoMemLoginVO {
	private String noUserId;

	public PcbNoMemLoginVO() {
	}

	public PcbNoMemLoginVO(String noUserId) {
		this.noUserId = noUserId;
	}

	public String getNoUserId() {
		return noUserId;
	}

	public void setNoUserId(String noUserId) {
		this.noUserId = noUserId;
	}

	@Override
	public String toString() {
		return "PcbNoMemLoginVO [noUserId=" + noUserId + "]";
	}
	
}
