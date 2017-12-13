package kr.co.sist.pcbmaster.frm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.evt.PcbMasterLoginEvt;


@SuppressWarnings("serial")
public class PcbMasterLoginFrm extends JFrame {
	private JButton btnLogin,btnClose;
	private JTextField tfID, tfPass;
	
	public PcbMasterLoginFrm() {
		
		super("pc방 연습");
		
		JLabel lblId=new JLabel("아이디");
		JLabel lblPass = new JLabel("비밀번호");
		JLabel lblAdmin= new JLabel("관리자 로그인");
		
		btnLogin = new JButton("로그인");
		btnClose = new JButton("종료");
		
		
		tfID = new JTextField();
		
		tfPass = new JPasswordField();
		
		setLayout(null);
		
		add(lblAdmin);
		add(lblId);
		add(lblPass);
		add(btnLogin);
		add(btnClose);
		add(tfID);
		add(tfPass);

		lblAdmin.setBounds(150, 20, 100, 100);
		lblId.setBounds(80, 100, 100, 60);
		lblPass.setBounds(80, 140, 100, 60);
		
		tfID.setBounds(200, 110, 100, 30);
		tfPass.setBounds(200, 150, 100, 30);
		
		btnLogin.setBounds(80, 230, 100, 40);
		btnClose.setBounds(200, 230, 100, 40);
		
		PcbMasterLoginEvt pmle = new PcbMasterLoginEvt(this); 
		btnLogin.addActionListener(pmle);
		btnClose.addActionListener(pmle);
		tfPass.addActionListener(pmle);
		addWindowListener(pmle);
		
		setVisible(true);
		setBounds(100,100,400,350);
		
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
