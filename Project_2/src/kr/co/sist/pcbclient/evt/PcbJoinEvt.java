package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.dao.PcbUserDAO;
import kr.co.sist.pcbclient.form.PcbJoinFrm;
import kr.co.sist.pcbclient.vo.PcbUserJoinVO;

public class PcbJoinEvt implements ActionListener {
	private PcbJoinFrm pjf;
	
	public PcbJoinEvt(PcbJoinFrm pjf) {
		this.pjf = pjf;
	}
	
	public boolean chkId() {
		boolean clickFlag = false;
		try {
			PcbUserDAO pu_dao = PcbUserDAO.getInstance();
			if(pu_dao.userIdChk(pjf)) {
				JOptionPane.showMessageDialog(pjf, "��밡���� ID�Դϴ�.");
				clickFlag = true;
			}else {
				JOptionPane.showMessageDialog(pjf, "�ߺ��� ID�� �����մϴ�.");
				clickFlag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clickFlag;
	}
	
	public void join() {
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		String id = pjf.getTfUserId().getText();
		String pass = new String(pjf.getTfUserPass().getPassword());
		String passChk = new String(pjf.getTfUserPassChk().getPassword());
		String name = pjf.getTfUserName().getText();
		String phone = pjf.getCbmPhNum().getSelectedItem() + pjf.getTfUserPh().getText();
		String email = pjf.getTfUserEm_addr().getText() + "@"+ pjf.getCbmEmail().getSelectedItem();
		
		try {
			//ȸ������ ��ȿ�� �˻�
			if(pu_dao.userIdChk(pjf)) {
				if("".equals(id) && pass.equals(passChk) && !pass.equals("") && !name.equals("") && pjf.getTfUserPh().getText().length() == 8 
						&& !pjf.getTfUserEm_addr().getText().equals("")) {
							
					PcbUserJoinVO pujvo = new PcbUserJoinVO(id, pass, name, phone, email);
					pu_dao.userJoin(pujvo);
					
					JOptionPane.showMessageDialog(pjf, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
					pjf.dispose();
				}else {
					JOptionPane.showMessageDialog(pjf, "�Է°��� �ٽ� Ȯ�����ּ���.");
					pjf.getTfUserPass().setText("");
					pjf.getTfUserPassChk().setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//join

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pjf.getBtnJoin()) {
			if(chkId()) {
				join();
			}else {
				JOptionPane.showMessageDialog(pjf, "ID�ߺ�Ȯ���� ���ּ���.");
			}
		}
		
		if(ae.getSource() == pjf.getBtnCancle()) {
			pjf.dispose();
		}
		
		if(ae.getSource() == pjf.getBtnchkId()) {
			chkId();
		}
		
	}	
}
