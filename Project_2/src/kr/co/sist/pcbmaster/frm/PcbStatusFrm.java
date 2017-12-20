package kr.co.sist.pcbmaster.frm;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;
import kr.co.sist.pcbmaster.evt.PcbMasterServer;
import kr.co.sist.pcbmaster.evt.PcbStatusEvt;

@SuppressWarnings("serial")
public class PcbStatusFrm extends JDialog  {
	
	private PcbMasterMainEvt pmme;
	private PcbMasterMainFrm pmmf;
	private JButton addTime,sendMsg,useEnd;
	private JLabel lblSeatNum,lblId,lblName,lblTime;
	private DefaultComboBoxModel<String> dcbm;
	private JComboBox<String> jcbTime;
	
	public PcbStatusFrm(PcbMasterMainEvt pmme, PcbMasterMainFrm pmmf,String seatNum,PcbMasterServer pms) {
		super(pmmf,"사용자관리",true);
		this.pmme = pmme;
		this.pmmf = pmmf;
		lblSeatNum = new JLabel("좌석"+(Integer.parseInt(seatNum)+1));
		lblId = new JLabel("ID : ");
		lblName = new JLabel("사용자 : ");
		lblTime = new JLabel("남은시간 : ");
		
		addTime =  new JButton("시간추가");
		sendMsg = new JButton("메시지 보내기");
		useEnd = new JButton("사용종료");
		
		Font lblSeatNumFont=new Font("맑은고딕", Font.BOLD, 80);
		
		dcbm=new DefaultComboBoxModel<>();
		String[] time = new String[] {"1:00","2:00","3:00","4:00","5:00"};
		for(String addt :time) {
			dcbm.addElement(addt);
		}
		
		jcbTime=new JComboBox<String>(dcbm);
		
		setLayout(new BorderLayout());
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(null);
		pCenter.add(lblId);
		pCenter.add(lblName);
		pCenter.add(lblTime);
		pCenter.add(addTime);
		pCenter.add(jcbTime);

		jcbTime.setBounds(100, 330, 100, 30);
		
		add(BorderLayout.CENTER,pCenter);
		
		add(BorderLayout.NORTH, lblSeatNum);
		
		lblSeatNum.setFont(lblSeatNumFont);
		lblSeatNum.setBounds(20,20,100,500);
		
		lblId.setBounds(100, 30, 100, 100);
		lblName.setBounds(100, 130, 100, 100);
		lblTime.setBounds(100, 230, 100, 100);
		
		addTime .setBounds(300, 330, 100, 30);
		
		JPanel pSouth = new JPanel();
		pSouth.setLayout(new GridLayout(1, 2));
		pSouth.add(sendMsg);
		pSouth.add(useEnd);

		add(BorderLayout.SOUTH,pSouth);
		
		setBounds(700,80,500,700);
		PcbStatusEvt pse = new PcbStatusEvt(this, pmmf, pmme, seatNum, pms);
	    
	    
	    addTime.addActionListener(pse);
	    sendMsg.addActionListener(pse);
	    useEnd.addActionListener(pse);
	    addWindowListener(pse);
	    
	    setVisible(true);
	    
	}//PcbStatusFrm

	public PcbMasterMainEvt getPmme() {
		return pmme;
	}

	public void setPmme(PcbMasterMainEvt pmme) {
		this.pmme = pmme;
	}

	public PcbMasterMainFrm getPmmf() {
		return pmmf;
	}

	public void setPmmf(PcbMasterMainFrm pmmf) {
		this.pmmf = pmmf;
	}

	public JButton getAddTime() {
		return addTime;
	}

	public void setAddTime(JButton addTime) {
		this.addTime = addTime;
	}

	public JButton getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(JButton sendMsg) {
		this.sendMsg = sendMsg;
	}

	public JButton getUseEnd() {
		return useEnd;
	}

	public void setUseEnd(JButton useEnd) {
		this.useEnd = useEnd;
	}

	public JLabel getLblSeatNum() {
		return lblSeatNum;
	}

	public void setLblSeatNum(JLabel lblSeatNum) {
		this.lblSeatNum = lblSeatNum;
	}

	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JLabel getLblTime() {
		return lblTime;
	}

	public void setLblTime(JLabel lblTime) {
		this.lblTime = lblTime;
	}

	public JComboBox<String> getJcbTime() {
		return jcbTime;
	}

	
}//class


