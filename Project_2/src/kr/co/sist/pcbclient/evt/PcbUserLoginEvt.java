package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.co.sist.pcbclient.form.PcbUserLoginFrm;

public class PcbUserLoginEvt implements ActionListener {
	private PcbUserLoginFrm pulf;
	private int seatPri;
	
	public PcbUserLoginEvt(PcbUserLoginFrm pulf) {
		this.pulf = pulf;
	}//생성자

	@Override
	public void actionPerformed(ActionEvent e) {

	}//actionPerformed
	
	public void checkLogin() { //로그인체크
		
	}//checkLogin
	
	public void join() {
		
	}

	public void login() {
		
	}
}//class
