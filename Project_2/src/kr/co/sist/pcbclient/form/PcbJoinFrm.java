package kr.co.sist.pcbclient.form;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PcbJoinFrm extends JDialog {
	private PcbUserLoginFrm pulf;
	private JButton btnJoin, btnCancle, btnchkId;
	private JTextField tfUserId, tfUserPass, tfUserPassChk, tfUserName, tfUserPh, tfUserEm;
	
	public PcbJoinFrm( PcbUserLoginFrm pulf ) {
		this.pulf = pulf;
	}

	public JButton getBtnJoin() {
		return btnJoin;
	}

	public JButton getBtnCancle() {
		return btnCancle;
	}

	public JButton getBtnchkId() {
		return btnchkId;
	}

	public JTextField getTfUserId() {
		return tfUserId;
	}

	public JTextField getTfUserPass() {
		return tfUserPass;
	}

	public JTextField getTfUserPassChk() {
		return tfUserPassChk;
	}

	public JTextField getTfUserName() {
		return tfUserName;
	}

	public JTextField getTfUserPh() {
		return tfUserPh;
	}

	public JTextField getTfUserEm() {
		return tfUserEm;
	}
	
	
	
}
