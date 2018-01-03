package kr.co.sist.pcbmaster.frm;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.evt.PcbSearchEvt;
import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

@SuppressWarnings("serial")
public class PcbSearchFrm extends JDialog {
	private PcbMasterMainEvt pmme;
	private JTextField searchId; //아이디 검색
	private JButton btnSearch,btnAddTime;//검색 버튼, 시간추가
	private JLabel jlName,jlTime;
	private DefaultComboBoxModel<String> addTime;
	private JComboBox<String> jcbAdTime;
	
	public PcbSearchFrm(PcbMasterMainEvt pmme) {
		this.pmme=pmme;
		
		btnSearch= new JButton("검색");
		btnSearch.setBackground(new Color(0x282827));
		btnSearch.setForeground(Color.white);
		btnAddTime = new JButton("시간추가");
		btnAddTime.setBackground(new Color(0x282827));
		btnAddTime.setForeground(Color.white);
		searchId = new JTextField();
		jlName = new JLabel("◎이름 : ");
		jlTime = new JLabel("◎남은 시간 : ");
		jlName.setForeground(Color.WHITE);
		jlTime.setForeground(Color.WHITE);
		
		addTime=new DefaultComboBoxModel<>();
		String[]time= new String[] {"1:00","2:00","3:00","4:00","5:00"};
		for(String adtime:time) {
			addTime.addElement(adtime);
		}//end for
		
		jcbAdTime=new JComboBox<String>(addTime);
		
		setLayout(null);
		add(searchId);
		add(jcbAdTime);
		
		add(btnSearch);
		add(btnAddTime);
		
		add(jlName);
		add(jlTime);
		
		searchId.setBounds(15, 15, 230, 30); //아이디 검색
		btnSearch.setBounds(270, 15, 90, 30);//검색 버튼
		
		jlName.setBounds(15,25,150,100);
		jlTime.setBounds(15,40,170,100);
		
		jcbAdTime.setBounds(162, 70, 85, 25);//시간추가
		btnAddTime.setBounds(270,70,90,30);//시간추가 버튼
		
		PcbSearchEvt pse = new PcbSearchEvt(this);
		btnSearch.addActionListener(pse);
		btnAddTime.addActionListener(pse);
		
		getContentPane().setBackground(new Color(0x6C6C6C));
		setVisible(true);
		setResizable(false);
		setBounds(500, 300, 390, 150);
	}//PcbSearchFrm

	public JTextField getSearchId() {
		return searchId;
	}

	public void setSearchId(JTextField searchId) {
		this.searchId = searchId;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnAddTime() {
		return btnAddTime;
	}

	public void setBtnAddTime(JButton btnAddTime) {
		this.btnAddTime = btnAddTime;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public void setJlName(JLabel jlName) {
		this.jlName = jlName;
	}

	public JLabel getJlTime() {
		return jlTime;
	}

	public void setJlTime(JLabel jlTime) {
		this.jlTime = jlTime;
	}

	public PcbMasterMainEvt getPmme() {
		return pmme;
	}

	public DefaultComboBoxModel<String> getAddTime() {
		return addTime;
	}

	public JComboBox<String> getJcbAdTime() {
		return jcbAdTime;
	}
	
}
