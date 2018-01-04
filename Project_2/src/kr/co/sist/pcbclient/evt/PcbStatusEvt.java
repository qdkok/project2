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
		this.pulf = pulf;//회원 비회원 판단을 위해서
		
		try {
			prop = new Properties();
			prop.load(new FileReader(System.getProperty("user.dir")+"/Project_2/src/kr/co/sist/pcbclient/dao/database.properties"));
			socketPort=Integer.parseInt(prop.getProperty("socketPort"));
			seatNumber=Integer.parseInt(prop.getProperty("seatsNum"));
			serverIp=prop.getProperty("serverIp");
			
			//서버에 시작을 알리기위해
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(START);//서버로 파일명 보내기
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(psf, "서버관련 파일이 존재하지 않습니다.");
			e1.printStackTrace();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(psf, "서버연결중 문제발생.");
			e1.printStackTrace();
		}//catch
		
		 id = pulf.getTfUserId().getText();
		 //현재상태뿌리기
		 setStatus();
		 
		 //유저서버오픈
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
			pu_dao.logout(psf.getLblStarttime().getText());//로그아웃 db변경
			
			//서버에 종료를 알리기위해
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(END);//서버로 파일명 보내기
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(psf, "사용종료 중 문제발생");
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
            		JOptionPane.showMessageDialog(psf, "사용시간이 모두 소모 되었습니다. \n충전하시려면 카운터에 문의해주세요.\n30초후 자동종료됩니다.");
            		left_time=0;
            		
            		Thread.sleep(1000*30);
            		psf.dispose();
            		
            	}//end if
            	//시간이 있는지 물어본뒤
            	//있다면 진행
            	
            	Thread.sleep(1000*60); 

            	//시간감소 
                 if(pulf.getCbMem().getState()){
						pu_dao.updateTime(id); // 회원 시간 업데이트
				}else if( pulf.getCbNoMem().getState()) {
						pu_dao.noUpdateTime(id); //비회원 시간 업데이트
				}//end if else
                 psf.getLblSeat().setText("좌석");
                 setStatus();
            } catch (InterruptedException e) {
            	JOptionPane.showMessageDialog(null, "에러발생");
            } catch (SQLException e) {
				e.printStackTrace();
			}
		 }//while
             
	}//run
	
	public void setStatus() { //현재상태뿌리기
		
		PcbUserDAO pu_dao=PcbUserDAO.getInstance();
		
		try {
			psf.getLblId().setText(id);//아이디 설정
			psf.getLblStarttime().setText(pu_dao.setUser());//시작시간 설정
			psf.getLblSeat().setText(psf.getLblSeat().getText()+(seatNumber+1));//좌석번호 설정

			if(pulf.getCbMem().getState()){//회원 비회원 판단 후 남은시간 설정
					left_time = pu_dao.userGetTime(id);
			}else{
					left_time = pu_dao.noUserGetTime(id);
					psf.getLblId().setText(id+"(비회원)");
			}//else
			psf.getLblLefttime().setText(String.valueOf(left_time+" 분"));	
			
		} catch (SQLException e) {
			//회원
			e.printStackTrace();
		}//try
	}//setStatus
	
	public void endRequest() {
		switch(JOptionPane.showConfirmDialog(psf, "사용을 종료하시겠습니까?")) {
		case JOptionPane.OK_OPTION:
			psf.dispose();
		};
	}
	public void sendMsg() throws FileNotFoundException, IOException {
		SendMsg = JOptionPane.showInputDialog("관리자에게 보낼 메시지를 적어주세요.");
		
		try {
			Socket client=new Socket(serverIp,socketPort);
			DataOutputStream dos=null;
			
			StringBuffer sbMsg=new StringBuffer();
			sbMsg.append("좌석").append(seatNumber+1).append(" : ").append(SendMsg);
			
			dos=new DataOutputStream( client.getOutputStream() );
			dos.writeInt(MSG);//서버로 파일명 보내기
			dos.writeUTF(sbMsg.toString());//서버로 파일명 보내기
			
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
