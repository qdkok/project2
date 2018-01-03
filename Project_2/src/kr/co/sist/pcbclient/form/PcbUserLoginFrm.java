package kr.co.sist.pcbclient.form;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
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
	private JLabel lblID, lblPW ;
	private ImageIcon mainBackImage, checkBack;
	
	public PcbUserLoginFrm() {
		btnJoin = new JButton("회원가입");
		btnJoin.setForeground(Color.white);
		btnJoin.setBackground(new Color(0x104F5E));
		btnLogin = new JButton("로그인");
		btnLogin.setForeground(Color.white);
		btnLogin.setBackground(new Color(0x104F5E));
		
		tfUserId = new JTextField();
		tfUserPass = new JPasswordField();
		tfNoMem = new JTextField();
		lblID = new JLabel("아이디");
		lblPW = new JLabel("비밀번호");
		
		mainBackImage = new ImageIcon(System.getProperty("user.dir") + "/common/" + "main_real.png");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainBackImage.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		checkBack = new ImageIcon(System.getProperty("user.dir") + "/common/" + "checkbox_back.png");
		JPanel Panel_memChk = new JPanel(new GridLayout(1, 2)) {
			public void paintComponent(Graphics g) {
				g.drawImage(checkBack.getImage(), 0, 0, null);
				setOpaque(true);
				super.paintComponent(g);
			}
		};
		
		cbg = new CheckboxGroup();
		cbMem = new Checkbox("회원", cbg, true);
		cbMem.setFont(new Font("돋움", Font.BOLD, 18));
		cbMem.setForeground(Color.white);
		
		cbNoMem = new Checkbox("비회원", cbg, false);
		cbNoMem.setFont(new Font("돋움", Font.BOLD, 18));
		cbNoMem.setForeground(Color.white);
		
		lblID.setForeground(Color.WHITE);
		lblPW.setForeground(Color.WHITE);
		
		background.setBounds(0, 0, 1915, 1035);
		background.setLayout(null);
		
		Panel_memChk.add(cbMem);
		Panel_memChk.add(cbNoMem);
		Panel_memChk.setBackground(new Color(0x0f5968));
		
		lblID.setBounds(820, 480, 80, 30);
		lblID.setFont(new Font("돋움", Font.BOLD, 25));
		tfUserId.setBounds(950, 480, 150, 30);
		lblPW.setBounds(820, 580, 100, 30);
		lblPW.setFont(new Font("돋움", Font.BOLD, 25));
		tfUserPass.setBounds(950, 580, 150, 30);
		btnJoin.setBounds(785, 825, 150, 80);
		btnLogin.setBounds(990, 825, 150, 80);

		Panel_memChk.setBounds(865, 700, 250, 40);
		
		setLayout(null);
		
		add(lblID);
		add(tfUserId);
		add(lblPW);
		add(tfUserPass);
		add(Panel_memChk);
		add(btnJoin);
		add(btnLogin);
		add(background);//
		
		PcbUserLoginEvt pule = new PcbUserLoginEvt(this);
		btnJoin.addActionListener(pule);
		btnLogin.addActionListener(pule);
		cbMem.addItemListener(pule);
		cbNoMem.addItemListener(pule);
		tfUserId.addActionListener(pule);
		tfUserPass.addActionListener(pule);
		
		setBounds(0, 0, 1915, 1035);
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

	public JLabel getLblID() {
		return lblID;
	}

	public void setLblID(JLabel lblID) {
		this.lblID = lblID;
	}

	
}//class
