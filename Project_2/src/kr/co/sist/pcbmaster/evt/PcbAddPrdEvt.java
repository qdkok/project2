package kr.co.sist.pcbmaster.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
			if(!"jpg,gif,png".contains(ext)) {
				JOptionPane.showMessageDialog(papf, "�̹��������� �������� �ʽ��ϴ�.");
				return;
			}//end if
			
			//��Ʈ���� ����Ͽ� �̹����� �̹��� ��������� ����
			String runDir = System.getProperty("user.dir");
			
			File readFile = new File(path+file);
			writeFile = new File(runDir+"/img/"+file);
			
//			if(writeFile.exists()) {//������ ����Ǵ� ������ ���� �̸��� ������ �����Ѵٸ� ���ϸ��� �����ϵ��� �����Ѵ�
//				JOptionPane.showMessageDialog(papf, "�����̸��� �̹����� �����մϴ�. \n �ٸ� �̸����� �����Ͽ� �̹����� ������ּ���.");
//				return;
//			}//end if
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			       
			try {
				byte[] readData = new byte[512];
				int temp=0;
				//���Ͽ��� �о� ���̴� ��Ʈ��.
//				fis=new FileInputStream(readFile);
				//�������� ������ �����ϴ� ��Ʈ��	
//				fos=new FileOutputStream(writeFile);
				//ū �̹��� ���
				while( (temp=fis.read(readData)) != -1 ) {
					//���Ͽ��� �о���� ������ ���Ϸ� ������.
					fos.write(readData, 0, temp);
				}//end while
				fos.flush();
				if(fis!=null) {fis.close();}//end if
				if(fos!=null) {fos.close();}//end if
				readData = null;
									
				//���� ����� ����� �Ǿ��ٸ� �⺻ �̹������� �ø� �̹����� �̸����� ����
				JLabel lblTemp = papf.getLblMenuImg();
				ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
				lblTemp.setIcon(img);
				
				JOptionPane.showMessageDialog(papf, "�̹����� ���������� ��ϵǾ����ϴ�.");
				
			}finally {
				if(fis!=null) {fis.close();}//end if
				if(fos!=null) {fos.close();}//end if
			}//end finally
			
		}//end if
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==papf.getPrdOk()) {
			JOptionPane.showConfirmDialog(null, "��ǰ�� �߰� �Ͻðڽ��ϱ�?");
		}//end if
		if(ae.getSource()==papf.getPrdAddImg()) {
			try {
				setImg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(papf, "�־ȵǴ°ž� ����");
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed
 	
}//class
