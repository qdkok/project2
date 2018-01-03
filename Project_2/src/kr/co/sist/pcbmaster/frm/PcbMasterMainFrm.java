package kr.co.sist.pcbmaster.frm;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

@SuppressWarnings("serial")
public class PcbMasterMainFrm extends JFrame {
	
	private JButton[] btnSeats;
	private JButton btnAddTime,btnOrdCancle,btnPrdAdd,btnPrdUpdate,btnPrdDelete;
	private DefaultTableModel dtmOrdList,dtmPrdList;
	private JTable tOrdList,tPrdList;
	 JTabbedPane jtp;
	private DefaultComboBoxModel<?> prdcate;
	
	public PcbMasterMainFrm() {
		super("������ ������");
		 jtp = new JTabbedPane();
		JPanel jpSeats = new JPanel();
		JPanel jpProducts = new JPanel();
		JPanel jpOrder = new JPanel();
		
		jpSeats.setBackground(new Color(0xF5461));
		jpProducts.setBackground(new Color(0xF5461));
		
		btnAddTime = new JButton("����� �ð� �߰�");
		btnAddTime.setBackground(new Color(0x282827));
		btnAddTime.setForeground(Color.WHITE);
		
		//----------------------------------��ǰ����-----------------------------
		 String[] productColumn= {"��ǰ��ȣ","�з�","��ǰ��","�ܰ�","���������ð�"};
	      //2.�÷� �� ���� �迭
	      String[][] productList=new String[0][6];
	      //3.DefaultTableModel �� �����ϰ� �� �Ҵ�
	      dtmPrdList=new DefaultTableModel(productList,productColumn);
	      //4.DefaultTableModel�� ����Ͽ� JTable�� ����
	      tPrdList=new JTable(dtmPrdList) {
	    	  @Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
	      };
	      //--------------------------------��ǰ����--------------------------
	      
	      //--------------------------------�ֹ�Ȯ��--------------------------
	      String[] orderColumn = {"�ֹ���ȣ","�¼���ȣ","��ǰ��","����","�ݾ�","����","�ֹ��ð�"};
	      String[][] orderList=new String[0][6];
	      dtmOrdList = new DefaultTableModel(orderList, orderColumn);
	      tOrdList=new JTable(dtmOrdList) {
	    	  @Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
	      };
	      //--------------------------------�ֹ�Ȯ��------------------------------
	      
	     
	      //------------------------ �¼�����----------------------------
	      int x=170, y=150;
	      btnSeats=new JButton[20];
	      for(int i=0 ; i<20 ; i++) {
	    	 btnSeats[i] = new JButton("�¼�"+(i+1));
	    	 btnSeats[i].setForeground(Color.white);
	         jpSeats.add(btnSeats[i]);
	         btnSeats[i].setBackground(new Color(0x282827));
	         btnSeats[i].setBounds(x, y, 150, 150);
	         x+=160;
	         switch (i) {
	         case 5: x=10; y+=160; break;
	         case 13: x=170; y+=160; break;
	         }//end switch
	      }//end for
	      //------------------------ �¼�����----------------------------
	      
	      JScrollPane jsp= new JScrollPane(tPrdList);
	      jpSeats.setLayout(null);
	      
	      jpSeats.add(btnAddTime);
	      btnAddTime.setBounds(900, 10, 200, 100);
	      
	      jpProducts.setLayout(null);
	      JPanel inJp1=new JPanel();
	      jpProducts.add(inJp1);
	      inJp1.setBounds(0, 0, 1300, 500);
	      inJp1.setLayout(new GridLayout(1, 1));
	      inJp1.add(jsp);
	      
	      JPanel inJp2=new JPanel();
	      jpProducts.add(inJp2);
	      inJp2.setBounds(0,500,1300,250);
	      inJp2.setLayout(null);
	      
	      btnPrdAdd = new JButton("��ǰ�߰�");
	      btnPrdAdd.setBounds(860, 80, 100, 50);
	      btnPrdAdd.setBackground(new Color(0x282827));
	      btnPrdAdd.setForeground(Color.white);
	      btnPrdDelete = new JButton("��ǰ����");
	      btnPrdDelete.setBounds(980, 80, 100, 50);
	      btnPrdDelete.setBackground(new Color(0x282827));
	      btnPrdDelete.setForeground(Color.white);
	      btnPrdUpdate = new JButton("��ǰ����");
	      btnPrdUpdate.setBounds(1100, 80, 100, 50);
	      btnPrdUpdate.setBackground(new Color(0x282827));
	      btnPrdUpdate.setForeground(Color.white);
	      
	      inJp2.add(btnPrdAdd);
	      inJp2.add(btnPrdDelete);
	      inJp2.add(btnPrdUpdate);
	      
	      jpOrder.setLayout(null);
	      JPanel inJp3 = new JPanel();
	      jpOrder.add(inJp3);
	      JScrollPane jsp2 = new JScrollPane(tOrdList);
	      inJp3.setBounds(0, 0, 1300,500);
	      inJp3.setLayout(new GridLayout(1, 1));
	      inJp3.add(jsp2);
	      inJp3.setBackground(new Color(0xF5461));
	      JPanel inJp4 = new JPanel();
	      jpOrder.add(inJp4);
	      inJp4.setBounds(0,500,1300,250);
	      inJp4.setLayout(null);
	      inJp4.setBackground(new Color(0xF5461));
	      
	      inJp2.setBackground(new Color(0xF5461));
	      jsp.getViewport().setBackground(new Color(0x0C353D));
	      jsp2.getViewport().setBackground(new Color(0x0C353D));
	      
	      btnOrdCancle = new JButton("�ֹ����");
	      btnOrdCancle.setBackground(new Color(0x282827));
	      btnOrdCancle.setForeground(Color.white);
	      inJp4.add(btnOrdCancle);
	      btnOrdCancle.setBounds(1100, 80, 100, 50);
	      
	      tPrdList.setRowHeight(50);
	      tOrdList.setRowHeight(50);
	      
	      jtp.addTab("�¼�", jpSeats);
	      jtp.addTab("��ǰ����", jpProducts);
	      jtp.addTab("�ֹ�����", jpOrder);
	      
	      add("Center",jtp);
	      
	      //�÷��� ���� ����
	      tPrdList.getColumnModel().getColumn(0).setPreferredWidth(100);
	      
	      setBounds(300,80,1310,750);
	      setResizable(false);
	      setVisible(true);
	      
	      PcbMasterMainEvt pmme = new PcbMasterMainEvt(this);
	      btnAddTime.addActionListener(pmme);
	      //��� �¼� ��ư�� ���� actionListener �� �ο�
	      for(int i= 0 ; i < btnSeats.length ;i++) {
	    	  btnSeats[i].addActionListener(pmme);
	      }//end for
	      
	      btnPrdAdd.addActionListener(pmme);
	      btnPrdUpdate.addActionListener(pmme);
	      btnOrdCancle.addActionListener(pmme);
	      tPrdList.addMouseListener(pmme);
	      tOrdList.addMouseListener(pmme);
	      btnPrdDelete.addActionListener(pmme);
	      
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public JButton[] getBtnSeats() {
		return btnSeats;
	}

	public void setBtnSeats(JButton[] btnSeats) {
		this.btnSeats = btnSeats;
	}

	public JButton getBtnAddTime() {
		return btnAddTime;
	}

	public void setBtnAddTime(JButton btnAddTime) {
		this.btnAddTime = btnAddTime;
	}

	public JButton getBtnOrdCancle() {
		return btnOrdCancle;
	}

	public void setBtnOrdCancle(JButton btnOrdCancle) {
		this.btnOrdCancle = btnOrdCancle;
	}

	public JButton getBtnPrdAdd() {
		return btnPrdAdd;
	}

	public void setBtnPrdAdd(JButton btnPrdAdd) {
		this.btnPrdAdd = btnPrdAdd;
	}

	public JButton getBtnPrdUpdate() {
		return btnPrdUpdate;
	}

	public void setBtnPrdUpdate(JButton btnPrdUpdate) {
		this.btnPrdUpdate = btnPrdUpdate;
	}

	public JButton getBtnPrdDelete() {
		return btnPrdDelete;
	}

	public void setBtnPrdDelete(JButton btnPrdDelete) {
		this.btnPrdDelete = btnPrdDelete;
	}

	public DefaultTableModel getDtmOrdList() {
		return dtmOrdList;
	}

	public void setDtmOrdList(DefaultTableModel dtmOrdList) {
		this.dtmOrdList = dtmOrdList;
	}

	public DefaultTableModel getDtmPrdList() {
		return dtmPrdList;
	}

	public void setDtmPrdList(DefaultTableModel dtmPrdList) {
		this.dtmPrdList = dtmPrdList;
	}

	public JTable gettOrdList() {
		return tOrdList;
	}

	public void settOrdList(JTable tOrdList) {
		this.tOrdList = tOrdList;
	}

	public JTable gettPrdList() {
		return tPrdList;
	}

	public void settPrdList(JTable tPrdList) {
		this.tPrdList = tPrdList;
	}

	public DefaultComboBoxModel<?> getPrdcate() {
		return prdcate;
	}

	public void setPrdcate(DefaultComboBoxModel<?> prdcate) {
		this.prdcate = prdcate;
	}


	public JTabbedPane getJtp() {
		return jtp;
	}
}
