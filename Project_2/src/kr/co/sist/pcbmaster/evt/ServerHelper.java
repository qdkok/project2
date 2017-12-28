package kr.co.sist.pcbmaster.evt;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;


public class ServerHelper extends Thread {
	private ServerSocket ss;
	private Socket client;
	private PcbMasterServer cs;
	private int port;
	
	private final static int MSG=0;
	private final static int START=1;
	private final static int END=2;
	private final static int ORDER=3;
	private final static int NONEFILE=4;
	public ServerHelper(PcbMasterServer cs,int port) {
		this.port=port;
		this.cs=cs;
	}//ServerHelper
	public void run() {
		try {
			ss=new ServerSocket(port);
			System.out.println(port+"번 서버열림");
			while(true) {
				client=ss.accept(); //다시 받으려면 while문을돌려야함
				DataInputStream dis = null;
				dis=new DataInputStream( client.getInputStream() );
				
				switch (dis.readInt()) {
				case MSG:
					cs.takeMsg(dis.readUTF());
					break;
				case START:
					cs.userLogin();
					break;
				case END:
					cs.takeUseEnd();
					break;
				case ORDER:
					cs.takeOrder();
					break;
				case NONEFILE:
					cs.sendFile(client);
					break;
				}//end switch
				
				
				if(dis!=null) {dis.close();}
			}//end while
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
