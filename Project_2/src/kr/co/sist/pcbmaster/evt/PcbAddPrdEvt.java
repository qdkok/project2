package kr.co.sist.pcbmaster.evt;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.PrdItemVO;

public class PcbAddPrdEvt extends WindowAdapter implements ActionListener{
	private PcbAddPrdFrm papf;
	private File writeFile;
	private PcbMasterMainEvt pmme;
	private boolean flag,okflag,updateFlag; //�߰�or���� Ȯ�� �÷���
	private String prdNum;
	
	
	@Override
	public void windowClosing(WindowEvent e) {
		papf.dispose();
	}
	@Override
	public void windowClosed(WindowEvent e) {
		if(flag) {//�����϶�
			if(!okflag) {
				if(updateFlag) {//�̹����� ��ω������� �����
					writeFile.delete();
				}//end if
			}//end if
		}//end if
		if(!flag) {//�߰��϶�
			if(!okflag) {
				if(writeFile!=null) {//�̹����� ��ω������� �����
					writeFile.delete();
				}//end if
			}//end if
		}//end if
		super.windowClosed(e);
	}
	
	
	public PcbAddPrdEvt(PcbAddPrdFrm papf,PcbMasterMainFrm pmmf,PcbMasterMainEvt pmme, boolean flag) {
		super();
		this.papf = papf;
		this.pmme = pmme;
		this.flag = flag;
		
		if(flag) {
			JTable jtemp=pmmf.gettPrdList();
			prdNum=(String)jtemp.getValueAt(jtemp.getSelectedRow(), 0);
			setPrdData(prdNum);
//			System.out.println(papf.getPrdcate().getSelectedItem());
		}//end if
		
	}//PcbPrdEvt
	public void prdAdd() {
		if(writeFile == null) {
			JOptionPane.showMessageDialog(papf, "��ǰ �̹����� ������ �ּ���");
			return;
		}//end if
		
		//��ǰ��, ���� �׸� �־������� Ȯ��
		JTextField prdName=papf.getPrdName();
		JTextField prdPrice=papf.getPrdPrice();
		 
		//��ǰ���� �Էµ��� ���� ��� �޽��� ���̾�α� 
		if(prdName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(papf, "��ǰ�� �Է��Ͻÿ�.");
			prdName.requestFocus();
			return;
		}//end if
		
		//��ǰ ���� �Է� �� ������ ��ȯ
		int intPrice = 0;
		try {
			intPrice=Integer.parseInt(prdPrice.getText().trim());
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(papf, "������ ���ڸ� �Է����ּ���");
			prdPrice.requestFocus();
			return;
		}//end catch
		
		String img = writeFile.getName();
		String name = papf.getPrdName().getText().trim();
		
		//�޺��ڽ� �ѱ�� ���� ����
		JComboBox<String> cb = papf.getJcbcate();
		String cate = (String) cb.getSelectedItem();
		
		//���� ����� �ԷµǾ��ٸ� �߻��� ���� ��� ó���ϱ� ���� VO�� �߰�
		/*PrdItemVO pvo = new PrdItemVO(prdName.getText().trim(), writeFile.getName(), prdCate, prdPrice.getText().trim());*/
		
		PcbDAO p_dao = PcbDAO.getInstance();
		
		try {
			p_dao.addPrdItme(new PrdItemVO(name, img, cate, intPrice));
			JOptionPane.showMessageDialog(papf, "��ǰ��Ͽ� �����ϼ̽��ϴ�.");
			pmme.setPrdList();
			okflag=true;
			papf.dispose();
		} catch (SQLException e) {
			System.out.println("��ǰ����� ���� �߻�");
			e.printStackTrace();
		}
				
	}//prdAdd
	
	public void prdUpdate() {
		if(writeFile == null) {
			JOptionPane.showMessageDialog(papf, "��ǰ �̹����� ������ �ּ���");
			return;
		}//end if
		
		//��ǰ��, ���� �׸� �־������� Ȯ��
		JTextField prdName=papf.getPrdName();
		JTextField prdPrice=papf.getPrdPrice();
		 
		//��ǰ���� �Էµ��� ���� ��� �޽��� ���̾�α� 
		if(prdName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(papf, "��ǰ�� �Է��Ͻÿ�.");
			prdName.requestFocus();
			return;
		}//end if
		
		//��ǰ ���� �Է� �� ������ ��ȯ
		int intPrice = 0;
		try {
			intPrice=Integer.parseInt(prdPrice.getText().trim());
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(papf, "������ ���ڸ� �Է����ּ���");
			prdPrice.requestFocus();
			return;
		}//end catch
		
		String img = writeFile.getPath();
		String name = papf.getPrdName().getText().trim();
		
		//�޺��ڽ� �ѱ�� ���� ����
		JComboBox<String> cb = papf.getJcbcate();
		String cate = (String) cb.getSelectedItem();
		
		
		PcbDAO p_dao = PcbDAO.getInstance();
		PrdItemVO pi = new PrdItemVO(name, img, cate, intPrice);
		try {
			p_dao.editPrdItme(pi, prdNum);
			JOptionPane.showMessageDialog(papf, "��ǰ������ �����Ͽ����ϴ�.");
			pmme.setPrdList();
			okflag=true;
			papf.dispose();
		} catch (SQLException e) {
			System.out.println("��ǰ����� ���� �߻�");
			e.printStackTrace();
		}
	}//prdUpdate

