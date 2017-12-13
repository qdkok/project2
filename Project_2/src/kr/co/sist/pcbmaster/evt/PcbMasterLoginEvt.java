package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import kr.co.sist.pcbmaster.frm.PcbMasterLoginFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;


public class PcbMasterLoginEvt extends WindowAdapter implements ActionListener {
	private PcbMasterLoginFrm pmlf;
	private Map<String,String> admin;
	
	public PcbMasterLoginEvt(PcbMasterLoginFrm pmlf) {
		super();
		this.pmlf = pmlf;
		
		//관리자가 들어올 수 있는 아이디와 비밀번호,
		admin = new HashMap<String,String>(2);
		admin.put("1", "1");
	}//PcbMasterLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) { // LoginFrm 에서 버튼 눌렀을 때 작동하는 것들 {
		if(ae.getSource()==pmlf.getBtnLogin() || ae.getSource() == pmlf.getTfPass()) {
			checkLogin();
		}//end if 
		//닫기 버튼을 누른 후 예 버튼을 누르면 종료
		if(ae.getSource()==pmlf.getBtnClose()) {
			switch (JOptionPane.showConfirmDialog(pmlf, "종료하시겠습니까?")) {
			case JOptionPane.OK_OPTION:
				pmlf.dispose();
			}//end switch
		}//end if
	}//actionPerformed
	
	public void checkLogin() {
		String id = pmlf.getTfID().getText();
		String pass = pmlf.getTfPass().getText();
		
		if(admin.get(id)!=null && admin.get(id).equals(pass)) {
			new PcbMasterMainFrm();
			pmlf.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(pmlf, "아이디와 비밀번호를 확인하세요");
		}//end else
		
	}//checkLogin

	@Override
	public void windowClosing(WindowEvent we) {
		pmlf.dispose();
	}

	@Override
	public void windowClosed(WindowEvent wwe) {
	
	}
	
	
	
}//class
