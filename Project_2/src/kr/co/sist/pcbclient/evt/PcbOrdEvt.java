package kr.co.sist.pcbclient.evt;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.ImageIcon;
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
import kr.co.sist.pcbclient.form.PcbStatusFrm;
import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbSetMenuVO;

public class PcbOrdEvt extends MouseAdapter implements ActionListener,ChangeListener {
	private PcbOrdFrm pof;
	private PcbStatusFrm psf;
	private DefaultTableModel  tempMenu;
	private int totalPay = 0;
	public static final int DOUBLE_CLICK = 2;
	private static final int ORDER=3;
	private static final int NONEFILE=4;
	
	private Properties prop;
	private int socketPort;
	private String serverIp;
	
	public PcbOrdEvt(PcbOrdFrm pof,PcbStatusFrm psf) {
		this.pof = pof;
		this.psf = psf;
		
		try {
			prop = new Properties();
			prop.load(new FileReader(System.getProperty("user.dir")+"/Project_2/src/kr/co/sist/pcbclient/dao/database.properties"));
			socketPort=Integer.parseInt(prop.getProperty("socketPort"));
			serverIp=prop.getProperty("serverIp");
			
			setListRamen();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setListRamen() {
		tempMenu = pof.getDtmRamen();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("���");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				
				tempFile = new File(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				if(!tempFile.exists()) {
					//�̹����� ���� �� default �̹����� ���� ������ ����
					DataOutputStream dos= null;
					DataInputStream dis = null;
					FileOutputStream fos = null;
					try {
//						System.out.println(tempFile.getName());
						
						Socket client = new Socket(serverIp,socketPort);
						dos = new DataOutputStream( client.getOutputStream() );
						dis = new DataInputStream(client.getInputStream());
						
						fos = new FileOutputStream(tempFile); 
						
						dos.writeInt(NONEFILE);//�ֹ���û
						dos.writeUTF(tempFile.getName()); //���ϸ� ������
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("�ٿ��� ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"���� ���� ����");
						
						if(dos!=null) {dos.close();}//end if
						if(dis!=null) {dis.close();}
						if(fos!=null) {fos.close();}
						if(client!=null) {client.close();}//end if
						
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
				ImageIcon img = new ImageIcon(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				Image originImg = img.getImage();
				Image changeImg = originImg.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon finalImg = new ImageIcon(changeImg);
				
				rowData[1] = finalImg;
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
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				
				tempFile = new File(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				if(!tempFile.exists()) {
					DataOutputStream dos= null;
					DataInputStream dis = null;
					FileOutputStream fos = null;
					try {
//						System.out.println(tempFile.getName());
						
						Socket client = new Socket(serverIp,socketPort);
						dos = new DataOutputStream( client.getOutputStream() );
						dis = new DataInputStream(client.getInputStream());
						
						fos = new FileOutputStream(tempFile); 
						
						dos.writeInt(NONEFILE);//�ֹ���û
						dos.writeUTF(tempFile.getName()); //���ϸ� ������
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("�ٿ��� ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"���� ���� ����");
						
						if(dos!=null) {dos.close();}//end if
						if(dis!=null) {dis.close();}
						if(fos!=null) {fos.close();}
						if(client!=null) {client.close();}//end if
						
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				ImageIcon img = new ImageIcon(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				Image originImg = img.getImage();
				Image changeImg = originImg.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon finalImg = new ImageIcon(changeImg);
				
				rowData[1] = finalImg;
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
			
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("����");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //��ǰ��
				
				tempFile = new File(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				if(!tempFile.exists()) {
					
					DataOutputStream dos= null;
					DataInputStream dis = null;
					FileOutputStream fos = null;
					try {
						
						Socket client = new Socket(serverIp,socketPort);
						dos = new DataOutputStream( client.getOutputStream() );
						dis = new DataInputStream(client.getInputStream());
						
						fos = new FileOutputStream(tempFile); 
						
						dos.writeInt(NONEFILE);//�ֹ���û
						dos.writeUTF(tempFile.getName()); //���ϸ� ������
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("�ٿ��� ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"���� ���� ����");
						
						if(dos!=null) {dos.close();}//end if
						if(dis!=null) {dis.close();}
						if(fos!=null) {fos.close();}
						if(client!=null) {client.close();}//end if
						
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
				
				ImageIcon img = new ImageIcon(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				Image originImg = img.getImage();
				Image changeImg = originImg.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon finalImg = new ImageIcon(changeImg);
				
				rowData[1] = finalImg;
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
		DefaultTableModel dtm = pof.getDtmOrder();
		List<PcbOrderVO> lpo = new ArrayList<PcbOrderVO>();// db�� ���� ����Ʈ
		sbText.append("\t\t�ֹ� ���� Ȯ��").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t�޴�\t����\t����").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n");
		
		for(int i=0; i<pof.getJtMenuList().getRowCount(); i++) {
			for(int j=1; j<pof.getJtMenuList().getColumnCount(); j++) {
				sbText.append("\t").append(dtm.getValueAt(i, j));
			}
			//�¼���ȣ //�α��νð� //��ǰ��ȣ //����
			lpo.add(new PcbOrderVO(psf.getLblStarttime().getText(), dtm.getValueAt(i, 0)+"", Integer.parseInt(dtm.getValueAt(i, 2)+"")) ) ;
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
		
		int rowCnt = pof.getDtmOrder().getRowCount();
		int ordCnt = 0;
			
		if(rowCnt == 1) {
			ordCnt = 0;
		}//ene if
		
		switch(JOptionPane.showConfirmDialog(pof, jspOrder)) {
		case JOptionPane.OK_OPTION:
			PcbUserDAO pu_dao = PcbUserDAO.getInstance();
			for(int i=0; i<(rowCnt+ordCnt); i++) {
				try {
					if(lpo.size()==0) {
						JOptionPane.showMessageDialog(pof, "�ֹ��� �׸��� �����ϴ�.");
						return;
					}//end if
					pu_dao.userOrder(lpo);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(pof, "�ֹ��� �׸��� �����ϴ�.");
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(pof, "�ֹ� ���� ������ �߻��߽��ϴ�.");
					e.printStackTrace();
				}
				pof.getDtmOrder().removeRow(0);
			}
			totalPay = 0;
			pof.getLblPay().setText("0");
			//�ֹ��Ϸ����� ���
			try {
		
				Socket client=new Socket(serverIp,socketPort);
				DataOutputStream dos=null;
				
				dos=new DataOutputStream( client.getOutputStream() );
				dos.writeInt(ORDER);//�ֹ���û
				
				if(dos!=null) {dos.close();}//end if
				if(client!=null) {client.close();}//end if
				
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(pof, "�ֹ��� �Ϸ�Ǿ����ϴ�.");
			break;
		};
	}//order
	
	public void addBasket() {
		
	}
	
	public void delBasket() {
		JTable tempTbl = pof.getJtMenuList();
		int removePay = (int) tempTbl.getValueAt(tempTbl.getSelectedRow(), 3);
		switch(JOptionPane.showConfirmDialog(pof, "��ٱ��Ͽ��� �ش� ǰ���� �����Ͻðڽ��ϱ�?")) {
		case JOptionPane.OK_OPTION:
			totalPay -= removePay;
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
					String menuNum = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 0));
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
						
						
						rowData[0] = menuNum;
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
					String menuNum = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 0));
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
						
	
						
						rowData[0] = menuNum;
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
					String menuNum = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 0));
					String menu = (String)(tempTbl.getValueAt(tempTbl.getSelectedRow(), 2));
					try {
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("�ֹ��Ͻ� ������ �Է����ּ���."));
						int price =  Integer.parseInt(String.valueOf(tempTbl.getValueAt(tempTbl.getSelectedRow(), 3)));
						
						
						rowData[0] = menuNum;
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
