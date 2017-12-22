package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import jdk.nashorn.internal.scripts.JO;
import kr.co.sist.pcbclient.dao.PcbUserDAO;
import kr.co.sist.pcbclient.form.PcbOrdFrm;
import kr.co.sist.pcbclient.form.PcbStatusFrm;
import kr.co.sist.pcbclient.form.PcbUserLoginFrm;

public class PcbStatusEvt extends WindowAdapter implements ActionListener, Runnable {
		
	private PcbStatusFrm psf  ;
	private String SendMsg;
	private String sb;
	private int addTime;
	private int addHH = 0;
	Thread timeThread;
	
	public PcbStatusEvt(PcbStatusFrm psf) {
		this.psf = psf;

		PcbUserDAO pu_dao=PcbUserDAO.getInstance();
		 try {//���ºҷ�����
				psf.getLblStarttime().setText(pu_dao.setUser());
			} catch (SQLException e1) {
				e1.printStackTrace();
		}
		
		timeThread = new Thread(this);
		timeThread.start(); ////////////////Thread
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		psf.dispose();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void windowClosed(WindowEvent e) {
		PcbUserDAO pu_dao=PcbUserDAO.getInstance();
		try {
			pu_dao.logout(psf.getLblStarttime().getText());
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(psf, "������� �� �����߻�");
			se.printStackTrace();
		}
		timeThread.stop();
		super.windowClosed(e);
	}



	@Override
	public void run() {
		
		////�����ڿ��� �����ð� �ް� �ð� -1 ������ ����
		int sTime = (int)System.currentTimeMillis()/10;
		
		 while(true){ 
             int cTime = (int)System.currentTimeMillis()/10; 
             int rTime = cTime - sTime; 
             int mm1 = rTime %100; 
             int mm = rTime/100 ; 
             int ss = mm % 60; 
             int MM = mm / 60 % 60; 
             int HH = mm / 3600; 
             sb = (HH + addHH) + ":"+ MM +":"+ss+"."+mm1;
             
            
             
             psf.getLblLefttime().setText(sb);
             try { 
                  Thread.sleep(1000); 
             } catch (InterruptedException e) {
             	JOptionPane.showMessageDialog(null, "�����߻�");
             }
        }
	}//run
	
	public void setStatus() { //������»Ѹ���
		
	}
	
	public String seatChk() { //�¼���ȣ �Ѹ���
		return "";
	}
	
	public String time() {
		return "";
	}
	
	public void endRequest() {
		switch(JOptionPane.showConfirmDialog(psf, "����� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			
			psf.dispose();
		};
	}
	
	public void sendMsg() {
		SendMsg = JOptionPane.showInputDialog("�����ڿ��� ���� �޽����� �����ּ���.");
	}

	public void PcbOrdView() {
		PcbOrdFrm pof = new PcbOrdFrm(psf);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psf.getBtnMsg()) {
			sendMsg();
		}
		
		if(ae.getSource()==psf.getBtnOrd()) {
			PcbOrdView();
		}
		
		if(ae.getSource()==psf.getBtnEnd()) {
			endRequest();
		}
	}
		
}//class
