package kr.co.sist.pcbmaster.evt;

import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;

public class PcbMasterServer implements Runnable {
	private PcbMasterMainFrm pmmf;
	private PcbMasterMainEvt pmme;
	
	public PcbMasterServer(PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme) {
		super();
		this.pmmf = pmmf;
		this.pmme = pmme;
	}//PcbMasterServer

	@Override
	public void run() {
		
	}//run
	
	public void userLogin() {
		
	}//userLogin
	
	public void takeMsg() {
		
	}//takeMsg
	
	public void takeFile() {
		
	}//takeFime
	
	public void takeUseEnd() {
		
	}//takeUseEnd
	
	public void takeOrder() {
		
	}//takeOrder
	
	public void sendFile() {
		
	}//sendFile 
	
	public void endUser(String seatNum) {
		
	}//endUser
	
	public void sendMsg(String seatNum,String msg) {
		 
	}//sendMsg 
}//class
