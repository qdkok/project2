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
	public ServerHelper(PcbMasterServer cs,int port) {
		this.port=port;
		this.cs=cs;
	}//ServerHelper
	public void run() {
		try {
			ss=new ServerSocket(port);
			System.out.println(port+"�� ��������");
			while(true) {
				client=ss.accept(); //�ٽ� �������� while������������
				DataInputStream dis = null;
				dis=new DataInputStream( client.getInputStream() );
				
				switch (dis.readInt()) {
				case MSG:
					cs.takeMsg(dis.readUTF());
					break;

				default:
					break;
				}
				
				
				if(dis!=null) {dis.close();}
			}//end while
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
