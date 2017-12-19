 package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;
import kr.co.sist.pcbmaster.vo.SetUserVO;

public class PcbStatusEvt implements Runnable,ActionListener {
	private PcbStatusFrm psf;
	private PcbMasterMainEvt pmme;
	
	public PcbStatusEvt(PcbStatusFrm psf,PcbMasterMainFrm pmmf, PcbMasterMainEvt pmme,String seatNum,PcbMasterServer pms) {
		super();
		this.psf = psf;
		this.pmme = pmme;
		setUser();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		
		if(ae.getSource()==psf.getAddTime()) {//시간추가 버튼을 클릭했을 때
			addTime("test");
			pmme.setSeats();//시간추가 새로고침
		}//end if
		
		if(ae.getSource()==psf.getSendMsg()) { //메시지 보내기 버튼을 클릭했을 때
			JOptionPane.showInputDialog("보낼 메시지를 입력하세요.");
		}//end if
		
		if(ae.getSource()==psf.getUseEnd()) { //사용종료 버튼을 클릭했을 때
			switch (JOptionPane.showConfirmDialog(psf, "종료하시겠습니까?")) {
				case JOptionPane.OK_OPTION:
					psf.dispose();
			}//end switch
		}//end if
		
	}//actionPerformed

	@Override
	public void run() {
		
	}//run

	public void addTime(String id) {
		/*1219 좌석에 유일할때만으로 검색하기
		 * 	좌석의 사용유무가 Y인 좌석의 번호를 이용하여 해당하는 아이디와 어쩌구저꾸
		 * */
		
		JTextArea result = new JTextArea();
		JScrollPane jsp = new JScrollPane(result);
		result.append("아래의 내용이 맞습니까??\n");
		result.append("--------------------------------------------------------------------------------------------------------------------\n");
		result.append("                                                  아이디 : "+"\n");
		result.append("                                                  시간 : "+"\n");
		result.append("                                                  총 금액 : "+"\n");
		result.append("                                                  요청시간 : "+"\n");
		JOptionPane.showConfirmDialog(psf, jsp); //showMessageDialog로 출력
	}//addTime
	
	public void setUser() {
		PcbDAO p_dao=PcbDAO.getInstance();
		try {
			SetUserVO suv=p_dao.setUser();
			String text = psf.getLblId().getText();
			psf.getLblId().setText(text+suv.getId());
			psf.getLblName().setText(text+suv.getName());
			psf.getLblTime().setText(text+suv.getLeftTime());
		} catch (SQLException e) {
			System.out.println("사용자정보 로딩 에러");
			e.printStackTrace();
		}
	}//setUser 
	
	
}//class
