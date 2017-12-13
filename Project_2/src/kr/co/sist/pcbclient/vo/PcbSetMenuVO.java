package kr.co.sist.pcbclient.vo;

public class PcbSetMenuVO {
	private String prdNum, prdName, prdImg, prdcate;
	private int price;
	
	public PcbSetMenuVO() {
	}

	public PcbSetMenuVO(String prdNum, String prdName, String prdImg, String prdcate, int price) {
		this.prdNum = prdNum;
		this.prdName = prdName;
		this.prdImg = prdImg;
		this.prdcate = prdcate;
		this.price = price;
	}

	public String getPrdNum() {
		return prdNum;
	}

	public void setPrdNum(String prdNum) {
		this.prdNum = prdNum;
	}

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

	public String getPrdcate() {
		return prdcate;
	}

	public void setPrdcate(String prdcate) {
		this.prdcate = prdcate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PcbSetMenuVO [prdNum=" + prdNum + ", prdName=" + prdName + ", prdImg=" + prdImg + ", prdcate=" + prdcate
				+ ", price=" + price + "]";
	}
}
