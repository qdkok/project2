package kr.co.sist.pcbclient.vo;

public class PcbStatusVO {
	private String id, startTime;
	private int leftTime;
	
	public PcbStatusVO() {
	}

	public PcbStatusVO(String id, String startTime, int leftTime) {
		this.id = id;
		this.startTime = startTime;
		this.leftTime = leftTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	
}
