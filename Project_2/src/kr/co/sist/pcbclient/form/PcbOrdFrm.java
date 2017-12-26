package kr.co.sist.pcbclient.form;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.pcbclient.evt.PcbOrdEvt;

@SuppressWarnings("serial")
public class PcbOrdFrm extends JDialog{
	private PcbStatusFrm psf;
	private DefaultTableModel dtmSnack, dtmRamen, dtmDrink, dtmOrder;
	private JButton btnOrder, btnCancle;
	private JLabel lblPrice, lblPay;
	private JTabbedPane jtMenubar;
	private JTable jtMenuList, jtMenuList_r , jtMenuList_s, jtMenuList_d;
	
	public PcbOrdFrm(PcbStatusFrm psf) {
		this.psf = psf;
		
		btnOrder = new JButton("주문하기");
		btnCancle = new JButton("닫기");
		lblPrice = new JLabel("총 금액 : ");
		lblPay = new JLabel("0");
		
		setLayout(null);
		
		////////////////////////////Ramen MenuItem form////////////////////////////
		
		String[] columnName_r = {"상품코드" , "상품이미지" , "상품명", "상품가격"};
		String[][] rowData_r = new String[10][4];
				
		dtmRamen = new DefaultTableModel(rowData_r, columnName_r);
				
		jtMenuList_r= new JTable(dtmRamen) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getcolumnClass
		};

		JScrollPane jspMenuList_r = new JScrollPane(jtMenuList_r);
		
		//890
		jtMenuList_r.getColumnModel().getColumn(0).setPreferredWidth(100); //상품코드
		jtMenuList_r.getColumnModel().getColumn(1).setPreferredWidth(200); //상품이미지
		jtMenuList_r.getColumnModel().getColumn(2).setPreferredWidth(390); //상품명
		jtMenuList_r.getColumnModel().getColumn(3).setPreferredWidth(200); //상품가격

		jtMenuList_r.setRowHeight(120);
		////////////////////////////Ramen MenuItem form////////////////////////////
		
		////////////////////////////cookie MenuItem form////////////////////////////
		
		String[] columnName_s = {"상품코드" , "상품이미지" , "상품명", "상품가격"};
		String[][] rowData_s = new String[10][4];
		
		dtmSnack = new DefaultTableModel(rowData_s, columnName_s);
		
