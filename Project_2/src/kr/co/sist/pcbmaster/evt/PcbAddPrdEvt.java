package kr.co.sist.pcbmaster.evt;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbSetMenuVO;
import kr.co.sist.pcbmaster.dao.PcbDAO;
import kr.co.sist.pcbmaster.frm.PcbAddPrdFrm;
import kr.co.sist.pcbmaster.frm.PcbMasterMainFrm;
import kr.co.sist.pcbmaster.vo.PrdItemVO;

public class PcbAddPrdEvt implements ActionListener{
	private PcbAddPrdFrm papf;
	private File writeFile;

	public PcbAddPrdEvt(PcbAddPrdFrm papf,PcbMasterMainFrm pmmf) {
		super();
		this.papf = papf;
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
		
		//�޺��ڽ� �ѱ�� ���� ����
		JComboBox<String> cb = papf.getJcbcate();
		System.out.println(cb.getSelectedItem());
		//���� ����� �ԷµǾ��ٸ� �߻��� ���� ��� ó���ϱ� ���� VO�� �߰�
		/*PrdItemVO pvo = new PrdItemVO(prdName.getText().trim(), writeFile.getName(), prdCate, prdPrice.getText().trim());*/
		
	}//prdAdd
	
	public void prdUpdate() {
		
	}//prdUpdate

	public void setPrdData(PrdItemVO prdItem) {
		
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
			       
			
			//���Ͽ��� �о� ���̴� ��Ʈ��.
			fis=new FileInputStream(readFile);
			//�������� ������ �����ϴ� ��Ʈ��	
			fos=new FileOutputStream(writeFile);
			//ū �̹��� ���
			while( (temp=fis.read(readData)) != -1 ) {
			//���Ͽ��� �о���� ������ ���Ϸ� ������.
				fos.write(readData, 0, temp);
			}//end while
			fos.flush();
			if(fis!=null) {fis.close();}//end if
			if(fos!=null) {fos.close();}//end if
			readData = null;
				
			fis=new FileInputStream(readFile);
		    fos=new FileOutputStream(writeFile);
		    readData=new byte[512];
		    while((temp=fis.read(readData))!=-1) {
		        fos.write(readData, 0, temp);
		    }//end while
		   fos.flush();
									
			//���� ����� ����� �Ǿ��ٸ� �⺻ �̹������� �ø� �̹����� �̸����� ����
			JLabel lblTemp = papf.getLblMenuImg();
			ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
			Image originImg = img.getImage();
			Image changeImg = originImg.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon finalImg = new ImageIcon(changeImg);
			
			lblTemp.setIcon(finalImg);
				
			JOptionPane.showMessageDialog(papf, "�̹����� ���������� ��ϵǾ����ϴ�.");
			
		}//end if
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==papf.getPrdOk()) {
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
				JOptionPane.showMessageDialog(papf, "�־ȵǴ°ž� ����");
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==papf.getJcbcate()) {
			JComboBox<String> cb = papf.getJcbcate();
			System.out.println(cb.getSelectedItem());
		}
		
	}//actionPerformed
 	
}//class
