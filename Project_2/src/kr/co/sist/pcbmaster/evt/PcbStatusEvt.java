 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;

public class PcbStatusEvt implements Runnable,ActionListener {
	private PcbStatusFrm psf;
	private PcbMasterMainEvt pmme;

	
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		super();
		this.psf = psf;
		this.pmme = pmme;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed

	@Override
	public void run() {
		
	}//run

	public void addTime(String id) {
		
	}//addTime
	
	public void setUser() {
		
	}//setUser 
	
	
}//class
