 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
	
	private Properties prop;
	private int socketPort;
	private String userIp;
	
	private final static int MSG=0;
	private final static int END=1;
	private final static int ADDTIME=2;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		this.psf = psf;
		this.pmme = pmme;
		flagnum= Integer.parseInt(seatNum);//비회원 파악 플래그
		this.seatNum="seat_"+ (seatNum.length()==1?"0"+seatNum:seatNum);
		setUser(this.seatNum);
		
		try {
			prop = new Properties();
			prop.load(new FileReader(System.getProperty("user.dir")+"/Project_2/src/kr/co/sist/pcbmaster/dao/database.properties"));
			socketPort=Integer.parseInt(prop.getProperty("socketPort"))+Integer.parseInt(seatNum);
			switch(Integer.parseInt(seatNum)) {
			case 15: userIp=prop.getProperty("userIp15"); break;
			case 16: userIp=prop.getProperty("userIp16"); break; 
			case 17: userIp=prop.getProperty("userIp17"); break;
			case 19: userIp=prop.getProperty("userIp19"); break;
			}
			
			//서버에 시작을 알리기위해

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(psf, "서버연결중 문제발생.");
			e1.printStackTrace();
		}//catch
		
		setting = new Thread(this);
		setting.start();
	}//PcbStatusEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==psf.getAddTime()) {//시간추가 버튼을 클릭했을 때
			addTime(suv.getId());
			pmme.setSeats();//시간추가 새로고침
			
			//새로고침해달라고 요청
			try {
			Socket client=new Socket(userIp,socketPort);
			DataOutputStream dos=null;
			
			dos=new DataOutputStream( client.getOutputStream() );
				dos.writeInt(ADDTIME);
			
			if(dos!=null) {dos.close();}//end if
			if(client!=null) {client.close();}//end if
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end if
		
		if(ae.getSource()==psf.getSendMsg()) { //메시지 보내기 버튼을 클릭했을 때
			String msg=JOptionPane.showInputDialog("보낼 메시지를 입력하세요.");
			//메시지 전달
			if(msg==null||msg.equals("")) {
				return;
			}//end if
			try {
				System.out.println(userIp);
				System.out.println(socketPort);
				Socket client=new Socket(userIp,socketPort);
				DataOutputStream dos=null;
				
				dos=new DataOutputStream( client.getOutputStream() );
				dos.writeInt(MSG);
				dos.writeUTF(msg);
				
				if(dos!=null) {dos.close();}//end if
				if(client!=null) {client.close();}//end if
				
				} catch (IOException e) {
					e.printStackTrace();
				}
		}//end if
		
		if(ae.getSource()==psf.getUseEnd()) { //사용종료 버튼을 클릭했을 때
			switch (JOptionPane.showConfirmDialog(psf, "종료하시겠습니까?")) {
				case JOptionPane.OK_OPTION:
					stopUser();
					pmme.setSeats();
					
					//종료요청
					try {
						Socket client=new Socket(userIp,socketPort);
						DataOutputStream dos=null;
						
						dos=new DataOutputStream( client.getOutputStream() );
						dos.writeInt(END);
						
						if(dos!=null) {dos.close();}//end if
						if(client!=null) {client.close();}//end if
						
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					psf.dispose();
					
			}//end switch
		}//end if
		
	}//actionPerformed
	
	public void stopUser() {
		
		PcbDAO p_dao = PcbDAO.getInstance();
		try {
			p_dao.seatUpdate(seatNum);
			JOptionPane.showMessageDialog(psf, seatNum+"좌석의 사용이 종료되었습니다.");
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
		/*1219 좌석에 유일할때만으로 검색하기
		 * 	좌석의 사용유무가 Y인 좌석의 번호를 이용하여 해당하는 아이디와 어쩌구저꾸
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
		result.append("아래의 내용이 맞습니까??\n");
		result.append("--------------------------------------------------------------------------------------------------------------------\n");
		result.append("                                                  아이디 : "+ id +"\n");
		result.append("                                                  시간 : "+selTime+"분("+(selTime/60)+" 시간)\n");
		result.append("                                                  총 금액 : "+((selTime/60)*1000)+" 원\n");
		result.append("                                                  요청시간 : "+sdf.format(time)+"\n");

		switch (JOptionPane.showConfirmDialog(psf, jsp)) {
		case JOptionPane.OK_OPTION:		
			PcbDAO p_dao=PcbDAO.getInstance();
			try {
				p_dao.addTime(new AddTimeVO(id, selTime),pmme.getMemFlag()[flagnum]);
				JOptionPane.showMessageDialog(psf, "시간추가 완료되었습니다.");
				setUser(seatNum);//시간추가 새로고침
			} catch (SQLException e) {
				System.out.println("시간추가 도중 문제발생");
				e.printStackTrace();
			}//catch
		}//switch
		
		
	}//addTime
	   
	public void setUser(String seatNum) { 
		PcbDAO p_dao=PcbDAO.getInstance();
		try {
			
			suv=p_dao.setUser(seatNum,pmme.getMemFlag()[flagnum]);
			psf.getLblId().setText("ID : "+ suv.getId());
			psf.getLblName().setText("사용자명 : "+suv.getName());
			psf.getLblTime().setText("남은시간 : "+suv.getLeftTime()+"분");
		} catch (SQLException e) {
			System.out.println("사용자정보 로딩 에러");
			e.printStackTrace();
		}
	}//setUser 
	
	
}//class
