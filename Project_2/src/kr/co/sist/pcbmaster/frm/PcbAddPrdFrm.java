package kr.co.sist.pcbmaster.frm;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.pcbmaster.evt.PcbAddPrdEvt;
import kr.co.sist.pcbmaster.evt.PcbMasterMainEvt;

public class PcbAddPrdFrm extends JFrame {
   private PcbMasterMainEvt pmme;
   private PcbSearchFrm pmmf;
   private ImageIcon img;
   private JLabel ldlMenuImg;//���õ� �̹����� �������� Label
   private JTextField prdName,prdPrice;//��ǰ��, �ܰ�
   private JButton prdOk,prdCancle,prdAddImg;//Ȯ��, ���, �̹������
   private DefaultComboBoxModel<String> prdcate; //��ǰ���
   private JComboBox<String> jcbcate;//��ǰ��� �� �޺��ڽ�
   private boolean flag;
   
   public PcbAddPrdFrm(PcbMasterMainEvt pmme, PcbSearchFrm pmmf, boolean flag) {
      super("��ǰ�߰�");
      this.pmme = pmme;
      this.pmmf = pmmf;
      
      JPanel jpimg = new JPanel(); 
      img=new ImageIcon();
      
      prdAddImg=new JButton("�̹������");
      prdOk= new JButton("Ȯ��");
      prdCancle=new JButton("���");
      
      JLabel lblcategory = new JLabel("��ī�װ� ����");
      JLabel lblname = new JLabel("�ݻ�ǰ��");
      JLabel lblprice = new JLabel("�ݰ���");
      
      prdcate=new DefaultComboBoxModel<>();
      
      //-------------------��ǰ ��� ��-----------------
      String[]cate=new String[] {"���","����","�����"};
      for(String addc:cate) {
         prdcate.addElement(addc);
      }//end for
      
      jcbcate = new JComboBox<String>(prdcate);
      //---------------------��ǰ ��� ��-----------------
      
      prdName=new JTextField("��ǰ�� �Է�");
      prdPrice=new JTextField("�ܰ� �Է�");
      
      setLayout(null);
      add(jpimg);
      add(prdAddImg);
      add(prdOk);
      add(prdCancle);
      
      add(lblcategory);
      add(lblname);
      add(lblprice);
      
      add(jcbcate);
      add(prdName);
      add(prdPrice);
      
      jpimg.setBounds(100, 50, 300, 300);
      prdAddImg.setBounds(200, 380, 100, 30);
      prdOk.setBounds(120, 630, 100, 50);
      prdCancle.setBounds(280, 630, 100, 50);
      
      lblcategory.setBounds(100, 450, 100, 30);
      lblname.setBounds(100, 500, 100, 30);
      lblprice.setBounds(100, 550, 100, 30);
      
      jcbcate.setBounds(250, 450, 150, 30);
      prdName.setBounds(250, 500, 150, 30);
      prdPrice.setBounds(250, 550, 150, 30);
      
      //��ο� �׵θ� ���̰� �ӽ÷� ����
      jpimg.setBorder(new TitledBorder(""));
      
      setLayout(null);
      
      setVisible(true);
      setBounds(50,50,500,750);
      
      PcbAddPrdEvt pape = new PcbAddPrdEvt(this, null);
      prdOk.addActionListener(pape);
      prdAddImg.addActionListener(pape);
      
   }//PcbPrdFrm
   
   public PcbMasterMainEvt getPmme() {
      return pmme;
   }
   
   public void setPmme(PcbMasterMainEvt pmme) {
      this.pmme=pmme;
   }
   
   public PcbSearchFrm getPmmf() {
      return pmmf;
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
   
   public static void main(String[] args) {
    new PcbAddPrdFrm(null,null,false);
}
   
}//class