package kr.co.sist.pcbmaster.evt;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;


public class ServerHelper extends Thread {
	private ServerSocket ss;
	private Socket client;
	
	private int port;
	
	public ServerHelper(PcbMasterServer cs,int port) {
		this.port=port;
	}//ServerHelper
	public void run() {
		try {
			ss=new ServerSocket(port);
			System.out.println(port+"번 서버열림");
			while(true) {
				client=ss.accept(); //다시 받으려면 while문을돌려야함
				DataInputStream dis = null;
				dis=new DataInputStream( client.getInputStream() );
				System.out.println(dis.readUTF());
			}//end while
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
