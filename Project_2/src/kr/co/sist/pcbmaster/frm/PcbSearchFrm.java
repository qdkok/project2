package kr.co.sist.pcbmaster.frm;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

public class PcbSearchFrm extends JDialog {
	private JTextField searchId;
	private JButton btnSearch,btnAddTime;
	private JLabel jlName,jlTime;
	
	public PcbSearchFrm(PcbMasterMainEvt pmme) {
	}//PcbSearchFrm

	public JTextField getSearchId() {
		return searchId;
	}

	public void setSearchId(JTextField searchId) {
		this.searchId = searchId;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnAddTime() {
		return btnAddTime;
	}

	public void setBtnAddTime(JButton btnAddTime) {
		this.btnAddTime = btnAddTime;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public void setJlName(JLabel jlName) {
		this.jlName = jlName;
	}

	public JLabel getJlTime() {
		return jlTime;
	}

	public void setJlTime(JLabel jlTime) {
		this.jlTime = jlTime;
	}
	
	
}
