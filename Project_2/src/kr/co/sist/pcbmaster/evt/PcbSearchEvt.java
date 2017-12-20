package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbSearchFrm;
import kr.co.sist.pcbmaster.vo.AddTimeVO;
import kr.co.sist.pcbmaster.vo.SearchIdVO;

public class PcbSearchEvt implements ActionListener {
	private PcbSearchFrm psf;
	private String id;

	public PcbSearchEvt(PcbSearchFrm psf) {
		super();
		this.psf = psf;
	}//PcbSearchEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		if(psf.getBtnSearch() == e.getSource()) {
			search();
		}//end if
		if(psf.getBtnAddTime() == e.getSource()) {
			addTime();
		}//end if
	}//actionPerformed
	
	public void search() {
		PcbDAO p_dao = PcbDAO.getInstance();
		id = psf.getSearchId().getText();
		if(id.trim().equals("")) { //id���� �����ϰ��
			JOptionPane.showMessageDialog(psf, "���̵� �Է����ּ���");
		}//end if
			try {
				SearchIdVO sid=p_dao.searchId(id);
				String name = sid.getName();
				int leftTime = sid.getLeftTime();
				
				psf.getJlName().setText("���̸� : "+name);
				psf.getJlTime().setText("�ݳ��� �ð� : "+leftTime+"��");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(psf, "�������� �ʴ� ���̵� �Դϴ� Ȯ�����ּ���.");
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			} //catch	
	}//search
	
	public void addTime() {

		if(id.trim().equals("")) {		//���̵� ã�⸦ ���� ���� ���
			JOptionPane.showMessageDialog(psf, "���̵� ���� �˻��� �ּ���");
			return;
		}//end if
		
		JComboBox<String> jcb = psf.getJcbAdTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date time=new Date(System.currentTimeMillis());
		
		int selTime=0;
		switch (jcb.getSelectedIndex()) {
		case 0:
			selTime=60;
			break;
		case 1:
			selTime=120;
			break;
		case 2: 
			selTime=180;
			break;
		case 3: 
			selTime=240;
			break;
		case 4: 
			selTime=300;
			break;
		}//switch
		
		JTextArea result = new JTextArea();
		JScrollPane jsp = new JScrollPane(result);
		result.append("�Ʒ��� ������ �½��ϱ�??\n");
		result.append("--------------------------------------------------------------------------------------------------------------------\n");
		result.append("                                                  ���̵� : "+ id +"\n");
		result.append("                                                  �ð� : "+selTime+"��("+(selTime/60)+" �ð�)\n");
		result.append("                                                  �� �ݾ� : "+((selTime/60)*1000)+" ��\n");
		result.append("                                                  ��û�ð� : "+sdf.format(time)+"\n");

		switch (JOptionPane.showConfirmDialog(psf, jsp)) {
		case JOptionPane.OK_OPTION:		
			PcbDAO p_dao=PcbDAO.getInstance();
			try {
				p_dao.addTime(new AddTimeVO(id, selTime));
				JOptionPane.showMessageDialog(psf, "�ð��߰� �Ϸ�Ǿ����ϴ�.");
				psf.getJlName().setText("���̸� : ");
				psf.getJlTime().setText("�ݳ��� �ð� : ");
				id="";//���̵� �ʱ�ȭ
			} catch (SQLException e) {
				System.out.println("�ð��߰� ���� �����߻�");
				e.printStackTrace();
			}//catch
		}//switch			
			
			
			
		
	}//addTime
	
}//class
