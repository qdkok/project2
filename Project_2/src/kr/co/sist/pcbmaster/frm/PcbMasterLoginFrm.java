package kr.co.sist.pcbmaster.frm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.evt.PcbMasterLoginEvt;


@SuppressWarnings("serial")
public class PcbMasterLoginFrm extends JFrame {
	private JButton btnLogin,btnClose;
	private JTextField tfID, tfPass;
	
	public PcbMasterLoginFrm() {
		
		ImageIcon mainBackImage = new ImageIcon(System.getProperty("user.dir")+"/common/"+"master_main.png");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainBackImage.getImage(), 0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		background.setLayout(null);
		background.setBounds(0,0,400,350);
		
		JLabel lblId=new JLabel("아이디");
		JLabel lblPass = new JLabel("비밀번호");
		JLabel lblAdmin= new JLabel("관리자 로그인");
		
		lblAdmin.setFont(new Font("돋움", Font.BOLD, 20));
		
		lblId.setForeground(Color.WHITE);
		lblPass.setForeground(Color.WHITE);
		lblAdmin.setForeground(Color.WHITE);
		
		btnLogin = new JButton(new ImageIcon(System.getProperty("user.dir")+"/common/"+"master_login.png"));
		btnClose = new JButton(new ImageIcon(System.getProperty("user.dir")+"/common/"+"master_end.png"));
		
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
		add(background);

		lblAdmin.setBounds(130, 15, 200, 100);
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
		setResizable(false);
		setBounds(100,100,400,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
