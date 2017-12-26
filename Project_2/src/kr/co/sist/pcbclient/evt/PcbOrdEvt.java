package kr.co.sist.pcbclient.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.pcbclient.dao.PcbUserDAO;
import kr.co.sist.pcbclient.form.PcbOrdFrm;
import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbSetMenuVO;

public class PcbOrdEvt extends MouseAdapter implements ActionListener,ChangeListener {
	private PcbOrdFrm pof;
	private DefaultTableModel mOrder, tempMenu;
	private int totalPay = 0;
	private int OrderIdx = 0;
	public static final int DOUBLE_CLICK = 2;
	public PcbOrdEvt(PcbOrdFrm pof) {
		this.pof = pof;
		setListRamen();
	}
	
	public void setListRamen() {
		tempMenu = pof.getDtmRamen();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("���");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			DecimalFormat df = new DecimalFormat("#,###");
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				rowData[1] = psmv.getPrdImg();
				rowData[2] = psmv.getPrdName();
				rowData[3] = psmv.getPrice();
				
				tempMenu.addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(pof, "�޴� ��ȸ�� ������ �� �����ϴ�.");
			e.printStackTrace();
		}
	}
	
	public void setListSnack() {
		tempMenu = pof.getDtmSnack();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("����");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			DecimalFormat df = new DecimalFormat("#,###");
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				rowData[1] = psmv.getPrdImg();
				rowData[2] = psmv.getPrdName();
				rowData[3] = psmv.getPrice();
				
				tempMenu.addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(pof, "�޴� ��ȸ�� ������ �� �����ϴ�.");
			e.printStackTrace();
		}
	}

	public void setListDrink() {
		DefaultTableModel tempMenu = pof.getDtmDrink();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			List<PcbSetMenuVO> userAllMenu3 = pu_dao.userAllMenu("����");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			DecimalFormat df = new DecimalFormat("#,###");
			
			for(int i=0; i< userAllMenu3.size(); i++) {
				psmv = userAllMenu3.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				rowData[1] = psmv.getPrdImg();
				rowData[2] = psmv.getPrdName();
				rowData[3] = psmv.getPrice();
				
				tempMenu.addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(pof, "�޴� ��ȸ�� ������ �� �����ϴ�.");
			e.printStackTrace();
		}
	}
	
	public void order() {
		StringBuilder sbText = new StringBuilder();
		JTable textTable = pof.getJtMenuList();
		DefaultTableModel dtm = pof.getDtmOrder();
		
		sbText.append("\t\t�ֹ� ���� Ȯ��").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t�޴�\t����\t����").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n");
		
		for(int i=0; i<pof.getJtMenuList().getRowCount(); i++) {
			for(int j=1; j<pof.getJtMenuList().getColumnCount(); j++) {
				sbText.append("\t").append(dtm.getValueAt(i, j));
			}
			sbText.append("\n");
		}
		
		sbText.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t�հ� : ").append("\t\t").append(pof.getLblPay().getText())
			.append("\n\n\n")
			.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t�ش� �ֹ��������� �ֹ��Ͻðڽ��ϱ�?");
		
		JTextArea jtaOrder = new JTextArea(15,40);
		JScrollPane jspOrder = new JScrollPane(jtaOrder);
		jtaOrder.setText(sbText.toString());
		
		JTable tempTbl = pof.getJtMenuList();
		int rowCnt = pof.getDtmOrder().getRowCount();
		int ordCnt = 0;
			
		if(rowCnt == 1) {
			ordCnt = 0;
		}
		
		switch(JOptionPane.showConfirmDialog(pof, jspOrder)) {
		case JOptionPane.OK_OPTION:
			for(int i=0; i<(rowCnt+ordCnt); i++) {
				pof.getDtmOrder().removeRow(0);
			}
			totalPay = 0;
			pof.getLblPay().setText("0");
			JOptionPane.showMessageDialog(pof, "�ֹ��� �Ϸ�Ǿ����ϴ�.");
			break;
		};
	}//order
	
	public void addBasket() {
		
	}
	
	public void delBasket() {
		JTable tempTbl = pof.getJtMenuList();
		int removePay = (int) tempTbl.getValueAt(tempTbl.getSelectedRow(), 3);
		int removeIdx = 1;
		switch(JOptionPane.showConfirmDialog(pof, "��ٱ��Ͽ��� �ش� ǰ���� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			totalPay -= removePay;
			OrderIdx -= removeIdx;
			pof.getLblPay().setText(String.valueOf(totalPay));
			pof.getDtmOrder().removeRow(tempTbl.getSelectedRow());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==pof.getBtnOrder()) {
			order();
		}
		
		if(ae.getSource()==pof.getBtnCancle()) {
			pof.dispose();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource()==pof.getJtMenuList_r() || 
				me.getSource()==pof.getJtMenuList_s() || 
						me.getSource()==pof.getJtMenuList_d()) {
			JTabbedPane tempTab = pof.getJtMenubar();
			Object[] rowData = null;
			JTable tempTbl = null;
			rowData = new Object[4];
			switch(tempTab.getSelectedIndex()) {
			case 0:
				switch(me.getClickCount()) {
				case DOUBLE_CLICK:
					tempTbl = pof.getJtMenuList_r();
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
						boolean numFlag = false;
					
						
						OrderIdx += 1;
						
						rowData[0] = OrderIdx;
						rowData[1] = menu;
						rowData[2] = quantity;
						rowData[3] = quantity*price;
						
						totalPay += (quantity*price);
						pof.getLblPay().setText(String.valueOf(totalPay));
						
						pof.getDtmOrder().addRow(rowData);
						break;
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(pof, "���ڸ� �Է°����մϴ�.");
					}
				}
				break;
			case 1:
				switch(me.getClickCount()) {
				case DOUBLE_CLICK:
					tempTbl = pof.getJtMenuList_s();
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
	
						OrderIdx += 1;
						
						rowData[0] = OrderIdx;
						rowData[1] = menu;
						rowData[2] = quantity;
						rowData[3] = quantity*price;
						
						totalPay += (quantity*price);
						pof.getLblPay().setText(String.valueOf(totalPay));
						
						pof.getDtmOrder().addRow(rowData);
						break;
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(pof, "���ڸ� �Է°����մϴ�.");
					}
				}
				break;
			case 2:
				switch(me.getClickCount()) {
				case DOUBLE_CLICK:
					tempTbl = pof.getJtMenuList_d();
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
					
						OrderIdx += 1;
						
						rowData[0] = OrderIdx;
						rowData[1] = menu;
						rowData[2] = quantity;
						rowData[3] = quantity*price;
						
						totalPay += (quantity*price);
						pof.getLblPay().setText(String.valueOf(totalPay));
						
						pof.getDtmOrder().addRow(rowData);
						break;
					}catch(NumberFormatException nfe) {
						JOptionPane.showMessageDialog(pof, "���ڸ� �Է°����մϴ�.");
					}
				}
				break;
			}
		}//if getSource
		
		if(me.getSource() == pof.getJtMenuList()) {
			switch(me.getClickCount()) {
			case DOUBLE_CLICK:
				delBasket();
			}
		}//if getSource
	}//mouseClicked

	@Override
	public void stateChanged(ChangeEvent e) {
		int Num = pof.getJtMenubar().getSelectedIndex();
		String TabName = SelectTabName(Num);
		
		switch(TabName) {
		case "ramen": 
			setListRamen();
			break;
		case "snack":
			setListSnack();
			break;
		case "drink":
			setListDrink();
			break;
		}
	}//stateChanged
	
	private String SelectTabName(int TabIdx) {
		String TabName = "";
		switch(TabIdx){
		case 0: TabName = "ramen"; break;
		case 1: TabName = "snack"; break;
		case 2: TabName = "drink"; break;
		}
		return TabName;
	}//SelectTabName
		
}//class
