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
	
	public void chkId() {
		try {
			PcbUserDAO pu_dao = PcbUserDAO.getInstance();
			pu_dao.userIdChk(pjf);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			//회원가입 유효성 검사
			if(pu_dao.userIdChk(pjf)) {
				if(pass.equals(passChk) && !pass.equals("")
						&& !name.equals("") && pjf.getTfUserPh().getText().length() == 8 
						&& !pjf.getTfUserEm_addr().getText().equals("")) {
							
					PcbUserJoinVO pujvo = new PcbUserJoinVO(id, pass, name, phone, email);
					pu_dao.userJoin(pujvo);
					
					JOptionPane.showMessageDialog(pjf, "회원가입이 완료되었습니다.");
					pjf.dispose();
				}else {
					JOptionPane.showMessageDialog(pjf, "입력값을 다시 확인해주세요.");
				}
			}else {
				JOptionPane.showMessageDialog(pjf, "ID 중복확인을 해주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//join

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pjf.getBtnJoin()) {
			join();
		}
		
		if(ae.getSource() == pjf.getBtnCancle()) {
			pjf.dispose();
		}
		
		if(ae.getSource() == pjf.getBtnchkId()) {
			chkId();
		}
		
	}	
}
