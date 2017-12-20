 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;
import kr.co.sist.pcbmaster.vo.SetUserVO;

public class PcbStatusEvt implements Runnable,ActionListener {
	private PcbStatusFrm psf;
	private PcbMasterMainEvt pmme;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		super();
		this.psf = psf;
		this.pmme = pmme;
		setUser();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource()==psf.getAddTime()) {//�ð��߰� ��ư�� Ŭ������ ��
			addTime("test");
			pmme.setSeats();//�ð��߰� ���ΰ�ħ
		}//end if
		
		if(ae.getSource()==psf.getSendMsg()) { //�޽��� ������ ��ư�� Ŭ������ ��
			JOptionPane.showInputDialog("���� �޽����� �Է��ϼ���.");
		}//end if
		
		if(ae.getSource()==psf.getUseEnd()) { //������� ��ư�� Ŭ������ ��
			switch (JOptionPane.showConfirmDialog(psf, "�����Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
					psf.dispose();
			}//end switch
		}//end if
		
	}//actionPerformed

	@Override
	public void run() {
		
	}//run

	public void addTime(String id) {
		/*1219 �¼��� �����Ҷ������� �˻��ϱ�
		 * 	�¼��� ��������� Y�� �¼��� ��ȣ�� �̿��Ͽ� �ش��ϴ� ���̵�� ��¼������
		 * */
		
		JTextArea result = new JTextArea();
		JScrollPane jsp = new JScrollPane(result);
		result.append("�Ʒ��� ������ �½��ϱ�??\n");
		result.append("--------------------------------------------------------------------------------------------------------------------\n");
		result.append("                                                  ���̵� : "+"\n");
		result.append("                                                  �ð� : "+"\n");
		result.append("                                                  �� �ݾ� : "+"\n");
		result.append("                                                  ��û�ð� : "+"\n");
		JOptionPane.showConfirmDialog(psf, jsp); //showMessageDialog�� ���
	}//addTime
	
	public void setUser() {
		PcbDAO p_dao=PcbDAO.getInstance();
		try {
			SetUserVO suv=p_dao.setUser();
			String text = psf.getLblId().getText();
			psf.getLblId().setText(text+suv.getId());
			psf.getLblName().setText(text+suv.getName());
			psf.getLblTime().setText(text+suv.getLeftTime());
		} catch (SQLException e) {
			System.out.println("��������� �ε� ����");
			e.printStackTrace();
		}
	}//setUser 
	
	
}//class
