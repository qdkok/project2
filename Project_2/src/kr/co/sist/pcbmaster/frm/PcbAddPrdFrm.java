package kr.co.sist.pcbmaster.frm;


import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.pcbmaster.evt.PcbAddPrdEvt;
import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

@SuppressWarnings("serial")
public class PcbAddPrdFrm extends JFrame {
   private PcbMasterMainEvt pmme;
   private PcbMasterMainFrm pmmf;
   private ImageIcon img;
   private JLabel lblMenuImg;//선택된 이미지가 보여지는 Label
   private JTextField prdName,prdPrice;//상품명, 단가
   private JButton prdOk,prdCancle,prdAddImg;//확인, 취소, 이미지등록
   private DefaultComboBoxModel<String> prdcate; //상품목록
   private JComboBox<String> jcbcate;//상품목록 탭 콤보박스
   
   public PcbAddPrdFrm(PcbMasterMainEvt pmme, PcbMasterMainFrm pmmf, boolean flag) {
      super("상품추가");
      this.pmme = pmme;
      this.pmmf = pmmf;
      
     img= new ImageIcon("");
     lblMenuImg = new JLabel("");// 선택된 이미지 보여지는
     lblMenuImg.setIcon(img);
      
     prdAddImg=new JButton("이미지등록");
     prdAddImg.setBackground(new Color(0x282827));
     prdAddImg.setForeground(Color.white);
     prdOk= new JButton("확인");
     prdOk.setBackground(new Color(0x282827));
     prdOk.setForeground(Color.white);
     prdCancle=new JButton("취소");
     prdCancle.setBackground(new Color(0x282827));
     prdCancle.setForeground(Color.white);
      
      JLabel lblcategory = new JLabel("◎카테고리 설정");
      JLabel lblname = new JLabel("◎상품명");
      JLabel lblprice = new JLabel("◎가격");
      
      lblcategory.setForeground(Color.WHITE);
      lblname.setForeground(Color.WHITE);
      lblprice.setForeground(Color.WHITE);
      
      prdcate=new DefaultComboBoxModel<>();
      
      //-------------------상품 목록 탭-----------------
      String[]cate=new String[] {"라면","과자","음료"};
      for(String addc:cate) {
         prdcate.addElement(addc);
      }//end for
      
      jcbcate = new JComboBox<String>(prdcate);
      //---------------------상품 목록 탭-----------------
      
      prdName=new JTextField("상품명 입력");
      prdPrice=new JTextField("단가 입력");
      
      setLayout(null);
      add(lblMenuImg);
      add(prdAddImg);
      add(prdOk);
      add(prdCancle);
      
      add(lblcategory);
      add(lblname);
      add(lblprice);
      
      add(jcbcate);
      add(prdName);
      add(prdPrice);
      
      lblMenuImg.setBounds(100, 50, 300, 300);
      prdAddImg.setBounds(200, 380, 100, 30);
      prdOk.setBounds(120, 630, 100, 50);
      prdCancle.setBounds(280, 630, 100, 50);
      
      lblcategory.setBounds(100, 450, 100, 30);
      lblname.setBounds(100, 500, 100, 30);
      lblprice.setBounds(100, 550, 100, 30);
      
      jcbcate.setBounds(250, 450, 150, 30);
      prdName.setBounds(250, 500, 150, 30);
      prdPrice.setBounds(250, 550, 150, 30);
      
      lblMenuImg.setBorder(new TitledBorder("이미지"));
      
      getContentPane().setBackground(new Color(0x6C6C6C));
      
      setVisible(true);
      setResizable(false);
      setBounds(50,50,500,750);
      
      PcbAddPrdEvt pape = new PcbAddPrdEvt(this,this.pmmf,pmme,flag);
      prdOk.addActionListener(pape);
      prdAddImg.addActionListener(pape);
      prdCancle.addActionListener(pape);
      addWindowListener(pape);
      
   }//PcbPrdFrm

public JComboBox<String> getJcbcate() {
	return jcbcate;
}

public JTextField getPrdName() {
	return prdName;     
}

public JTextField getPrdPrice() {
	return prdPrice;
}

public JLabel getLblMenuImg() {
	return lblMenuImg;
}

public void setLdlMenuImg(JLabel lblMenuImg) {
	this.lblMenuImg = lblMenuImg;
}

public PcbMasterMainEvt getPmme() {
      return pmme;
   }
   
   public void setPmme(PcbMasterMainEvt pmme) {
      this.pmme=pmme;
   }
   
   
   public PcbMasterMainFrm getPmmf() {
	return pmmf;
}

public ImageIcon getImg() {
	return img;
}

public DefaultComboBoxModel<String> getPrdcate() {
	return prdcate;
}

public void viewImg() {
      
   }//viewImg
   
   public void setImg(ImageIcon img) {
      this.img = img;
   }

   public void setPrdName(JTextField prdName) {
      this.prdName = prdName;
   }

   public void setPrdPrice(JTextField prdPrice) {
      this.prdPrice = prdPrice;
   }

   public void setPrdOk(JButton prdOk) {
      this.prdOk = prdOk;
   }
   
   public JButton getPrdOk() {
	   return prdOk;
}

   public void setPrdCancle(JButton prdCancle) {
      this.prdCancle = prdCancle;
   }
   
   public JButton getPrdCancle() {
	   return prdCancle;
}

   public JButton getPrdAddImg() {
	   return prdAddImg;
}

public void setPrdAddImg(JButton prdAddImg) {
      this.prdAddImg = prdAddImg;
   }

   public void setPrdcate(DefaultComboBoxModel prdcate) {
      this.prdcate = prdcate;
   }
   
   
}//class