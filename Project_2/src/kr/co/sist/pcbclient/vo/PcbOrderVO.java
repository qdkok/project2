package kr.co.sist.pcbclient.vo;

public class PcbOrderVO {//좌석번호 //로그인시간 //상품번호 //수량
	private String seatNum, loginTime, prdNum;
	private int quantity;
	public PcbOrderVO(String seatNum, String loginTime, String prdNum, int quantity) {
		super();
		this.seatNum = seatNum;
		this.loginTime = loginTime;
		this.prdNum = prdNum;
		this.quantity = quantity;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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
