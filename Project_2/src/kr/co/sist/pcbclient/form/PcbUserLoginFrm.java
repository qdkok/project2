package kr.co.sist.pcbclient.form;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.pcbclient.evt.PcbUserLoginEvt;

@SuppressWarnings("serial")
public class PcbUserLoginFrm extends JFrame {
	private JButton btnJoin, btnLogin;
	private JPasswordField tfUserPass;
	private JTextField tfUserId, tfNoMem;
	private CheckboxGroup cbg;
	private Checkbox cbMem, cbNoMem;
	private JLabel lblPW ;
	
	public PcbUserLoginFrm() {
		btnJoin = new JButton("회원가입");
		btnLogin = new JButton("로그인");
		tfUserId = new JTextField();
		tfUserPass = new JPasswordField();
		tfNoMem = new JTextField();
		JLabel lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");
		cbg = new CheckboxGroup();
		cbMem = new Checkbox("회원", cbg, true);
		cbNoMem = new Checkbox("비회원", cbg, false);
		
		JPanel Panel_memChk = new JPanel(new GridLayout(1, 2));
		Panel_memChk.add(cbMem);
		Panel_memChk.add(cbNoMem);
		
		lblID.setBounds(90, 110, 70, 20);
		tfUserId.setBounds(160, 110, 150, 20);
		lblPW.setBounds(90, 160, 100, 20);
		tfUserPass.setBounds(160, 160, 150, 20);
		btnJoin.setBounds(90, 260, 100, 40);
		btnLogin.setBounds(205, 260, 100, 40);

		Panel_memChk.setBounds(135, 200, 150, 40);
		
		setLayout(null);
		
		add(lblID);
		add(tfUserId);
		add(lblPW);
		add(tfUserPass);
		add(Panel_memChk);
		add(btnJoin);
		add(btnLogin);
		
		PcbUserLoginEvt pule = new PcbUserLoginEvt(this);
		btnJoin.addActionListener(pule);
		btnLogin.addActionListener(pule);
		cbMem.addItemListener(pule);
		cbNoMem.addItemListener(pule);
		
		setBounds(766, 326, 400, 380);
		setVisible(true);
		setResizable(false);
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
	}//생성자

	public JButton getBtnJoin() {
		return btnJoin;
	}

	public void setBtnJoin(JButton btnJoin) {
		this.btnJoin = btnJoin;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JPasswordField getTfUserPass() {
		return tfUserPass;
	}

	public void setTfUserPass(JPasswordField tfUserPass) {
		this.tfUserPass = tfUserPass;
	}

	public JTextField getTfUserId() {
		return tfUserId;
	}

	public void setTfUserId(JTextField tfUserId) {
		this.tfUserId = tfUserId;
	}

	public JTextField getTfNoMem() {
		return tfNoMem;
	}

	public void setTfNoMem(JTextField tfNoMem) {
		this.tfNoMem = tfNoMem;
	}

	public CheckboxGroup getCbg() {
		return cbg;
	}

	public void setCbg(CheckboxGroup cbg) {
		this.cbg = cbg;
	}

	public Checkbox getCbMem() {
		return cbMem;
	}

	public void setCbMem(Checkbox cbMem) {
		this.cbMem = cbMem;
	}

	public Checkbox getCbNoMem() {
		return cbNoMem;
	}

	public void setCbNoMem(Checkbox cbNoMem) {
		this.cbNoMem = cbNoMem;
	}

	public JLabel getLblPW() {
		return lblPW;
	}

	public void setLblPW(JLabel lblPW) {
		this.lblPW = lblPW;
	}

	
}//class
