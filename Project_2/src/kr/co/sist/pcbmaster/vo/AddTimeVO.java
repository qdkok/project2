package kr.co.sist.pcbmaster.vo;

public class AddTimeVO {
	String id;
	int Time;
	public AddTimeVO(String id, int time) {
		super();
		this.id = id;
		Time = time;
	}//AddTimeVO
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	
}//class
