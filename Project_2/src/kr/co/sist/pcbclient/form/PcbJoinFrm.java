package kr.co.sist.pcbclient.form;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
	
	public PcbJoinFrm( PcbUserLoginFrm pulf ) {
		this.pulf = pulf;
		JLabel lblId = new JLabel("아이디");
		JLabel lblPw = new JLabel("비밀번호");
		JLabel lblPwChk = new JLabel("비밀번호확인");
		JLabel lblName = new JLabel("이름");
		JLabel lblPhone = new JLabel("핸드폰번호");
		JLabel lblEmail = new JLabel("이메일");
		JLabel lblAt = new JLabel("@");
		tfUserId = new JTextField();
		tfUserPass = new JPasswordField();
		tfUserPassChk = new JPasswordField();
		tfUserName = new JTextField();
		tfUserPh = new JTextField();
		tfUserEm_addr = new JTextField();
		tfUserEm_domain = new JTextField();
		
		btnchkId = new JButton("중복확인");
		btnJoin = new JButton("회원가입");
		btnCancle = new JButton("취소");
		
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
		
		btnchkId.setBounds(330, 50, 90, 20);
		btnJoin.setBounds(120, 375, 100, 30);
		btnCancle.setBounds(230, 375, 100, 30);
		
		add(lblId);
		add(tfUserId);
		add(lblPw);
		add(tfUserPass);
		add(lblPwChk);
		add(tfUserPassChk);
		add(lblName);
		add(tfUserName);
		add(lblPhone);
		add(cbPhBox);
		add(tfUserPh);
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
