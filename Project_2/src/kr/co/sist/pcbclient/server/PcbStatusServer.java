package kr.co.sist.pcbclient.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.form.PcbStatusFrm;

public class PcbStatusServer implements Runnable {
	private PcbStatusFrm psf;

	public PcbStatusServer(PcbStatusFrm psf) {
		this.psf = psf;
	}
	
	public void takeMsg(ServerSocket masterServer) throws IOException {
		
	}//takeMsg
	
	public void takeEndTime() {
		
	}
	
	public void takeFile() {
		
	}
	
	@Override
	public void run() {
		
		
	}

}
