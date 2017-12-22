package kr.co.sist.pcbclient.vo;

public class PcbOrderVO {
	private String prdNum, prdName;
	private int quantity, price;
	
	public PcbOrderVO() {
	}

	public PcbOrderVO(String prdNum, String prdName, int quantity, int price) {
		this.prdNum = prdNum;
		this.prdName = prdName;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
}
