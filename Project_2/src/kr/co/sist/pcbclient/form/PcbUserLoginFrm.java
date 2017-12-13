package kr.co.sist.pcbclient.form;

import java.awt.Checkbox;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PcbUserLoginFrm extends JFrame {
	private JButton btnJoin, btnLogin;
	private JTextField tfUserId, tfUserPass, tfNoMem;
	private Checkbox cbMem, cbNoMem;
	
	public PcbUserLoginFrm() {
		
	}//»ý¼ºÀÚ

	//getter method
	public JButton getBtnJoin() {
		return btnJoin;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JTextField getTfUserId() {
		return tfUserId;
	}

	public JTextField getTfUserPass() {
		return tfUserPass;
	}

	public JTextField getTfNoMem() {
		return tfNoMem;
	}

	public Checkbox getCbMem() {
		return cbMem;
	}

	public Checkbox getCbNoMem() {
		return cbNoMem;
	}

	
}//class
