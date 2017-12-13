package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import kr.co.sist.pcbclient.form.PcbStatusFrm;

public class PcbStatusEvt implements ActionListener, Runnable {
		
	private PcbStatusFrm psf;
	
	public PcbStatusEvt(PcbStatusFrm psf) {
		this.psf = psf;
	}
	
	@Override
	public void run() {

	}
	
	public void setStatus() { //ÇöÀç»óÅÂ»Ñ¸®±â
		
	}
	
	public String seatChk() { //ÁÂ¼®¹øÈ£ »Ñ¸®±â
		return "";
	}
	
	public String time() {
		return "";
	}
	
	public void endRequest() {
		
	}
	
	public void sendMsg() {
		
	}

	public void PcbOrdView() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
