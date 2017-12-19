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
		FileDialog fdImg = new FileDialog(papf, "메뉴 이미지 선택", FileDialog.LOAD);
		fdImg.setVisible(true);
		
		String path = fdImg.getDirectory(); //string 형으로 폴더명 변환
		String file = fdImg.getFile(); //string 형으로 파일명 변환
		
		if(file != null) {
			//이미지 파일만 선택하도록 확장자 비교
			//파일명을 . 으로 구분되는 곳을 쪼갠 뒤 배열에 넣음
			String[] arrFile=file.split("[.]");
			String ext = arrFile[arrFile.length-1];
			if(!"jpg,gif,png".contains(ext)) {
				JOptionPane.showMessageDialog(papf, "이미지형식이 적절하지 않습니다.");
				return;
			}//end if
			
			//스트림을 사용하여 이미지를 이미지 사용폴더에 복사
			String runDir = System.getProperty("user.dir");
			
			File readFile = new File(path+file);
			writeFile = new File(runDir+"/img/"+file);
			
//			if(writeFile.exists()) {//파일이 복사되는 폴더에 같은 이름의 파일이 존재한다면 파일명을 변경하도록 설정한다
//				JOptionPane.showMessageDialog(papf, "동일이름의 이미지가 존재합니다. \n 다른 이름으로 변경하여 이미지를 등록해주세요.");
//				return;
//			}//end if
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			       
			try {
				byte[] readData = new byte[512];
				int temp=0;
				//파일에서 읽어 들이는 스트림.
//				fis=new FileInputStream(readFile);
				//목적지에 파일을 생성하는 스트림	
//				fos=new FileOutputStream(writeFile);
				//큰 이미지 기록
				while( (temp=fis.read(readData)) != -1 ) {
					//파일에서 읽어들인 내용을 파일로 보낸다.
					fos.write(readData, 0, temp);
				}//end while
				fos.flush();
				if(fis!=null) {fis.close();}//end if
				if(fos!=null) {fos.close();}//end if
				readData = null;
									
				//파일 등록이 제대로 되었다면 기본 이미지에서 올린 이미지로 미리보기 변경
				JLabel lblTemp = papf.getLblMenuImg();
				ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
				lblTemp.setIcon(img);
				
				JOptionPane.showMessageDialog(papf, "이미지가 정상적으로 등록되었습니다.");
				
			}finally {
				if(fis!=null) {fis.close();}//end if
				if(fos!=null) {fos.close();}//end if
			}//end finally
			
		}//end if
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==papf.getPrdOk()) {
			JOptionPane.showConfirmDialog(null, "상품을 추가 하시겠습니까?");
		}//end if
		if(ae.getSource()==papf.getPrdAddImg()) {
			try {
				setImg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(papf, "왜안되는거야 ㅅㅂ");
				e.printStackTrace();
			}
		}//end if
		
	}//actionPerformed
 	
}//class
