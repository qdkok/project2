package kr.co.sist.pcbmaster.vo;

public class SetOrdListVO {
	String ordNum,seatNum,prdName,status,ordTime;
	int quantity,price;
	public SetOrdListVO(String ordNum, String seatNum, String prdName, String status, String ordTime, int quantity,
			int price) {
		super();
		this.ordNum = ordNum;
		this.seatNum = seatNum;
		this.prdName = prdName;
		this.status = status;
		this.ordTime = ordTime;
		this.quantity = quantity;
		this.price = price;
	}
	public String getOrdNum() {
		return ordNum;
	}
	public void setOrdNum(String ordNum) {
		this.ordNum = ordNum;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(String ordTime) {
		this.ordTime = ordTime;
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
