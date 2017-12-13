package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.PrdItemVO;

public class PcbAddPrdEvt implements ActionListener{
	private PcbAddPrdFrm ppf;

	public PcbAddPrdEvt(PcbAddPrdFrm ppf,PcbMasterMainFrm pmmf) {
		super();
		this.ppf = ppf;
	}//PcbPrdEvt
	
	public void prdAdd() {
		
	}//prdAdd
	
	public void prdUpdate() {
		
	}//prdUpdate

	public void setPrdData(PrdItemVO prdItem) {
		
	}//setItem 
	
	public void setImg() {
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed
 	
}//class
