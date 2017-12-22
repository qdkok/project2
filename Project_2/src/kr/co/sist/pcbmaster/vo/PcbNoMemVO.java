package kr.co.sist.pcbmaster.vo;

public class PcbNoMemVO {
	private String noMemId,seatNum ;
	private int leftTime;
	public PcbNoMemVO(String noMemId, String seatNum, int leftTime) {
		super();
		this.noMemId = noMemId;
		this.seatNum = seatNum;
		this.leftTime = leftTime;
	}
	
	public String getNoMemId() {
		return noMemId;
	}
	public void setNoMemId(String noMemId) {
		this.noMemId = noMemId;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	
}
