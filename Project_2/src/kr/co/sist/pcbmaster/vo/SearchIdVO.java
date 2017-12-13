package kr.co.sist.pcbmaster.vo;

public class SearchIdVO {
	String id,name;
	int leftTime;
	public SearchIdVO(String id, String name, int leftTime) {
		super();
		this.id = id;
		this.name = name;
		this.leftTime = leftTime;
	}//SearchIdVO
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
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	
	
}//SearchIdVO