	public void setPrdData(String prdNum) {
		PcbDAO p_dao =PcbDAO.getInstance();
		try {
			PrdItemVO pi = p_dao.setPrdItme(prdNum);
			papf.getPrdName().setText(pi.getPrdName());
			papf.getPrdPrice().setText(pi.getPrdPrice()+"");
			papf.getPrdcate().setSelectedItem(pi.getPrdCate());
			
			writeFile = new File(System.getProperty("user.dir")+"/img/"+pi.getPrdImg());
			
			ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
			Image originImg = img.getImage();
			Image changeImg = originImg.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon finalImg = new ImageIcon(changeImg);
			papf.getLblMenuImg().setIcon(finalImg);
			
		} catch (SQLException e) {
			System.out.println("��ǰ�� �ҷ����� ���� �����߻�");
			e.printStackTrace();
		}
		
	}//setItem 
	
	public void setImg() throws IOException {
		FileDialog fdImg = new FileDialog(papf, "�޴� �̹��� ����", FileDialog.LOAD);
		fdImg.setVisible(true);
		
		String path = fdImg.getDirectory(); //string ������ ������ ��ȯ
		String file = fdImg.getFile(); //string ������ ���ϸ� ��ȯ
		
		if(file != null) {
			//�̹��� ���ϸ� �����ϵ��� Ȯ���� ��
			//���ϸ��� . ���� ���еǴ� ���� �ɰ� �� �迭�� ����
			String[] arrFile=file.split("[.]");
			String ext = arrFile[arrFile.length-1];
			if(!"jpg,gif,png,JPG,GIF,PNG".contains(ext)) {
				JOptionPane.showMessageDialog(papf, "�̹��������� �������� �ʽ��ϴ�.");
				return;
			}//end if
			
			//��Ʈ���� ����Ͽ� �̹����� �̹��� ��������� ����
			String runDir = System.getProperty("user.dir");
			
			File readFile = new File(path+file);
			writeFile = new File(runDir+"/img/"+file);
			
			if(writeFile.exists()) {//������ ����Ǵ� ������ ���� �̸��� ������ �����Ѵٸ� ���ϸ��� �����ϵ��� �����Ѵ�
				JOptionPane.showMessageDialog(papf, "�����̸��� �̹����� �����մϴ�. \n �ٸ� �̸����� �����Ͽ� �̹����� ������ּ���.");
				return;
			}//end if
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			byte[] readData = new byte[512];
			int temp=0;
			
			fis=new FileInputStream(readFile);
		    fos=new FileOutputStream(writeFile);
		    readData=new byte[512];
		    while((temp=fis.read(readData))!=-1) {
		        fos.write(readData, 0, temp);
		    }//end while
		   fos.flush();
		   
		   if(fis!=null) {fis.close();}//end if
		   if(fos!=null) {fos.close();}//end if
									
			//���� ����� ����� �Ǿ��ٸ� �⺻ �̹������� �ø� �̹����� �̸����� ����
			JLabel lblTemp = papf.getLblMenuImg();
			ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
			Image originImg = img.getImage();
			Image changeImg = originImg.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon finalImg = new ImageIcon(changeImg);
			
			lblTemp.setIcon(finalImg);
				
			JOptionPane.showMessageDialog(papf, "�̹����� ���������� ��ϵǾ����ϴ�.");
			updateFlag=true;//�̹��������� ���������� ��ϵǾ�����
			
		}//end if
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==papf.getPrdOk()) {

			if(flag) {
				switch(JOptionPane.showConfirmDialog(papf, "��ǰ�� ���� �Ͻðڽ��ϱ�?")) {
				case JOptionPane.OK_OPTION:{
					prdUpdate();
					}
				}//switch
				return;
			}//end if
			
			switch(JOptionPane.showConfirmDialog(papf, "��ǰ�� �߰� �Ͻðڽ��ϱ�?")) {
			case JOptionPane.OK_OPTION:{
				prdAdd();
				}
			}//switch
		}//end if
		if(ae.getSource()==papf.getPrdAddImg()) {
			try {
				setImg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(papf, "�̹��� ���� ����");
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==papf.getPrdCancle()) {
			papf.dispose();
		}//end if
		
	}//actionPerformed
 	
}//class
