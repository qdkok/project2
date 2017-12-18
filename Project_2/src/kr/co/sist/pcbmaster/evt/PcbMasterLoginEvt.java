package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbMasterLoginFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.PcbMasterLoginVO;


public class PcbMasterLoginEvt extends WindowAdapter implements ActionListener {
	private PcbMasterLoginFrm pmlf;
	private Map<String,String> admin;
	
	public PcbMasterLoginEvt(PcbMasterLoginFrm pmlf) {
		super();
		this.pmlf = pmlf;
		
		//�����ڰ� ���� �� �ִ� ���̵�� ��й�ȣ,
		admin = new HashMap<String,String>(2);
		admin.put("1", "1");
	}//PcbMasterLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) { // LoginFrm ���� ��ư ������ �� �۵��ϴ� �͵� {
		if(ae.getSource()==pmlf.getBtnLogin() || ae.getSource() == pmlf.getTfPass()) {
			checkLogin();
		}//end if 
		//�ݱ� ��ư�� ���� �� �� ��ư�� ������ ����
		if(ae.getSource()==pmlf.getBtnClose()) {
			switch (JOptionPane.showConfirmDialog(pmlf, "�����Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:
				pmlf.dispose();
			}//end switch
		}//end if
	}//actionPerformed
	
	public void checkLogin() {
		
		String id = pmlf.getTfID().getText();
		String pass = pmlf.getTfPass().getText();

		PcbDAO p_dao=PcbDAO.getInstance();
		
		try {
			if(p_dao.masterLogin(new PcbMasterLoginVO(id, pass))) {
				new PcbMasterMainFrm();
				pmlf.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(pmlf, "���̵�� ��й�ȣ�� Ȯ���ϼ���");
			}//end else
		} catch (SQLException e) {
			System.out.println("�ù�");
			e.printStackTrace();
		}
		
	}//checkLogin

	@Override
	public void windowClosing(WindowEvent we) {
		pmlf.dispose();
	}

	@Override
	public void windowClosed(WindowEvent wwe) {
	
	}
	
	
	
}//class
