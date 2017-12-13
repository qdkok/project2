package kr.co.sist.pcbmaster.vo;

public class PrdItemVO {	
	String prdName,prdImg,prdCate;
	int prdPrice;
	public PrdItemVO(String prdName, String prdImg, String prdCate, int prdPrice) {
		super();
		this.prdName = prdName;
		this.prdImg = prdImg;
		this.prdCate = prdCate;
		this.prdPrice = prdPrice;
	}//PrdItemVO
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getPrdImg() {
		return prdImg;
	}
	public void setPrdImg(String prdImg) {
		this.prdImg = prdImg;
	}
	public String getPrdCate() {
		return prdCate;
	}
	public void setPrdCate(String prdCate) {
		this.prdCate = prdCate;
	}
	public int getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(int prdPrice) {
		this.prdPrice = prdPrice;
	}
	
	
}//PrdVO
