package kr.co.sist.pcbclient.vo;

public class PcbOrderVO {//�¼���ȣ //�α��νð� //��ǰ��ȣ //����
	private String loginTime, prdNum;
	private int quantity;
	public PcbOrderVO(String loginTime, String prdNum, int quantity) {
		super();
		this.loginTime = loginTime;
		this.prdNum = prdNum;
		this.quantity = quantity;
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
