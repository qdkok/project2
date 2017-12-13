package kr.co.sist.pcbmaster.frm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;
import kr.co.sist.pcbmaster.evt.PcbMasterServer;

public class PcbStatusFrm {
	private PcbMasterMainEvt pmme;
	private PcbMasterMainFrm pmmf;
	private JButton addTime,sendMsg,useEnd;
	private JLabel lblSeatNum,lblId,lblName,lblTime;
	private DefaultComboBoxModel dcbm;
	
	public PcbStatusFrm(PcbMasterMainEvt pmme, PcbMasterMainFrm pmmf,String seatNum,PcbMasterServer pms) {
		super();
		this.pmme = pmme;
		this.pmmf = pmmf;
	}//PcbStatusFrm

	public PcbMasterMainEvt getPmme() {
		return pmme;
	}

	public void setPmme(PcbMasterMainEvt pmme) {
		this.pmme = pmme;
	}

	public PcbMasterMainFrm getPmmf() {
		return pmmf;
	}

	public void setPmmf(PcbMasterMainFrm pmmf) {
		this.pmmf = pmmf;
	}

	public JButton getAddTime() {
		return addTime;
	}

	public void setAddTime(JButton addTime) {
		this.addTime = addTime;
	}

	public JButton getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(JButton sendMsg) {
		this.sendMsg = sendMsg;
	}

	public JButton getUseEnd() {
		return useEnd;
	}

	public void setUseEnd(JButton useEnd) {
		this.useEnd = useEnd;
	}

	public JLabel getLblSeatNum() {
		return lblSeatNum;
	}

	public void setLblSeatNum(JLabel lblSeatNum) {
		this.lblSeatNum = lblSeatNum;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblTime() {
		return lblTime;
	}

	public void setLblTime(JLabel lblTime) {
		this.lblTime = lblTime;
	}

	public DefaultComboBoxModel getDcbm() {
		return dcbm;
	}

	public void setDcbm(DefaultComboBoxModel dcbm) {
		this.dcbm = dcbm;
	}
	
	
}//class
