package kr.co.sist.pcbclient.form;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PcbStatusFrm extends JFrame {
	private JButton btnOrd, btnMsg, btnEnd;
	private JLabel lblSeat, lblId, lblLefttime, lblStarttime;
	
	public PcbStatusFrm() {
		
	}

	public JButton getBtnOrd() {
		return btnOrd;
	}

	public void setBtnOrd(JButton btnOrd) {
		this.btnOrd = btnOrd;
	}

	public JButton getBtnMsg() {
		return btnMsg;
	}

	public void setBtnMsg(JButton btnMsg) {
		this.btnMsg = btnMsg;
	}

	public JButton getBtnEnd() {
		return btnEnd;
	}

	public void setBtnEnd(JButton btnEnd) {
		this.btnEnd = btnEnd;
	}

	public JLabel getLblSeat() {
		return lblSeat;
	}

	public void setLblSeat(JLabel lblSeat) {
		this.lblSeat = lblSeat;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblLefttime() {
		return lblLefttime;
	}

	public void setLblLefttime(JLabel lblLefttime) {
		this.lblLefttime = lblLefttime;
	}

	public JLabel getLblStarttime() {
		return lblStarttime;
	}

	public void setLblStarttime(JLabel lblStarttime) {
		this.lblStarttime = lblStarttime;
	}
	
	
}
