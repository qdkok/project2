package kr.co.sist.pcbclient.vo;

public class PcbOrderVO {
	private String prdNum;
	private int quantity;
	
	public PcbOrderVO() {
	}

	public PcbOrderVO(String prdNum, int quantity) {
		this.prdNum = prdNum;
		this.quantity = quantity;
	}

	public String getPrdNum() {
		return prdNum;
	}

	public void setPrdNum(String prdNum) {
		this.prdNum = prdNum;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
