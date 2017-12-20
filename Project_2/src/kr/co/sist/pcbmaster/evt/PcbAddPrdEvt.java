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
			JOptionPane.showMessageDialog(papf, "상품 이미지를 선택해 주세요");
			return;
		}//end if
		
		//상품명, 가격 항목에 넣어졌는지 확인
		JTextField prdName=papf.getPrdName();
		JTextField prdPrice=papf.getPrdPrice();
		 
		//상품명이 입력되지 않을 경우 메시지 다이얼로그 
		if(prdName.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(papf, "상품명 입력하시오.");
			prdName.requestFocus();
			return;
		}//end if
		
		//상품 가격 입력 후 정수로 변환
		int intPrice = 0;
		try {
		intPrice=Integer.parseInt(prdPrice.getText().trim());
		}catch(NumberFormatException nfe) {
			JOptionPane.showMessageDialog(papf, "가격은 숫자만 입력해주세여");
			prdPrice.requestFocus();
			return;
		}//end catch 
		
		//콤보박스 넘기기 위해 선언
		JComboBox<String> cb = papf.getJcbcate();
		System.out.println(cb.getSelectedItem());
		//값이 제대로 입력되었다면 발생된 값을 묶어서 처리하기 위해 VO에 추가
		/*PrdItemVO pvo = new PrdItemVO(prdName.getText().trim(), writeFile.getName(), prdCate, prdPrice.getText().trim());*/
		
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
			if(!"jpg,gif,png,JPG,GIF,PNG".contains(ext)) {
				JOptionPane.showMessageDialog(papf, "이미지형식이 적절하지 않습니다.");
				return;
			}//end if
			
			//스트림을 사용하여 이미지를 이미지 사용폴더에 복사
			String runDir = System.getProperty("user.dir");
			
			File readFile = new File(path+file);
			writeFile = new File(runDir+"/img/"+file);
			
			if(writeFile.exists()) {//파일이 복사되는 폴더에 같은 이름의 파일이 존재한다면 파일명을 변경하도록 설정한다
				JOptionPane.showMessageDialog(papf, "동일이름의 이미지가 존재합니다. \n 다른 이름으로 변경하여 이미지를 등록해주세요.");
				return;
			}//end if
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			byte[] readData = new byte[512];
			int temp=0;
			       
			
			//파일에서 읽어 들이는 스트림.
			fis=new FileInputStream(readFile);
			//목적지에 파일을 생성하는 스트림	
			fos=new FileOutputStream(writeFile);
			//큰 이미지 기록
			while( (temp=fis.read(readData)) != -1 ) {
			//파일에서 읽어들인 내용을 파일로 보낸다.
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
									
			//파일 등록이 제대로 되었다면 기본 이미지에서 올린 이미지로 미리보기 변경
			JLabel lblTemp = papf.getLblMenuImg();
			ImageIcon img = new ImageIcon(writeFile.getAbsolutePath());
			Image originImg = img.getImage();
			Image changeImg = originImg.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon finalImg = new ImageIcon(changeImg);
			
			lblTemp.setIcon(finalImg);
				
			JOptionPane.showMessageDialog(papf, "이미지가 정상적으로 등록되었습니다.");
			
		}//end if
		
	}//setImg

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==papf.getPrdOk()) {
			switch(JOptionPane.showConfirmDialog(papf, "상품을 추가 하시겠습니까?")) {
			case JOptionPane.OK_OPTION:{
					prdAdd();
				}
			}//switch
		}//end if
		if(ae.getSource()==papf.getPrdAddImg()) {
			try {
				setImg();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(papf, "왜안되는거야 ㅅㅂ");
				e.printStackTrace();
			}
		}//end if
		if(ae.getSource()==papf.getJcbcate()) {
			JComboBox<String> cb = papf.getJcbcate();
			System.out.println(cb.getSelectedItem());
		}
		
	}//actionPerformed
 	
}//class
