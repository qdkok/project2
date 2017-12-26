package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.dao.PcbUserDAO;
import kr.co.sist.pcbclient.form.PcbJoinFrm;
import kr.co.sist.pcbclient.form.PcbStatusFrm;
import kr.co.sist.pcbclient.form.PcbUserLoginFrm;

public class PcbUserLoginEvt implements ActionListener, ItemListener {
	private PcbUserLoginFrm pulf;
	private int seatPri;
	
	public PcbUserLoginEvt(PcbUserLoginFrm pulf) {
		this.pulf = pulf;
	}//������

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == pulf.getBtnJoin()) {
			join();
		}
		if(ae.getSource() == pulf.getBtnLogin()) {
			try {
				login();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}//actionPerformed
	
	public void checkLogin() { //�α���üũ
		
	}//checkLogin
	
	public void join() {
		new PcbJoinFrm(pulf);
	}

	public void login() throws SQLException {
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
	
		String id = pulf.getTfUserId().getText();
		String password = new String(pulf.getTfUserPass().getPassword());
		
		if(pulf.getCbMem().getState()==true && !id.equals("") && !password.equals("")) { //ȸ�� üũ�Ұ�� 
			
				if(pu_dao.userLogin(pulf).equals("N")) {
					JOptionPane.showMessageDialog(pulf, "���̵� ��й�ȣ�� Ʋ���ϴ�.");
					return;
				}//end if
				
				if(pu_dao.userLogin(pulf).equals("L")) {
					JOptionPane.showMessageDialog(pulf, "�̹� ������� ���̵� �Դϴ�.");
					return;
				}//end if
				
				if(pu_dao.userGetTime(id) == 0) {
					JOptionPane.showMessageDialog(pulf, "������ ��� �ð��� �����ϴ�. ī���Ϳ� �����Ͽ� �������ּ���.");
					return;
				}//end if
				
				pu_dao.useSeats(pulf.getTfUserId().getText());
				new PcbStatusFrm(pulf);
				pulf.dispose();
		}else if(pulf.getCbNoMem().getState() == true && !id.equals("") ){ //��ȸ�� üũ�Ұ��
				if(pu_dao.noUserLogin(pulf).equals("N")) {
					JOptionPane.showMessageDialog(pulf, "��ȸ����ȣ�� Ȯ�����ּ���.");
					return;
				}//end if
				if(pu_dao.noUserLogin(pulf).equals("L")) {
					JOptionPane.showMessageDialog(pulf, "�̹� ������� ���̵� �Դϴ�.");
					return;
				}//end if
				if(pu_dao.noUserGetTime(id) == 0) {
					JOptionPane.showMessageDialog(pulf, "������ ��� �ð��� �����ϴ�. ī���Ϳ� �����Ͽ� �������ּ���.");
					return;
				}//end if
				
				pu_dao.useSeats(pulf.getTfUserId().getText());
				new PcbStatusFrm(pulf);
				pulf.dispose();
		}else {
			JOptionPane.showMessageDialog(pulf, "���̵�� ��й�ȣ�� �Է����ּ���.");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getSource() == pulf.getCbNoMem()) {
			pulf.getLblPW().setVisible(false);
			pulf.getTfUserPass().setVisible(false);
		}
		if(ie.getSource() == pulf.getCbMem()) {
			pulf.getLblPW().setVisible(true);
			pulf.getTfUserPass().setVisible(true);
		}
	}
}//class
