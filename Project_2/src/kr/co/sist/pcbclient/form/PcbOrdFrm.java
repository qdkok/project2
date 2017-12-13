package kr.co.sist.pcbclient.form;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PcbOrdFrm extends JDialog {
	private PcbStatusFrm psf;
	private DefaultTableModel dtmCookie, dtmRamen, dtmDrink, dtmOrder;
	private JButton btnOrder, btnCancle;
	private JLabel lblPrice;
	
	public PcbOrdFrm(PcbStatusFrm psf) {
		this.psf = psf;
	}

	public PcbStatusFrm getPsf() {
		return psf;
	}

	public void setPsf(PcbStatusFrm psf) {
		this.psf = psf;
	}

	public DefaultTableModel getDtmCookie() {
		return dtmCookie;
	}

	public void setDtmCookie(DefaultTableModel dtmCookie) {
		this.dtmCookie = dtmCookie;
	}

	public DefaultTableModel getDtmRamen() {
		return dtmRamen;
	}

	public void setDtmRamen(DefaultTableModel dtmRamen) {
		this.dtmRamen = dtmRamen;
	}

	public DefaultTableModel getDtmDrink() {
		return dtmDrink;
	}

	public void setDtmDrink(DefaultTableModel dtmDrink) {
		this.dtmDrink = dtmDrink;
	}

	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}

	public void setDtmOrder(DefaultTableModel dtmOrder) {
		this.dtmOrder = dtmOrder;
	}

	public JButton getBtnOrder() {
		return btnOrder;
	}

	public void setBtnOrder(JButton btnOrder) {
		this.btnOrder = btnOrder;
	}

	public JButton getBtnCancle() {
		return btnCancle;
	}

	public void setBtnCancle(JButton btnCancle) {
		this.btnCancle = btnCancle;
	}

	public JLabel getLblPrice() {
		return lblPrice;
	}

	public void setLblPrice(JLabel lblPrice) {
		this.lblPrice = lblPrice;
	}
	
	
}
