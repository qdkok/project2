package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Properties;

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
	private String id;
	private int left_time ;
	private PcbUserLoginFrm pulf;
	private PcbUserDAO pu_dao = PcbUserDAO.getInstance();
	private Socket ss;
	private final static int MSG=0;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbUserLoginFrm pulf) {
		this.psf = psf;
		this.pulf = pulf;//회원 비회원 판단을 위해서

		PcbUserDAO pu_dao=PcbUserDAO.getInstance();
		
		
		
		 id = pulf.getTfUserId().getText(); 
			try {
				psf.getLblId().setText(pulf.getTfUserId().getText());//아이디 설정
				psf.getLblStarttime().setText(pu_dao.setUser());//시작시간 설정

				if(pulf.getCbMem().getState()){//회원 비회원 판단 후 남은시간 설정
						left_time = pu_dao.userGetTime(id);
				}else{
						left_time = pu_dao.noUserGetTime(id);
				}//else
				
				psf.getLblLefttime().setText(String.valueOf(left_time+" 분"));	
				
			} catch (SQLException e) {
				//회원
				e.printStackTrace();
			}//try
		 
		
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
			JOptionPane.showMessageDialog(psf, "사용종료 중 문제발생");
			se.printStackTrace();
		}
		timeThread.stop();
		super.windowClosed(e);
	}



	@Override
	public void run() {
		
		////관리자에게 남은시간 받고 시간 -1 씩으로 수정
//		int sTime = (int)System.currentTimeMillis()/10;
		
		/* while(true){ 
             int cTime = (int)System.currentTimeMillis()/10; 
             int rTime = cTime - sTime; 
             int mm1 = rTime %100; 
             int mm = rTime/100 ; 
             int ss = mm % 60; 
             int MM = mm / 60 % 60; 
             int HH = mm / 3600; 
             sb = (HH + addHH) + ":"+ MM +":"+ss+"."+mm1;*/
		
		while(true){ 
            try { 
            	if(left_time==0) {
            		JOptionPane.showMessageDialog(psf, "사용시간이 모두 소모 되었습니다. \n충전하시려면 카운터에 문의해주세요.\n30초후 자동종료됩니다.");
            		left_time=0;
            		
            		Thread.sleep(1000*30);
            		psf.dispose();
            		
            	}//end if
            	//시간이 있는지 물어본뒤
            	//있다면 진행
            	
            	Thread.sleep(1000*60); 
            	//시간감소 
                 left_time = left_time - 1;
                 psf.getLblLefttime().setText(String.valueOf(left_time+" 분"));
                 
                 
                 if(pulf.getCbMem().getState()){
						pu_dao.updateTime(id, left_time); // 회원 시간 업데이트
				}else if( pulf.getCbNoMem().getState()) {
						pu_dao.noUpdateTime(id, left_time); //비회원 시간 업데이트
				}//end if else
                 
                 
            } catch (InterruptedException e) {
            	JOptionPane.showMessageDialog(null, "에러발생");
            } catch (SQLException e) {
				e.printStackTrace();
			}
		 }//while
             
	}//run
	
	public void setStatus() { //현재상태뿌리기
		
	}
	
	public String seatChk() { //좌석번호 뿌리기
		return "";
	}
	
	public String time() {
		return "";
	}
	
	public void endRequest() {
		switch(JOptionPane.showConfirmDialog(psf, "사용을 종료하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			
			psf.dispose();
		};
	}
	
	public void sendMsg() throws FileNotFoundException, IOException {
		SendMsg = JOptionPane.showInputDialog("관리자에게 보낼 메시지를 적어주세요.");
		
		/*Properties prop=new Properties();
		String path=System.getProperty("user.dir");//경로나옴
		prop.load(new FileInputStream(path+"src/kr/co/sist/pcbclient/dao/database.properties"));
		String serverIp=prop.getProperty("serverIp");
		String serverPort=prop.getProperty("serverPort");
		*/
		
		try {
			ss=new Socket("211.63.89.149",Integer.parseInt("10000"));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Socket client=null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		//FileOutputStream fos=null;
		
		client=ss;
		dos=new DataOutputStream( client.getOutputStream() );
		dis=new DataInputStream( client.getInputStream() );
		dos.writeInt(MSG);//서버로 파일명 보내기
		dos.writeUTF(SendMsg);//서버로 파일명 보내기
		
		if(dis!=null) {dis.close();}//end if
		if(dos!=null) {dos.close();}//end if
		if(client!=null) {client.close();}//end if
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
