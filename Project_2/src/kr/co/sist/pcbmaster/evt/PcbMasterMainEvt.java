package kr.co.sist.pcbmaster.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdk.nashorn.internal.scripts.JO;
import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;
import kr.co.sist.pcbmaster.vo.SetPrdListVO;
import kr.co.sist.pcbmaster.frm.PcbStatusFrm;

public class PcbMasterMainEvt extends MouseAdapter implements Runnable, ActionListener {
	private PcbMasterMainFrm pmmf;
	private PcbMasterServer pms;
	
	public static final int DOUBLE_CLICK = 2;
	public static final int SEATS_TAB = 0;
	public static final int PRODUCT_TAB = 1;
	public static final int ORDER_TAB = 2;

	public PcbMasterMainEvt(PcbMasterMainFrm pmmf) {
		super();
		this.pmmf = pmmf;
		setPrdList();
		setOrdList();
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
		if(ae.getSource()==pmmf.getBtnOrdCancle()) {
			JTable jtTemp = pmmf.gettOrdList();
			String ordNum = (String) jtTemp.getValueAt(jtTemp.getSelectedRow(), 0);
			System.out.println(ordNum);
			ordCancle(ordNum);
		}//end if
		

	}// actionPerformed

	@Override
	public void run() {

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
