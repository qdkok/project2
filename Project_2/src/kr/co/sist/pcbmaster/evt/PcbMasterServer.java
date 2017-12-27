package kr.co.sist.pcbmaster.evt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.form.PcbStatusFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;

public class PcbMasterServer implements Runnable {
	private PcbMasterMainFrm pmmf;
	private PcbMasterMainEvt pmme;
	
	private PcbStatusFrm psf;
	private Thread serverThred;
	private int[] serverport;
	
	public static List<ServerHelper> listServer=new ArrayList<>();
	
	public PcbMasterServer(PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme) {
		super();
		this.pmmf = pmmf;
		this.pmme = pmme;  
		
		serverport = new int[20];
		int port=10000;
		
		//포트지정
		for(int i=0 ; i < serverport.length ; i++  ) {
			serverport[i]=port++;
		}//end for
		
		//서버시작
		serverThred = new Thread(this);
		serverThred.start();
	}//PcbMasterServer

	@Override
	public void run() {
		for(int i=0 ; i < serverport.length ; i++) {
			ServerHelper sh=new ServerHelper(this,serverport[i]);
			listServer.add(sh);
			sh.start();
		}
	}//run
	
	public void userLogin() {//유저로그인
		pmme.setSeats();
	}//userLogin
	
	public void takeMsg(String msg) throws IOException {
		JOptionPane.showMessageDialog(pmmf, msg);
	}//takeMsg
	
	public void takeFile() {
		
	}//takeFime
	
	public void takeUseEnd() {//사용종료
		pmme.setSeats();
	}//takeUseEnd
	
	public void takeOrder() {
		pmme.setOrdList();
	}//takeOrder
	
	public void sendFile() {
		
	}//sendFile 
	
	public void endUser(String seatNum) {
		
	}//endUser
	
	public void sendMsg(String seatNum,String msg) {
		 
	}//sendMsg 
}//class
