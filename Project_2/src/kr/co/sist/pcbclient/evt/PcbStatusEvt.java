package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.dao.PcbUserDAO;
import kr.co.sist.pcbclient.form.PcbOrdFrm;
import kr.co.sist.pcbclient.form.PcbStatusFrm;
import kr.co.sist.pcbclient.form.PcbUserLoginFrm;
import kr.co.sist.pcbclient.server.PcbStatusServer;

public class PcbStatusEvt extends WindowAdapter implements ActionListener, Runnable {
		
	private PcbStatusFrm psf  ;
	private String SendMsg;
	Thread timeThread;
	private String id;
	private int left_time ;
	private PcbUserLoginFrm pulf;
	private PcbUserDAO pu_dao = PcbUserDAO.getInstance();
	private final static int MSG=0;
	private final static int START=1;
	private final static int END=2;
	
	private Properties prop;
	private int socketPort;
	private int seatNumber;
	private String serverIp;
	private PcbStatusServer pss;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbUserLoginFrm pulf) {
		this.psf = psf;
		this.pulf = pulf;//ȸ�� ��ȸ�� �Ǵ��� ���ؼ�
		
		try {
			prop = new Properties();
			prop.load(new FileReader(System.getProperty("user.dir")+"/Project_2/src/kr/co/sist/pcbclient/dao/database.properties"));
			socketPort=Integer.parseInt(prop.getProperty("socketPort"));
			seatNumber=Integer.parseInt(prop.getProperty("seatsNum"));
			serverIp=prop.getProperty("serverIp");
			
			//������ ������ �˸�������
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(START);//������ ���ϸ� ������
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(psf, "�������� ������ �������� �ʽ��ϴ�.");
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(psf, "���������� �����߻�.");
			e1.printStackTrace();
		}//catch
		
		 id = pulf.getTfUserId().getText();
		 //������»Ѹ���
		 setStatus();
		 
		 //������������
		pss=new PcbStatusServer(psf, this);
		
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
			pu_dao.logout(psf.getLblStarttime().getText());//�α׾ƿ� db����
			
			//������ ���Ḧ �˸�������
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(END);//������ ���ϸ� ������
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(psf, "������� �� �����߻�");
			se.printStackTrace();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		timeThread.stop();
		System.exit(JFrame.ABORT);
		super.windowClosed(e);
	}

	@Override
	public void run() {
		while(true){ 
            try { 
            	if(left_time==0) {
            		JOptionPane.showMessageDialog(psf, "���ð��� ��� �Ҹ� �Ǿ����ϴ�. \n�����Ͻ÷��� ī���Ϳ� �������ּ���.\n30���� �ڵ�����˴ϴ�.");
            		left_time=0;
            		
            		Thread.sleep(1000*30);
            		psf.dispose();
            		
            	}//end if
            	//�ð��� �ִ��� �����
            	//�ִٸ� ����
            	
            	Thread.sleep(1000*60); 

            	//�ð����� 
                 if(pulf.getCbMem().getState()){
						pu_dao.updateTime(id); // ȸ�� �ð� ������Ʈ
				}else if( pulf.getCbNoMem().getState()) {
						pu_dao.noUpdateTime(id); //��ȸ�� �ð� ������Ʈ
				}//end if else
                 psf.getLblSeat().setText("�¼�");
                 setStatus();
            } catch (InterruptedException e) {
            	JOptionPane.showMessageDialog(null, "�����߻�");
            } catch (SQLException e) {
				e.printStackTrace();
			}
		 }//while
             
	}//run
	
	public void setStatus() { //������»Ѹ���
		
		PcbUserDAO pu_dao=PcbUserDAO.getInstance();
		
		try {
			psf.getLblId().setText(id);//���̵� ����
			psf.getLblStarttime().setText(pu_dao.setUser());//���۽ð� ����
			psf.getLblSeat().setText(psf.getLblSeat().getText()+(seatNumber+1));//�¼���ȣ ����

			if(pulf.getCbMem().getState()){//ȸ�� ��ȸ�� �Ǵ� �� �����ð� ����
					left_time = pu_dao.userGetTime(id);
			}else{
					left_time = pu_dao.noUserGetTime(id);
					psf.getLblId().setText(id+"(��ȸ��)");
			}//else
			psf.getLblLefttime().setText(String.valueOf(left_time+" ��"));	
			
		} catch (SQLException e) {
			//ȸ��
			e.printStackTrace();
		}//try
	}//setStatus
	
	public void endRequest() {
		switch(JOptionPane.showConfirmDialog(psf, "����� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			psf.dispose();
		};
	}
	public void sendMsg() throws FileNotFoundException, IOException {
		SendMsg = JOptionPane.showInputDialog("�����ڿ��� ���� �޽����� �����ּ���.");
		
		try {
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			StringBuffer sbMsg=new StringBuffer();
			sbMsg.append("�¼�").append(seatNumber+1).append(" : ").append(SendMsg);
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(MSG);//������ ���ϸ� ������
			dos.writeUTF(sbMsg.toString());//������ ���ϸ� ������
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void PcbOrdView() {
		new PcbOrdFrm(psf);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==psf.getBtnMsg()) {
			try {
				sendMsg();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getSource()==psf.getBtnOrd()) {
			PcbOrdView();
		}
		
		if(ae.getSource()==psf.getBtnEnd()) {
			endRequest();
		}
	}
		
}//class
