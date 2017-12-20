package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.frm.PcbSearchFrm;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;
import kr.co.sist.pcbmaster.vo.SetPrdListVO;
import kr.co.sist.pcbmaster.vo.SetSeatsVO;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;

public class PcbMasterMainEvt extends MouseAdapter implements Runnable, ActionListener {
	private PcbMasterMainFrm pmmf;
	private PcbMasterServer pms;
	private Thread setting;
	
	
	public static final int DOUBLE_CLICK = 2;
	public static final int SEATS_TAB = 0;
	public static final int PRODUCT_TAB = 1;
	public static final int ORDER_TAB = 2;

	public PcbMasterMainEvt(PcbMasterMainFrm pmmf) {
		super();
		this.pmmf = pmmf;
		
		setSeats();
		setPrdList();
		setOrdList();
		
		setting = new Thread(this);
		setting.start();
	}// PcbMasterMainEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == pmmf.getBtnAddTime()) {
			
			new PcbSearchFrm(this);
		}

		// --------------------------------강사님이 도와주심...ㅎ--------------------------
		// --------------------------------좌석버튼을 눌렀을 경우 이벤트 발생--------------------------
			JButton[] temp = pmmf.getBtnSeats();
			for (int i = 0; i < temp.length; i++) {
				if (ae.getSource() == temp[i]) {
					if(temp[i].getText().contains("사용자")) {
						new PcbStatusFrm(this, pmmf, String.valueOf(i), pms);
					}else {
						JOptionPane.showMessageDialog(pmmf, "사용중인 좌석이 아닙니다.");
					}
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
		if(ae.getSource()==pmmf.getBtnOrdCancle()) {
			JTable jtTemp = pmmf.gettOrdList();
			String ordNum = (String) jtTemp.getValueAt(jtTemp.getSelectedRow(), 0);
			ordCancle(ordNum);
		}//end if
		

	}// actionPerformed

	@Override
	public void run() {
		
		try {
			while(true) {
				Thread.sleep(1000 *60);
				setSeats();
				setPrdList();
				setOrdList();
			}//while
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}// run

	@Override
	public void mouseClicked(MouseEvent me) {
		JTabbedPane jtp = pmmf.getJtp();
		
		switch (jtp.getSelectedIndex()) {
			case SEATS_TAB: break;
			case PRODUCT_TAB: break;
			case ORDER_TAB: {
				switch (me.getClickCount()) {
					case DOUBLE_CLICK:{
						JTable jtTemp = pmmf.gettOrdList();
						String ordNum = (String) jtTemp.getValueAt(jtTemp.getSelectedRow(), 0);
						String ordStatus = (String) jtTemp.getValueAt(jtTemp.getSelectedRow(), 5);
						if(ordStatus.equals("Y")) {
							JOptionPane.showMessageDialog(pmmf, "이미처리된 주문입니다.");
							return;
						}//end if
						ordClear(ordNum);
					}
				}//switch
				break;
			}//order_tab
		}//switch
	}// mouseClicked

	public void setSeats() {
		PcbDAO p_dao= PcbDAO.getInstance();
		JButton [] seat = pmmf.getBtnSeats();
		List<SetSeatsVO> lss=null;
		int flag=-1;
		try {
			lss = p_dao.seats();
			for(int i=0;i<seat.length;i++) {
				for(int j=0;j<lss.size();j++) {
					flag=Integer.parseInt(lss.get(j).getSeatNum().substring(5));//좌석번호의 숫자와 자리비교후 같으면 ㄱㄱ
					if(flag==i) {
						seat[i].setText("<html>좌석"+(i+1)+"<br>사용자 : "+lss.get(j).getId()+"<br>남은시간 : "+lss.get(j).getLeftTime()+"분");
					}//end if
				}//end for
				
			}//end for
		} catch (SQLException e) {
			System.out.println("좌석 호출 실패");
			e.printStackTrace();
		}//catch
		
		
	}// setSeats

	public void setOrdList() {
		PcbDAO p_dao= PcbDAO.getInstance();
		try {
			List<SetOrdListVO> solList = p_dao.ordList();
			DefaultTableModel dtm = pmmf.getDtmOrdList();
			dtm.setNumRows(0);
			SetOrdListVO sol=null;
			Object[] rowData = null;
			for(int i=0;i<solList.size();i++) {
				sol=solList.get(i);
				
				rowData = new Object[7];
				rowData[0]=sol.getOrdNum();
				rowData[1]=sol.getSeatNum();
				rowData[2]=sol.getPrdName();
				rowData[3]=sol.getQuantity();
				rowData[4]=sol.getPrice();
				rowData[5]=sol.getStatus();
				rowData[6]=sol.getOrdTime();
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// setOrdList

	public void ordCancle(String ordNum) {
		PcbDAO p_dao = PcbDAO.getInstance();
		
		if(ordNum==null) {
			JOptionPane.showMessageDialog(pmmf, "삭제할 항목을 선택하세요!");
			return;
		}//end if
		switch (JOptionPane.showConfirmDialog(pmmf, "선택한 주문을 삭제 하시겠습니까?")) {
			case JOptionPane.OK_OPTION:{
				try {
					p_dao.ordDelete(ordNum);
					JOptionPane.showMessageDialog(pmmf, "삭제 처리가 완료되었습니다.");
					setOrdList();
				} catch (SQLException e) {
					System.out.println("주문삭제 오류");
					e.printStackTrace();
				}//catch
			}
		}//switch
	}// ordCanCle
	
	public void ordClear(String ordNum) {
		PcbDAO p_dao= PcbDAO.getInstance();
		
		switch (JOptionPane.showConfirmDialog(pmmf, "처리 완료 하시겠습니까?")) {
		case JOptionPane.OK_OPTION:{
			try {
				p_dao.ordClear(ordNum);
				JOptionPane.showMessageDialog(pmmf, "주문 처리가 완료되었습니다.");
				setOrdList();
			} catch (SQLException e) {
				System.out.println("업데이트 오류");
				e.printStackTrace();
			}//catch
		}
	}//switch
	}// ordClear

	public void setPrdList() {
		PcbDAO p_dao= PcbDAO.getInstance();
		try {
			List<SetPrdListVO> spl = p_dao.prdList();
			DefaultTableModel dtm = pmmf.getDtmPrdList();
			dtm.setNumRows(0);
			SetPrdListVO sol=null;
			Object[] rowData = null;
			for(int i=0;i<spl.size();i++) {
				sol=spl.get(i);
				
				rowData = new Object[5];
				rowData[0]=sol.getPrdNum();
				rowData[1]=sol.getPrdCate();
				rowData[2]=sol.getPrdName();
				rowData[3]=sol.getPrice();
				rowData[4]=sol.getPrdInputTime();
				
				dtm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
