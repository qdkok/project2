package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;

import kr.co.sist.pcbclient.form.PcbOrdFrm;

public class PcbOrdEvt extends MouseAdapter implements ActionListener {
	private PcbOrdFrm pof;
	private DefaultTableModel dtmOrder;
	
	public PcbOrdEvt(PcbOrdFrm pof) {
		this.pof = pof;
	}
	
	public void setList() {
		
	}
	
	public void order() {
		
	}
	
	public void addBasket() {
		
	}
	
	public void delBasket() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
}
