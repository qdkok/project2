package kr.co.sist.pcbmaster.frm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class PcbMasterMainFrm extends JFrame {
	private JButton[] btnSeats;
	private JButton btnAddTime,btnOrdCancle,btnPrdAdd,btnPrdUpdate,btnPrdDelete;
	private DefaultListModel dtmOrdList,dtmPrdList;
	private JTable tOrdList,tPrdList;
	private DefaultComboBoxModel prdcate;
	
	public PcbMasterMainFrm() {
	}

	public JButton[] getBtnSeats() {
		return btnSeats;
	}

	public void setBtnSeats(JButton[] btnSeats) {
		this.btnSeats = btnSeats;
	}

	public JButton getBtnAddTime() {
		return btnAddTime;
	}

	public void setBtnAddTime(JButton btnAddTime) {
		this.btnAddTime = btnAddTime;
	}

	public JButton getBtnOrdCancle() {
		return btnOrdCancle;
	}

	public void setBtnOrdCancle(JButton btnOrdCancle) {
		this.btnOrdCancle = btnOrdCancle;
	}

	public JButton getBtnPrdAdd() {
		return btnPrdAdd;
	}

	public void setBtnPrdAdd(JButton btnPrdAdd) {
		this.btnPrdAdd = btnPrdAdd;
	}

	public JButton getBtnPrdUpdate() {
		return btnPrdUpdate;
	}

	public void setBtnPrdUpdate(JButton btnPrdUpdate) {
		this.btnPrdUpdate = btnPrdUpdate;
	}

	public JButton getBtnPrdDelete() {
		return btnPrdDelete;
	}

	public void setBtnPrdDelete(JButton btnPrdDelete) {
		this.btnPrdDelete = btnPrdDelete;
	}

	public DefaultListModel getDtmOrdList() {
		return dtmOrdList;
	}

	public void setDtmOrdList(DefaultListModel dtmOrdList) {
		this.dtmOrdList = dtmOrdList;
	}

	public DefaultListModel getDtmPrdList() {
		return dtmPrdList;
	}

	public void setDtmPrdList(DefaultListModel dtmPrdList) {
		this.dtmPrdList = dtmPrdList;
	}

	public JTable gettOrdList() {
		return tOrdList;
	}

	public void settOrdList(JTable tOrdList) {
		this.tOrdList = tOrdList;
	}

	public JTable gettPrdList() {
		return tPrdList;
	}

	public void settPrdList(JTable tPrdList) {
		this.tPrdList = tPrdList;
	}

	public DefaultComboBoxModel getPrdcate() {
		return prdcate;
	}

	public void setPrdcate(DefaultComboBoxModel prdcate) {
		this.prdcate = prdcate;
	}
	
}
