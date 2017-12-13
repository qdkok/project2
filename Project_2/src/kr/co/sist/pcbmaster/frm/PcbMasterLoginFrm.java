package kr.co.sist.pcbmaster.frm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class PcbMasterLoginFrm extends JFrame {
	private JButton btnLogin,btnClose;
	private JTextField tfID, tfPass;
	
	public PcbMasterLoginFrm() {
	}//PcbMasterLoginFrm

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public JTextField getTfID() {
		return tfID;
	}

	public JTextField getTfPass() {
		return tfPass;
	}
	
}