		jtMenuList_s= new JTable(dtmSnack) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getcolumnClass
		};
		
		JScrollPane jspMenuList_s = new JScrollPane(jtMenuList_s);
		
		
		//890
		jtMenuList_s.getColumnModel().getColumn(0).setPreferredWidth(100); //상품코드
		jtMenuList_s.getColumnModel().getColumn(1).setPreferredWidth(200); //상품이미지
		jtMenuList_s.getColumnModel().getColumn(2).setPreferredWidth(390); //상품명
		jtMenuList_s.getColumnModel().getColumn(3).setPreferredWidth(200); //상품가격
		
		jtMenuList_s.setRowHeight(120);
		////////////////////////////snack MenuItem form////////////////////////////
		
		////////////////////////////drink MenuItem form////////////////////////////
		
		String[] columnName_d = {"상품코드" , "상품이미지" , "상품명", "상품가격"};
		String[][] rowData_d = new String[10][4];
		
		dtmDrink = new DefaultTableModel(rowData_d, columnName_d);
		
		jtMenuList_d= new JTable(dtmDrink) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getcolumnClass
		};
		
		JScrollPane jspMenuList_d = new JScrollPane(jtMenuList_d);
		
		//890
		jtMenuList_d.getColumnModel().getColumn(0).setPreferredWidth(100); //상품코드
		jtMenuList_d.getColumnModel().getColumn(1).setPreferredWidth(200); //상품이미지
		jtMenuList_d.getColumnModel().getColumn(2).setPreferredWidth(390); //상품명
		jtMenuList_d.getColumnModel().getColumn(3).setPreferredWidth(200); //상품가격
		
		jtMenuList_d.setRowHeight(120);
		////////////////////////////drink MenuItem form////////////////////////////
		
		jtMenubar = new JTabbedPane();
		
		jtMenubar.add("라면", jspMenuList_r);
		jtMenubar.add("과자", jspMenuList_s);
		jtMenubar.add("음료", jspMenuList_d);
		
		////////////////////////////OrderList Table form////////////////////////////
		String[] columnName = {"상품코드", "메뉴" , "수량" , "가격"};
		String[][] rowData = {};
				
		dtmOrder = new DefaultTableModel(rowData, columnName);
		
		//컬럼의 내용 편집 막기
		jtMenuList= new JTable(dtmOrder) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}//getcolumnClass
		};
		
		jtMenuList.getColumnModel().getColumn(0).setPreferredWidth(70); //상품명
		jtMenuList.getColumnModel().getColumn(1).setPreferredWidth(200); //상품명
		jtMenuList.getColumnModel().getColumn(2).setPreferredWidth(155); //상품수량
		jtMenuList.getColumnModel().getColumn(3).setPreferredWidth(150); //상품가격
		
		jtMenuList.setRowHeight(40);

		JScrollPane jspMenuList = new JScrollPane(jtMenuList);		
		////////////////////////////OrderList Table form////////////////////////////
		
		JLabel OrderInfoMsg = new JLabel("해당 Swing 사용 설명 메시지");
		OrderInfoMsg.setBounds(585, 420, 280, 100);
		
		////////////////////////////Order Button////////////////////////////
		btnOrder.setBounds(585, 520, 280, 100);
		btnCancle.setBounds(585, 640, 280, 55);
		////////////////////////////Order Button////////////////////////////
		lblPrice.setBorder(new TitledBorder(""));
		lblPrice.setBounds(5, 630, 555, 70);
		lblPay.setBounds(360, 625, 300, 80);
		jtMenubar.setBounds(0, 0, 890, 430);
		jspMenuList.setBorder(new TitledBorder("주문목록"));
		jspMenuList.setBounds(5, 435, 555, 190);
		
		
		//column 이동 막기
		jtMenuList.getTableHeader().setReorderingAllowed(false);
		jtMenuList_r.getTableHeader().setReorderingAllowed(false);
		jtMenuList_s.getTableHeader().setReorderingAllowed(false);
		jtMenuList_d.getTableHeader().setReorderingAllowed(false);
		
		add(jtMenubar);
		add(jspMenuList);
		add(btnOrder);
		add(btnCancle);
		add(lblPrice);
		add(lblPay);
		add(OrderInfoMsg);
		
		PcbOrdEvt poe = new PcbOrdEvt(this,psf);
		jtMenuList.addMouseListener(poe);
		jtMenuList_r.addMouseListener(poe);
		jtMenuList_s.addMouseListener(poe);
		jtMenuList_d.addMouseListener(poe);
		btnOrder.addActionListener(poe);
		btnCancle.addActionListener(poe);
		jtMenubar.addChangeListener(poe);

		setVisible(true);
		setResizable(false);
		setBounds(480, 170, 910, 750);
		
	}

	public PcbStatusFrm getPsf() {
		return psf;
	}

	public void setPsf(PcbStatusFrm psf) {
		this.psf = psf;
	}

	public DefaultTableModel getDtmSnack() {
		return dtmSnack;
	}

	public void setDtmSnack(DefaultTableModel dtmSnack) {
		this.dtmSnack = dtmSnack;
	}

	public DefaultTableModel getDtmRamen() {
		return dtmRamen;
	}

	public void setDtmRamen(DefaultTableModel dtmRamen) {
		this.dtmRamen = dtmRamen;
	}

	public DefaultTableModel getDtmDrink() {
		return dtmDrink;
	}

	public void setDtmDrink(DefaultTableModel dtmDrink) {
		this.dtmDrink = dtmDrink;
	}

	public DefaultTableModel getDtmOrder() {
		return dtmOrder;
	}

	public void setDtmOrder(DefaultTableModel dtmOrder) {
		this.dtmOrder = dtmOrder;
	}

	public JButton getBtnOrder() {
		return btnOrder;
	}

	public void setBtnOrder(JButton btnOrder) {
		this.btnOrder = btnOrder;
	}

	public JButton getBtnCancle() {
		return btnCancle;
	}

	public void setBtnCancle(JButton btnCancle) {
		this.btnCancle = btnCancle;
	}

	public JLabel getLblPrice() {
		return lblPrice;
	}

	public void setLblPrice(JLabel lblPrice) {
		this.lblPrice = lblPrice;
	}

	public JLabel getLblPay() {
		return lblPay;
	}

	public void setLblPay(JLabel lblPay) {
		this.lblPay = lblPay;
	}

	public JTabbedPane getJtMenubar() {
		return jtMenubar;
	}

	public void setJtMenubar(JTabbedPane jtMenubar) {
		this.jtMenubar = jtMenubar;
	}

	public JTable getJtMenuList() {
		return jtMenuList;
	}

	public void setJtMenuList(JTable jtMenuList) {
		this.jtMenuList = jtMenuList;
	}

	public JTable getJtMenuList_r() {
		return jtMenuList_r;
	}

	public void setJtMenuList_r(JTable jtMenuList_r) {
		this.jtMenuList_r = jtMenuList_r;
	}

	public JTable getJtMenuList_s() {
		return jtMenuList_s;
	}

	public void setJtMenuList_s(JTable jtMenuList_s) {
		this.jtMenuList_s = jtMenuList_s;
	}

	public JTable getJtMenuList_d() {
		return jtMenuList_d;
	}

	public void setJtMenuList_d(JTable jtMenuList_d) {
		this.jtMenuList_d = jtMenuList_d;
	}

	
}
