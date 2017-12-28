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
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("라면");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //상품명
				
				tempFile = new File(System.getProperty("user.dir")+"/img/"+psmv.getPrdImg());
				if(!tempFile.exists()) {
					//이미지가 없을 때 default 이미지를 띄우는 것으로 수정
					DataOutputStream dos= null;
					DataInputStream dis = null;
					FileOutputStream fos = null;
					try {
//						System.out.println(tempFile.getName());
						
						Socket client = new Socket(serverIp,socketPort);
						dos = new DataOutputStream( client.getOutputStream() );
						dis = new DataInputStream(client.getInputStream());
						
						fos = new FileOutputStream(tempFile); 
						
						dos.writeInt(NONEFILE);//주문요청
						dos.writeUTF(tempFile.getName()); //파일명 보내기
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("다운중 ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"파일 저장 성공");
						
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
			JOptionPane.showMessageDialog(pof, "메뉴 조회를 수행할 수 없습니다.");
			e.printStackTrace();
		}
	}
	
	public void setListSnack() {
		tempMenu = pof.getDtmSnack();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("과자");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //상품명
				
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
						
						dos.writeInt(NONEFILE);//주문요청
						dos.writeUTF(tempFile.getName()); //파일명 보내기
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("다운중 ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"파일 저장 성공");
						
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
			JOptionPane.showMessageDialog(pof, "메뉴 조회를 수행할 수 없습니다.");
			e.printStackTrace();
		}
	}

	public void setListDrink() {
		DefaultTableModel tempMenu = pof.getDtmDrink();
		PcbUserDAO pu_dao = PcbUserDAO.getInstance();
		
		try {
			
			List<PcbSetMenuVO> userAllMenu = pu_dao.userAllMenu("음료");
			tempMenu.setRowCount(0);
			
			Object[] rowData = null;
			
			PcbSetMenuVO psmv = null;
			File tempFile = null;
			
			for(int i=0; i< userAllMenu.size(); i++) {
				psmv = userAllMenu.get(i);
				
				rowData = new Object[4];
				
				rowData[0] = psmv.getPrdNum(); //상품명
				
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
						
						dos.writeInt(NONEFILE);//주문요청
						dos.writeUTF(tempFile.getName()); //파일명 보내기
						
						byte[] readData = new byte[512];
			            int length = dis.read(readData);
			            System.out.print("다운중 ");
			            
			            while (length != -1) {
			                System.out.print(".");
			                fos.write(readData, 0, length);
			                length = dis.read(readData);
			            }
			 
			            System.out.println(tempFile.getName()+"파일 저장 성공");
						
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
			JOptionPane.showMessageDialog(pof, "메뉴 조회를 수행할 수 없습니다.");
			e.printStackTrace();
		}
	}
	
	public void order() {
		StringBuilder sbText = new StringBuilder();
		DefaultTableModel dtm = pof.getDtmOrder();
		List<PcbOrderVO> lpo = new ArrayList<PcbOrderVO>();// db로 보낼 리스트
		sbText.append("\t\t주문 내역 확인").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t메뉴\t수량\t가격").append("\n")
			.append("--------------------------------------------------------------------------------------------------------------\n");
		
		for(int i=0; i<pof.getJtMenuList().getRowCount(); i++) {
			for(int j=1; j<pof.getJtMenuList().getColumnCount(); j++) {
				sbText.append("\t").append(dtm.getValueAt(i, j));
			}
			//좌석번호 //로그인시간 //상품번호 //수량
			lpo.add(new PcbOrderVO(psf.getLblStarttime().getText(), dtm.getValueAt(i, 0)+"", Integer.parseInt(dtm.getValueAt(i, 2)+"")) ) ;
			sbText.append("\n");
		}
		
		sbText.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t합계 : ").append("\t\t").append(pof.getLblPay().getText())
			.append("\n\n\n")
			.append("--------------------------------------------------------------------------------------------------------------\n")
			.append("\t해당 주문내용으로 주문하시겠습니까?");
		
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
						JOptionPane.showMessageDialog(pof, "주문할 항목이 없습니다.");
						return;
					}//end if
					pu_dao.userOrder(lpo);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(pof, "주문할 항목이 없습니다.");
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(pof, "주문 도중 문제가 발생했습니다.");
					e.printStackTrace();
				}
				pof.getDtmOrder().removeRow(0);
			}
			totalPay = 0;
			pof.getLblPay().setText("0");
			//주문완료했을 경우
			try {
		
				Socket client=new Socket(serverIp,socketPort);
				DataOutputStream dos=null;
				
				dos=new DataOutputStream( client.getOutputStream() );
				dos.writeInt(ORDER);//주문요청
				
				if(dos!=null) {dos.close();}//end if
				if(client!=null) {client.close();}//end if
				
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(pof, "주문이 완료되었습니다.");
			break;
		};
	}//order
	
	public void addBasket() {
		
	}
	
	public void delBasket() {
		JTable tempTbl = pof.getJtMenuList();
		int removePay = (int) tempTbl.getValueAt(tempTbl.getSelectedRow(), 3);
		switch(JOptionPane.showConfirmDialog(pof, "장바구니에서 해당 품목을 삭제하시겠습니까?")) {
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
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("주문하실 수량을 입력해주세요."));
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
						JOptionPane.showMessageDialog(pof, "숫자만 입력가능합니다.");
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
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("주문하실 수량을 입력해주세요."));
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
						JOptionPane.showMessageDialog(pof, "숫자만 입력가능합니다.");
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
						int quantity = Integer.parseInt(JOptionPane.showInputDialog("주문하실 수량을 입력해주세요."));
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
						JOptionPane.showMessageDialog(pof, "숫자만 입력가능합니다.");
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
