package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;

public class PcbMasterMainEvt extends MouseAdapter implements Runnable,ActionListener{
	private PcbMasterMainFrm pmmf;
	private PcbMasterServer pms;

	public PcbMasterMainEvt(PcbMasterMainFrm pmmf) {
		super();
		this.pmmf = pmmf;
	}//PcbMasterMainEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}//actionPerformed

	@Override
	public void run() {
		
	}//run
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}//mouseClicked

	public void setSeats() {
		
	}//setSeats
	
	public void setOrdList(List<SetOrdListVO> ordList){
		
	}//setOrdList
	
	public void ordCanCle(String prdNum) {
		
	}//ordCanCle
	
	public void setPrdList() {
		
	}//setPrdList
	
	public void openServer() {
		
	}//openServer
	
	public void delPrdBtn() {
		
	}//delPrdBtn
	
	public void delPrd() {
		
	}//delPrd
	
	public void addPrdBtn() {
		
	}//addPrdBtn
	
	public void editPrdBtn() {
		
	}//editPrdBtn
}//class
