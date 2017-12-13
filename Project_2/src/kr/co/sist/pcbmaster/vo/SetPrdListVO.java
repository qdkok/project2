package kr.co.sist.pcbmaster.vo;

public class SetPrdListVO {
	String prdNum,prdCate,prdName,prdInputTime;
	int price;
	public SetPrdListVO(String prdNum, String prdCate, String prdName, String prdInputTime, int price) {
		super();
		this.prdNum = prdNum;
		this.prdCate = prdCate;
		this.prdName = prdName;
		this.prdInputTime = prdInputTime;
		this.price = price;
	}//SetPrdListVO
	public String getPrdNum() {
		return prdNum;
	}
	public void setPrdNum(String prdNum) {
		this.prdNum = prdNum;
	}
	public String getPrdCate() {
		return prdCate;
	}
	public void setPrdCate(String prdCate) {
		this.prdCate = prdCate;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPrdInputTime() {
		return prdInputTime;
	}
	public void setPrdInputTime(String prdInputTime) {
		this.prdInputTime = prdInputTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}//class
