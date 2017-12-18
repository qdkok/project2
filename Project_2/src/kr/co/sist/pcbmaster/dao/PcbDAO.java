package kr.co.sist.pcbmaster.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kr.co.sist.pcbmaster.vo.AddTimeVO;
import kr.co.sist.pcbmaster.vo.PcbMasterLoginVO;
import kr.co.sist.pcbmaster.vo.PrdItemVO;
import kr.co.sist.pcbmaster.vo.SearchIdVO;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;
import kr.co.sist.pcbmaster.vo.SetPrdListVO;
import kr.co.sist.pcbmaster.vo.SetSeatsVO;
import kr.co.sist.pcbmaster.vo.SetUserVO;

public class PcbDAO {
	private static PcbDAO p_dao;

	private PcbDAO() {
		 
	}//DosotDAO
	
	public static PcbDAO getInstance() {
		if(p_dao ==null) {
			p_dao=new PcbDAO();
		}//end if
		return p_dao;
	}//DosotDAO

	private Connection getConn() throws SQLException {
		Connection con = null;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:/dev/git/project2/Project_2/src/kr/co/sist/pcbmaster/dao/database.properties"));
		
			Class.forName(prop.getProperty("driverClass"));
	
			con=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("id"),prop.getProperty("pass"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}//getConn
	
	private void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException{
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(con!=null) {con.close();}
	}//dbClose
	
	public boolean masterLogin(PcbMasterLoginVO pml) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String id=pml.getId();
		String pass=pml.getPass();
		
		boolean result=false;
		try {
			con=getConn();
			
			String login="select pass from master where id='"+id+"'"; 
			
			pstmt=con.prepareStatement( login );
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pass").equals(pass)) {
					result=true;
				}//end if
			}//end if
		}finally {
			dbClose(con, pstmt, rs);
		}
		
		return result;
	}//masterLogin
	
	public List<SetSeatsVO> seats(){
		List<SetSeatsVO> l=null;
		return l;
	}//seats
	
	public AddTimeVO addTime() {
		AddTimeVO at=null;
		return at;
	}//AddTimeVO
	
	public List<SearchIdVO> searchId(){
		List<SearchIdVO> sid=null;
		return sid;
	}//searchId
	
	public void seatUpdate(String seatNum) {
		
	}//seatUpdate
	
	public List<SetOrdListVO> ordList(){
		List<SetOrdListVO> sol=null;
		return sol;
	}//ordList
	
	public void ordDelete(String ordNum) {
		
	}//ordDelete
	
	public void ordClear(String ordNum) {
		
	}//ordClear
	
	public List<SetPrdListVO> prdList() throws SQLException{
		List<SetPrdListVO> spl=new ArrayList<SetPrdListVO>();
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			con=getConn();
			String listprd="select prd_num, prd_name, price, category, input_date from product"; 
			
			pstmt=con.prepareStatement( listprd );
			rs=pstmt.executeQuery();
			SetPrdListVO splVO =null; 
			while(rs.next()) {
				splVO=new SetPrdListVO(rs.getString("prd_num"), rs.getString("category"), rs.getString("prd_name"),  rs.getString("input_date"), rs.getInt("price"));
				spl.add(splVO);
			}//while
		}finally {
			dbClose(con, pstmt, rs);
		}
		
		
		return spl;
	}//prdList
	
	public void addPrdItme(PrdItemVO pi) {
		
	}//addPrdItme
	public void setPrdItme(PrdItemVO pi) {
		
	}//setPrdItme
	public void editPrdItme(PrdItemVO pi) {
		
	}//editPrdItme
	
	public void delPrd(String prdNum) {
		
	}//delPrd
	
	public SetUserVO setUser() {
		SetUserVO su=null;
		return su; 
	}
}//class
