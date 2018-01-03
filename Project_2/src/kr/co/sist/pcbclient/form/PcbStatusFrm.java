package kr.co.sist.pcbclient.form;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.pcbclient.evt.PcbStatusEvt;

@SuppressWarnings("serial")
public class PcbStatusFrm extends JFrame {
	private PcbUserLoginFrm pulf;
	private JButton btnOrd, btnMsg, btnEnd;
	private JLabel lblSeat, lblId, lblLefttime, lblStarttime;
	private ImageIcon mainBackImage;
	
	public PcbStatusFrm(PcbUserLoginFrm pulf) {
		this.pulf = pulf;
		lblSeat = new JLabel("좌석");
		lblId = new JLabel("Alpha34");
		JLabel textStart = new JLabel("시작시간");
		JLabel textLeft = new JLabel("남은시간");
		lblLefttime = new JLabel("");
		lblStarttime = new JLabel("");
		
		lblSeat.setFont(new Font("돋움", Font.BOLD, 30));
		lblId.setFont(new Font("돋움", Font.BOLD, 20));
		textStart.setFont(new Font("돋움", Font.BOLD, 15));
		textLeft.setFont(new Font("돋움", Font.BOLD, 15));
		
		//버튼이미지 & 롤오버 이미지
		btnOrd = new JButton("음식주문");
		btnOrd.setForeground(Color.white);
		btnOrd.setBackground(new Color(0x104F5E) );
		btnMsg = new JButton("메시지송신");
		btnMsg.setForeground(Color.white);
		btnMsg.setBackground(new Color(0x104F5E) );
		btnEnd = new JButton("사용종료");
		btnEnd.setForeground(Color.white);
		btnEnd.setBackground(new Color(0x104F5E) );
		
		setLayout(null);
		
		mainBackImage = new ImageIcon(System.getProperty("user.dir")+"/common/"+"user_login_background.jpg");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainBackImage.getImage(), 0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		//글자색 전부 흰색으로
		lblSeat.setForeground(Color.WHITE);
		lblId.setForeground(Color.WHITE);
		textStart.setForeground(Color.WHITE);
		textLeft.setForeground(Color.WHITE);
		lblStarttime.setForeground(Color.WHITE);
		lblLefttime.setForeground(Color.WHITE);
		
		
		background.setLayout(null);
		background.setBounds(0, 0, 400, 380);
		lblSeat.setBounds(50, 40, 160, 30);
		lblId.setBounds(200, 40, 100, 30);
		textStart.setBounds(80, 130, 100, 20);
		textLeft.setBounds(80, 180, 100, 20);
		lblStarttime.setBounds(200, 130, 100, 20);
		lblLefttime.setBounds(200, 180, 100, 20);
		btnOrd.setBounds(10, 250, 100, 40);
		btnMsg.setBounds(120, 250, 100, 40);
		btnEnd.setBounds(230, 250, 100, 40);
		
		add(lblSeat);
		add(lblId);
		add(textStart);
		add(textLeft);
		add(lblLefttime);
		add(lblStarttime);
		add(btnOrd);
		add(btnMsg);
		add(btnEnd);
		add(background);
		
		PcbStatusEvt pse = new PcbStatusEvt(this,pulf);
		btnOrd.addActionListener(pse);
		btnMsg.addActionListener(pse);
		btnEnd.addActionListener(pse);
		addWindowListener(pse);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(1530, 10, 350, 340);
		setVisible(true);
		setResizable(false);
	}

	public PcbUserLoginFrm getPulf() {
		return pulf;
	}

	public JButton getBtnOrd() {
		return btnOrd;
	}

	public JButton getBtnMsg() {
		return btnMsg;
	}

	public JButton getBtnEnd() {
		return btnEnd;
	}

	public JLabel getLblSeat() {
		return lblSeat;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public JLabel getLblLefttime() {
		return lblLefttime;
	}

	public JLabel getLblStarttime() {
		return lblStarttime;
	}

	public void setPulf(PcbUserLoginFrm pulf) {
		this.pulf = pulf;
	}

	public void setBtnOrd(JButton btnOrd) {
		this.btnOrd = btnOrd;
	}

	public void setBtnMsg(JButton btnMsg) {
		this.btnMsg = btnMsg;
	}

	public void setBtnEnd(JButton btnEnd) {
		this.btnEnd = btnEnd;
	}

	public void setLblSeat(JLabel lblSeat) {
		this.lblSeat = lblSeat;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public void setLblLefttime(JLabel lblLefttime) {
		this.lblLefttime = lblLefttime;
	}

	public void setLblStarttime(JLabel lblStarttime) {
		this.lblStarttime = lblStarttime;
	}
	
	
}
