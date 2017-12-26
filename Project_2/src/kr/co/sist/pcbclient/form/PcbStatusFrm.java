package kr.co.sist.pcbclient.form;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import kr.co.sist.pcbclient.evt.PcbStatusEvt;

@SuppressWarnings("serial")
public class PcbStatusFrm extends JFrame {
	private PcbUserLoginFrm pulf;
	private JButton btnOrd, btnMsg, btnEnd;
	private JLabel lblSeat, lblId, lblLefttime, lblStarttime;
	
	public PcbStatusFrm(PcbUserLoginFrm pulf) {
		this.pulf = pulf;
		lblSeat = new JLabel("좌석2");
		lblId = new JLabel("Alpha34");
		JLabel textStart = new JLabel("시작시간");
		JLabel textLeft = new JLabel("남은시간");
		lblLefttime = new JLabel("");
		lblStarttime = new JLabel("");
		btnOrd = new JButton("음식주문");
		btnMsg = new JButton("메시지송신");
		btnEnd = new JButton("사용종료");
		
		setLayout(null);
		
		lblSeat.setBounds(90, 40, 50, 30);
		lblId.setBounds(200, 40, 50, 30);
		textStart.setBounds(90, 130, 100, 20);
		textLeft.setBounds(90, 180, 100, 20);
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
		
		PcbStatusEvt pse = new PcbStatusEvt(this,pulf);
		btnOrd.addActionListener(pse);
		btnMsg.addActionListener(pse);
		btnEnd.addActionListener(pse);
		addWindowListener(pse);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
