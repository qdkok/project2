 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;

public class PcbStatusEvt implements Runnable,ActionListener {
	private PcbStatusFrm psf;
	private PcbMasterMainEvt pmme;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		super();
		this.psf = psf;
		this.pmme = pmme;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		JTextArea result = new JTextArea();
		JScrollPane jsp = new JScrollPane(result);
		if(ae.getSource()==psf.getAddTime()) {//�ð��߰� ��ư�� Ŭ������ ��
			result.append("�Ʒ��� ������ �½��ϱ�??\n");
			result.append("--------------------------------------------------------------------------------------------------------------------\n");
			result.append("                                                  ���̵� : "+"\n");
			result.append("                                                  �ð� : "+"\n");
			result.append("                                                  �� �ݾ� : "+"\n");
			result.append("                                                  ��û�ð� : "+"\n");
			JOptionPane.showConfirmDialog(psf, jsp); //showMessageDialog�� ���
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
		
	}//addTime
	
	public void setUser() {
		
	}//setUser 
	
	
}//class
