package kr.co.sist.pcbclient.form;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.pcbclient.evt.PcbJoinEvt;

@SuppressWarnings("serial")
public class PcbJoinFrm extends JDialog {
	private PcbUserLoginFrm pulf;
	private JButton btnJoin, btnCancle, btnchkId;
	private JPasswordField tfUserPass, tfUserPassChk;
	private JTextField tfUserId, tfUserName, tfUserPh, tfUserEm_addr, tfUserEm_domain;
	private DefaultComboBoxModel<String> cbmPhNum, cbmEmail;
	private JComboBox<String> cbPhBox, cbEmBox;
	private ImageIcon mainBackImage;
	
	public PcbJoinFrm( PcbUserLoginFrm pulf ) {
		this.pulf = pulf;
		JLabel lblId = new JLabel("아이디");
		JLabel lblIdImf=new JLabel("아이디는 4자이상 15자 이내로 입력");
		JLabel lblPw = new JLabel("비밀번호");
		JLabel lblPwImf=new JLabel("영문, 숫자 조합하여 입력(20자 이내)");
		JLabel lblPwChk = new JLabel("비밀번호확인");
		JLabel lblName = new JLabel("이름");
		JLabel lblPhone = new JLabel("핸드폰번호");
		JLabel lblPhoneImf=new JLabel("하이픈(-)을 제외하고 숫자만 입력");
		JLabel lblEmail = new JLabel("이메일");
		JLabel lblAt = new JLabel("@");
		tfUserId = new JTextField();
		tfUserPass = new JPasswordField();
		tfUserPassChk = new JPasswordField();
		tfUserName = new JTextField();
		tfUserPh = new JTextField();
		tfUserEm_addr = new JTextField();
		tfUserEm_domain = new JTextField();
		
		//버튼 이미지 넣기
		btnchkId = new JButton("중복확인");
		btnchkId.setForeground(Color.white);
		btnchkId.setBackground(new Color(0x104F5E));
		btnJoin = new JButton("회원가입");
		btnJoin.setForeground(Color.white);
		btnJoin.setBackground(new Color(0x104F5E));
		btnCancle = new JButton("취소");
		btnCancle.setForeground(Color.white);
		btnCancle.setBackground(new Color(0x104F5E));
		
		cbmPhNum = new DefaultComboBoxModel<>();
		String[] phoneNum = {"010","011","017","018","019"};
		for(int i =0; i<phoneNum.length; i++) {
			cbmPhNum.addElement(phoneNum[i]);
		}
		cbPhBox = new JComboBox<>(cbmPhNum);
		
		cbmEmail = new DefaultComboBoxModel<>();
		String[] EmailArr = {"naver.com","daum.net","google.com","hanmail.net","nate.com"};
		for(int i =0; i<EmailArr.length; i++) {
			cbmEmail.addElement(EmailArr[i]);
		}
		cbEmBox = new JComboBox<>(cbmEmail);
		
		setLayout(null);
		lblId.setBounds(50, 50, 40, 20);
		tfUserId.setBounds(140, 50, 180, 20);
		lblPw.setBounds(50, 100, 60, 20);
		tfUserPass.setBounds(140, 100, 180, 20);
		lblPwChk.setBounds(50, 150, 80, 20);
		tfUserPassChk.setBounds(140, 150, 180, 20);
		lblName.setBounds(50, 200, 80, 20);
		tfUserName.setBounds(140, 200, 180, 20);
		lblPhone.setBounds(50, 250, 80, 20);
		cbPhBox.setBounds(140, 250, 50, 20);
		tfUserPh.setBounds(195, 250, 125, 20);
		lblEmail.setBounds(50, 300, 80, 20);
		tfUserEm_addr.setBounds(140, 300, 80, 20);
		lblAt.setBounds(220, 300, 20, 20);
		cbEmBox.setBounds(235, 300, 85, 20);
		lblIdImf.setBounds(140, 75, 220, 20);
		lblPwImf.setBounds(140, 175, 220, 20);
		lblPhoneImf.setBounds(140, 275, 220, 20);
		
		btnchkId.setBounds(330, 50, 90, 20);
		btnJoin.setBounds(120, 375, 100, 30);
		btnCancle.setBounds(230, 375, 100, 30);
		
		
		//글자색 변경
		lblId.setForeground(Color.WHITE);
		lblPw.setForeground(Color.WHITE);
		lblPwChk.setForeground(Color.WHITE);
		lblName.setForeground(Color.WHITE);
		lblPhone.setForeground(Color.WHITE);
		lblEmail.setForeground(Color.WHITE);
		lblAt.setForeground(Color.WHITE);
		lblIdImf.setForeground(Color.LIGHT_GRAY);
		lblPwImf.setForeground(Color.LIGHT_GRAY);
		lblPhoneImf.setForeground(Color.LIGHT_GRAY);
		
		add(lblId);
		add(tfUserId);
		add(lblIdImf);
		add(lblPw);
		add(tfUserPass);
		add(lblPwImf);
		add(lblPwChk);
		add(tfUserPassChk);
		add(lblName);
		add(tfUserName);
		add(lblPhone);
		add(cbPhBox);
		add(tfUserPh);
		add(lblPhoneImf);
		add(lblEmail);
		add(tfUserEm_addr);
		add(lblAt);
		add(cbEmBox);
		
		add(btnchkId);
		add(btnJoin);
		add(btnCancle);
		
		setBounds(720, 274, 475, 470);
		setVisible(true);
		setResizable(false);
		
		//배경 이미지 넣기
		mainBackImage = new ImageIcon(System.getProperty("user.dir")+"/common/"+"Join_background.jpg");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainBackImage.getImage(), 0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background.setBounds(0, 0, 475, 470);
		background.setLayout(null);
		add(background);
		
		
		PcbJoinEvt pje = new PcbJoinEvt(this);
		btnchkId.addActionListener(pje);
		btnJoin.addActionListener(pje);
		btnCancle.addActionListener(pje);
		cbPhBox.addActionListener(pje);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
	}

	public PcbUserLoginFrm getPulf() {
		return pulf;
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

	public JPasswordField getTfUserPass() {
		return tfUserPass;
	}

	public JPasswordField getTfUserPassChk() {
		return tfUserPassChk;
	}

	public JTextField getTfUserId() {
		return tfUserId;
	}

	public JTextField getTfUserName() {
		return tfUserName;
	}

	public JTextField getTfUserPh() {
		return tfUserPh;
	}

	public JTextField getTfUserEm_addr() {
		return tfUserEm_addr;
	}

	public JTextField getTfUserEm_domain() {
		return tfUserEm_domain;
	}

	public DefaultComboBoxModel<String> getCbmPhNum() {
		return cbmPhNum;
	}

	public DefaultComboBoxModel<String> getCbmEmail() {
		return cbmEmail;
	}

	public JComboBox<String> getCbPhBox() {
		return cbPhBox;
	}

	public JComboBox<String> getCbEmBox() {
		return cbEmBox;
	}
	
	
}
