 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;
import kr.co.sist.pcbmaster.vo.AddTimeVO;
import kr.co.sist.pcbmaster.vo.SetUserVO;

public class PcbStatusEvt extends WindowAdapter implements Runnable,ActionListener {
	private PcbStatusFrm psf;
	private PcbMasterMainEvt pmme;
	private SetUserVO suv;
	private String seatNum;
	Thread setting;
	int flagnum;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		super();
		this.psf = psf;
		this.pmme = pmme;
		flagnum= Integer.parseInt(seatNum);//��ȸ�� �ľ� �÷���
		this.seatNum="seat_"+ (seatNum.length()==1?"0"+seatNum:seatNum);
		setUser(this.seatNum);
		setting = new Thread(this);
		setting.start();
	}//PcbStatusEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==psf.getAddTime()) {//�ð��߰� ��ư�� Ŭ������ ��
			addTime(suv.getId());
			pmme.setSeats();//�ð��߰� ���ΰ�ħ
		}//end if
		
		if(ae.getSource()==psf.getSendMsg()) { //�޽��� ������ ��ư�� Ŭ������ ��
			JOptionPane.showInputDialog("���� �޽����� �Է��ϼ���.");
		}//end if
		
		if(ae.getSource()==psf.getUseEnd()) { //������� ��ư�� Ŭ������ ��
			switch (JOptionPane.showConfirmDialog(psf, "�����Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:
					stopUser();
					pmme.setSeats();
					psf.dispose();
			}//end switch
		}//end if
		
	}//actionPerformed
	
	public void stopUser() {
		
		PcbDAO p_dao = PcbDAO.getInstance();
		try {
			p_dao.seatUpdate(seatNum);
			JOptionPane.showMessageDialog(psf, seatNum+"�¼��� ����� ����Ǿ����ϴ�.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//end stopUser
	

	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000 *60);
				setUser(this.seatNum);
			}//while
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//run
	
	@Override
	public void windowClosing(WindowEvent e) {
		psf.dispose();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void windowClosed(WindowEvent e) {
		setting.stop();
		super.windowClosed(e);
	}

	public void addTime(String id) {
		/*1219 �¼��� �����Ҷ������� �˻��ϱ�
		 * 	�¼��� ��������� Y�� �¼��� ��ȣ�� �̿��Ͽ� �ش��ϴ� ���̵�� ��¼������
		 * */
		JComboBox<String> jcb = psf.getJcbTime();
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
				p_dao.addTime(new AddTimeVO(id, selTime),pmme.getMemFlag()[flagnum]);
				JOptionPane.showMessageDialog(psf, "�ð��߰� �Ϸ�Ǿ����ϴ�.");
				setUser(seatNum);//�ð��߰� ���ΰ�ħ
			} catch (SQLException e) {
				System.out.println("�ð��߰� ���� �����߻�");
				e.printStackTrace();
			}//catch
		}//switch
		
		
	}//addTime
	
	public void setUser(String seatNum) {
		PcbDAO p_dao=PcbDAO.getInstance();
		try {
			
			suv=p_dao.setUser(seatNum,pmme.getMemFlag()[flagnum]);
			psf.getLblId().setText("ID : "+ suv.getId());
			psf.getLblName().setText("����ڸ� : "+suv.getName());
			psf.getLblTime().setText("�����ð� : "+suv.getLeftTime()+"��");
		} catch (SQLException e) {
			System.out.println("��������� �ε� ����");
			e.printStackTrace();
		}
	}//setUser 
	
	
}//class
