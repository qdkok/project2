package kr.co.sist.pcbclient.dao;

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

import kr.co.sist.pcbclient.vo.PcbNoMemLoginVO;
import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbStatusVO;
import kr.co.sist.pcbclient.vo.PcbUserJoinVO;
import kr.co.sist.pcbclient.vo.PcbUserLoginVO;

public class PcbUserDAO {
	private static PcbUserDAO pu_dao;
	
	private PcbUserDAO() {
		 
	}//DosotDAO
	
	public static PcbUserDAO getInstance() {
		if(pu_dao ==null) {
			pu_dao=new PcbUserDAO();
		}//end if
		return pu_dao;
	}//DosotDAO

	
	private Connection getConn() throws SQLException {
		Connection con = null;
		
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:/dev/workspace/javase_prj1/src/kr/co/sist/menu/dao/database.properties"));
		
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
	
	
	public boolean userIdChk() {
		System.out.println("test");
		return false;
	}
	
	public void userJoin(PcbUserJoinVO pujvo) {
		
	}
	
	public boolean userLogin(PcbUserLoginVO pulvo) {
		return false;
	}
	
	public PcbStatusVO userStatus() {
		PcbStatusVO psvo = new PcbStatusVO();
		return psvo;
	}
	
	public int userUseTime() {
		return 1;
	}
	
	public void userOrder(List<PcbOrderVO> lsPovo) {
		
	}
	
	public List<PcbOrderVO> userAllMenu(){ //전체메뉴조회
		List<PcbOrderVO> listMenu = new ArrayList<>();
		
		return listMenu;
	}
	
	public boolean noUserLogin(PcbNoMemLoginVO pnmlvo) {
		return false;
	}
	
}
