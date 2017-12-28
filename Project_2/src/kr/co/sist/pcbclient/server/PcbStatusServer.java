package kr.co.sist.pcbclient.server;

import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.evt.PcbStatusEvt;
import kr.co.sist.pcbclient.form.PcbStatusFrm;

public class PcbStatusServer implements Runnable {
	private PcbStatusFrm psf;
	private PcbStatusEvt pse;
	private ServerSocket ss;
	private Socket client;
	private Thread serverThred;
	
	private final static int MSG=0;
	private final static int END=1;
	private final static int ADDTIME=2;
	
	public PcbStatusServer(PcbStatusFrm psf,PcbStatusEvt pse) {
		this.pse = pse;
		this.psf = psf;
		
		//서버가동시작
		serverThred = new Thread(this);
		serverThred.start();
	}
	
	public void takeMsg(String msg){
		JOptionPane.showMessageDialog(psf, msg);
	}//takeMsg
	
	
	public void takeEndTime() {
		psf.dispose();
	}
	
	public void takeTime() {
		pse.setStatus();
	}
	
	
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("C:/dev/git/project2/Project_2/src/kr/co/sist/pcbclient/dao/database.properties"));
			int myServerPort=Integer.parseInt(prop.getProperty("myServerPort"));
			ss=new ServerSocket(myServerPort);
			System.out.println("유저서버"+myServerPort+"번 서버열림");
		
			while(true) {
				client=ss.accept(); //다시 받으려면 while문을돌려야함
				DataInputStream dis = null;
				dis=new DataInputStream( client.getInputStream() );
				
				System.out.println(psf);
				switch (dis.readInt()) {
				case MSG:
					 takeMsg(dis.readUTF());
					break;
				case END:
					takeEndTime();
					break;
				case ADDTIME:
					takeTime();
					break;
				}//switch
				
				if(dis!=null) {dis.close();}
			}
			
		}catch (IOException e) {
				e.printStackTrace();
		}//catch
		
	}//run

	public ServerSocket getSs() {
		return ss;
	}

}
