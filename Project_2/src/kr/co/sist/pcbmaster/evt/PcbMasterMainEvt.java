package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jdk.nashorn.internal.scripts.JO;
import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;

public class PcbMasterMainEvt extends MouseAdapter implements Runnable, ActionListener {
	private PcbMasterMainFrm pmmf;
	private PcbMasterServer pms;

	public PcbMasterMainEvt(PcbMasterMainFrm pmmf) {
		super();
		this.pmmf = pmmf;
	}// PcbMasterMainEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		boolean flag = true;
		
		if (ae.getSource() == pmmf.getBtnAddTime()) {
			while (flag) {
				// 시간추가할 아이디를 검색하는 메시지 다이얼로그
				String id = JOptionPane.showInputDialog(pmmf, "아이디 입력");
				if ("".equals(id)) {
					JOptionPane.showMessageDialog(pmmf, "아이디를 입력하세요.");
				} else if (id == null) {
					flag = false;
				} else {
					String[] time = { "1:00", "2:00", "3:00", "4:00", "5:00" };
					JOptionPane.showInputDialog(pmmf, "추가할 시간", "시간추가", JOptionPane.QUESTION_MESSAGE, null, time, "1:00");
					flag = false;
				}//end else
			}//end while
		}//end if

		// --------------------------------강사님이 도와주심...ㅎ--------------------------
		// --------------------------------좌석버튼을 눌렀을 경우 이벤트 발생--------------------------
		JButton[] temp = pmmf.getBtnSeats();
		for (int i = 0; i < temp.length; i++) {
			if (ae.getSource() == temp[i]) {
				new PcbStatusFrm(this, pmmf, String.valueOf(i), pms);
			} // end if
		} // end for
		// --------------------------------강사님이 도와주심...ㅎ--------------------------
		
		//상품추가 버튼을 눌렀을 경우 이벤트 발생
		if(ae.getSource()==pmmf.getBtnPrdAdd()) {
			new PcbAddPrdFrm(this, null, false);
		}//end if
		//상품수정 버튼을 눌렀을 경우 이벤트 발생
		if(ae.getSource()==pmmf.getBtnPrdUpdate()) {
			new PcbAddPrdFrm(this, null, true);
		}//end if
		

	}// actionPerformed

	@Override
	public void run() {

	}// run

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}// mouseClicked

	public void setSeats() {

	}// setSeats

	public void setOrdList(List<SetOrdListVO> ordList) {

	}// setOrdList

	public void ordCanCle(String prdNum) {

	}// ordCanCle

	public void setPrdList() {

	}// setPrdList

	public void openServer() {

	}// openServer

	public void delPrdBtn() {

	}// delPrdBtn

	public void delPrd() {

	}// delPrd

	public void addPrdBtn() {

	}// addPrdBtn

	public void editPrdBtn() {

	}// editPrdBtn
}// class
