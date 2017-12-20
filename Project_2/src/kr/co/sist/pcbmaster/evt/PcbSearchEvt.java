package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbSearchFrm;
import kr.co.sist.pcbmaster.vo.AddTimeVO;
import kr.co.sist.pcbmaster.vo.SearchIdVO;

public class PcbSearchEvt implements ActionListener {
	private PcbSearchFrm psf;
	private String id;

	public PcbSearchEvt(PcbSearchFrm psf) {
		super();
		this.psf = psf;
	}//PcbSearchEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		if(psf.getBtnSearch() == e.getSource()) {
			search();
		}//end if
		if(psf.getBtnAddTime() == e.getSource()) {
			addTime();
		}//end if
	}//actionPerformed
	
	public void search() {
		PcbDAO p_dao = PcbDAO.getInstance();
		id = psf.getSearchId().getText();
		if(id.trim().equals("")) { //id란이 공백일경우
			JOptionPane.showMessageDialog(psf, "아이디를 입력해주세요");
		}//end if
			try {
				SearchIdVO sid=p_dao.searchId(id);
				String name = sid.getName();
				int leftTime = sid.getLeftTime();
				
				psf.getJlName().setText("◎이름 : "+name);
				psf.getJlTime().setText("◎남은 시간 : "+leftTime+"분");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(psf, "존재하지 않는 아이디 입니다 확인해주세요.");
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
			} //catch	
	}//search
	
	public void addTime() {

		if(id.trim().equals("")) {		//아이디 찾기를 하지 않을 경우
			JOptionPane.showMessageDialog(psf, "아이디를 먼저 검색해 주세요");
			return;
		}//end if
		
		JComboBox<String> jcb = psf.getJcbAdTime();
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
				p_dao.addTime(new AddTimeVO(id, selTime));
				JOptionPane.showMessageDialog(psf, "시간추가 완료되었습니다.");
				psf.getJlName().setText("◎이름 : ");
				psf.getJlTime().setText("◎남은 시간 : ");
				id="";//아이디 초기화
			} catch (SQLException e) {
				System.out.println("시간추가 도중 문제발생");
				e.printStackTrace();
			}//catch
		}//switch			
			
			
			
		
	}//addTime
	
}//class
