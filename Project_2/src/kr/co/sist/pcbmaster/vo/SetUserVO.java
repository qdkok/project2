package kr.co.sist.pcbmaster.vo;

public class SetUserVO {
	String id,name,startTime;
	int leftTime;
	
	public SetUserVO(String id, String name, String startTime, int leftTime) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.leftTime = leftTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
