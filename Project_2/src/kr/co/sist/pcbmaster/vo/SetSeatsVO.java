package kr.co.sist.pcbmaster.vo;

public class SetSeatsVO {
	private String id,seatNum,name;
	private int leftTime;
	
	public SetSeatsVO(String id, String seatNum, String name, int leftTime) {
		super();
		this.id = id;
		this.seatNum = seatNum;
		this.name = name;
		this.leftTime = leftTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLeftTime() {
		return leftTime;
	}

	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	
	
	
}
