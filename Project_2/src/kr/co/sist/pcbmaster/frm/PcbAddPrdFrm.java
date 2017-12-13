package kr.co.sist.pcbmaster.frm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

public class PcbAddPrdFrm {
	private PcbMasterMainEvt pmme;
	private PcbSearchFrm pmmf;
	private ImageIcon img;
	private JTextField prdName,prdPrice;
	private JButton prdOk,prdCancle,prdAddImg;
	private DefaultComboBoxModel prdcate;
	private boolean flag;
	
	public PcbAddPrdFrm(PcbMasterMainEvt pmme, PcbSearchFrm pmmf,boolean flag) {
		super();
		this.pmme = pmme;
		this.pmmf = pmmf;
	}//PcbPrdFrm
	
	public void viewImg() {
		
	}//viewImg
	
	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public void setPrdName(JTextField prdName) {
		this.prdName = prdName;
	}

	public void setPrdPrice(JTextField prdPrice) {
		this.prdPrice = prdPrice;
	}

	public void setPrdOk(JButton prdOk) {
		this.prdOk = prdOk;
	}

	public void setPrdCancle(JButton prdCancle) {
		this.prdCancle = prdCancle;
	}

	public void setPrdAddImg(JButton prdAddImg) {
		this.prdAddImg = prdAddImg;
	}

	public void setPrdcate(DefaultComboBoxModel prdcate) {
		this.prdcate = prdcate;
	}
	
}//class
