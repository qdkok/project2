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

import javax.swing.JOptionPane;

import kr.co.sist.pcbclient.form.PcbJoinFrm;
import kr.co.sist.pcbclient.form.PcbUserLoginFrm;
import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbSetMenuVO;
import kr.co.sist.pcbclient.vo.PcbStatusVO;
import kr.co.sist.pcbclient.vo.PcbUserJoinVO;

public class PcbUserDAO {
	private static PcbUserDAO pu_dao;
	private boolean chkFlag = false;
	
	public PcbUserDAO() {
		
	}//PcbUserDAO
	
	public static PcbUserDAO getInstance() {
			if(pu_dao == null) {
				pu_dao = new PcbUserDAO();
			}
			return pu_dao;
	}//getInstance
	
	public Connection getConn() throws SQLException {
		Connection con = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:/dev/git/project2/Project_2/src/kr/co/sist/pcbmaster/dao/database.properties"));
			
			Class.forName(prop.getProperty("driverClass"));
			
			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("id"),prop.getProperty("pass"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	private void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) throws SQLException{
		if(rs != null) { rs.close();	}
		if(pstmt != null) {pstmt.close(); }
		if(con != null) { con.close(); }
		
	}//dbClose
	
	public boolean userIdChk(PcbJoinFrm pjf) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inputId = pjf.getTfUserId().getText();
		
		try {
			con = getConn();
			
			StringBuilder userIdChk = new StringBuilder();
			userIdChk.append("select mem_id from member where mem_id=?");
			
			pstmt = con.prepareStatement(userIdChk.toString());
			pstmt.setString(1, inputId);
			
			rs = pstmt.executeQuery();
			
			if(!inputId.equals("")) {
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
					chkFlag = false;
				}else {
					JOptionPane.showMessageDialog(null, inputId+"는 사용가능한 ID입니다.");
					chkFlag = true;
				}
			}else {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			}
			
		}finally {
			dbClose(con, pstmt, rs);
		}
		
		return chkFlag;
	}
	
	public void userJoin(PcbUserJoinVO pujvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			
			String insertUser = "insert into member(mem_id, pass, name, phone, email,  join_date) values(?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(insertUser);
			pstmt.setString(1, pujvo.getId());
			pstmt.setString(2, pujvo.getPass());
			pstmt.setString(3, pujvo.getName());
			pstmt.setString(4,  pujvo.getPhone());
			pstmt.setString(5, pujvo.getEmail());
			
			pstmt.executeUpdate();
			
		} finally {
			dbClose(con, pstmt, null);
		}
		
	}//userJoin
	
	public void useSeats(String id) throws SQLException {//좌석 사용시작 메서드
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String userLogin="";
			if(id.length()>=4) {
				userLogin = "insert into seats(SEATS_NUM,LOGIN_TIME,LOGIN_STATUS,MEM_ID) values(SEAT_NUM(15),sysdate,'Y',?)";
			}else {
				userLogin = "insert into seats(SEATS_NUM,LOGIN_TIME,LOGIN_STATUS,NOMEM_ID) values(SEAT_NUM(15),sysdate,'Y',?)";
			}//end if
						
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} finally {
			dbClose(con, pstmt, rs);
		}
	}
	
	
	public boolean userLogin(PcbUserLoginFrm pulf) throws SQLException {//아이디 비밀번호 체크 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginFlag = false;
		
		try {
			con = getConn();
			
			String userLogin = "select mem_id, pass from member where mem_id=? and pass=?";
			
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, pulf.getTfUserId().getText());
			pstmt.setString(2, new String(pulf.getTfUserPass().getPassword()));
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginFlag = true;
			}//end if 아이디 체크
			
			
			
		} finally {
			dbClose(con, pstmt, rs);
		}
		return loginFlag;
	}
	
	public PcbStatusVO userStatus() {
		PcbStatusVO psvo = new PcbStatusVO();
		
		return psvo;
	}
	
	public int userUseTime() {
		return 1;
	}
	
	public void userOrder(List<PcbOrderVO> lsPovo) throws SQLException { //상품주문
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			
			String insertUser = "insert into orderlist(order_num, order_time, seats_num, prd_num, quantity, login_time) values()";
			pstmt = con.prepareStatement(insertUser);
			
			pstmt.setString(1, lsPovo.get(1).getPrdNum());
			pstmt.setString(2, lsPovo.get(1).getPrdName());
			pstmt.setInt(3, lsPovo.get(1).getQuantity());
			pstmt.setInt(4,  lsPovo.get(1).getPrice());
			
			pstmt.executeUpdate();
			
		} finally {
			dbClose(con, pstmt, null);
		}
		
		
	}//userOrder
	
	
	public List<PcbSetMenuVO> userAllMenu(String TabName) throws SQLException{ //전체메뉴조회
		List<PcbSetMenuVO> listMenu = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			StringBuilder selectMenu = new StringBuilder();
			selectMenu.append("select prd_num, img, prd_name, category prdcate, price ")
				.append(" from PRODUCT ")
				.append(" where category = ?");
			
			pstmt = con.prepareStatement(selectMenu.toString());
			pstmt.setString(1, TabName);
			rs = pstmt.executeQuery();
			
			PcbSetMenuVO mv = null;
			while(rs.next()) {
				mv = new PcbSetMenuVO(rs.getString("prd_num"), rs.getString("prd_name"), rs.getString("img"), rs.getString("prdcate"),rs.getInt("price"));
				listMenu.add(mv);
			}//end while

	}finally {
		dbClose(con, pstmt, rs);
	}//end finally
		
		return listMenu;
	}
	
	public boolean noUserLogin(PcbUserLoginFrm pulf) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean loginFlag = false;
		
		try {
			con = getConn();
			
			String noUserLogin = 
					"select nomem_id, left_time from NOMEMBER where nomem_id=?";
			
			pstmt = con.prepareStatement(noUserLogin);
			pstmt.setString(1, pulf.getTfUserId().getText());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				loginFlag = true;
			}
		} finally {
			dbClose(con, pstmt, rs);
		}
		
		return loginFlag;
	}
	
	//test단계
	public String setUser() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String testTime="";
		
		try {
			con = getConn();
			String userLogin="";
				
			//현재좌석의 사용중인 데이터의 시간을 가져온다.
			userLogin = "select to_char(LOGIN_TIME,'yyyy-mm-dd hh:mi') time from SEATS where LOGIN_STATUS='Y' and SEATS_NUM=?";
						
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, "seat_15");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				testTime=rs.getString("time");
			}//end if
			return testTime;
		} finally {
			dbClose(con, pstmt, rs);
		}
	}
	
	//test단계
	//사용종료 메서드 좌석번호를 받아 현재 좌석의 종료를 알림
	public void logout(String startTime) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String userLogin="";
				
			userLogin = "update seats set LOGIN_STATUS='N' where seats_num=? and (select to_char(LOGIN_TIME,'yyyy-mm-dd hh:mi') from SEATS where LOGIN_STATUS='Y' and SEATS_NUM=?) = ? ";
						
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, "seat_15");
			pstmt.setString(2, "seat_15");
			pstmt.setString(3, startTime);
			pstmt.executeUpdate();
			
		} finally {
			dbClose(con, pstmt, rs);
		}
	}//logout
	
	
	
	////////////////////////회원 비회원 시간사용 및 시간 깔아주기////////////
	public int userGetTime(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int leftTime = 0;
		
		try {
			con = getConn();
			
			String userLogin = 
					"select left_time from member where mem_id=?";
			
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				leftTime = rs.getInt("left_time");
			}
		} finally {
			dbClose(con, pstmt, rs);
		}
		return leftTime;
	}
	
	public int noUserGetTime(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int leftTime = 0;
		
		try {
			con = getConn();
			
			String userLogin = 
					"select left_time from nomember where nomem_id=?";
			
			pstmt = con.prepareStatement(userLogin);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				leftTime = rs.getInt("left_time");
			}
		} finally {
			dbClose(con, pstmt, rs);
		}
		return leftTime;
	}
	
	public boolean updateTime(String id, int left_time) throws SQLException{
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			
			String updateStatus = "update member set left_time=? where mem_id=?";
			pstmt = con.prepareStatement(updateStatus);
			
			pstmt.setInt(1, left_time);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				flag = true;
			}
			
		}finally {
			dbClose(con, pstmt, null);
		}
		
		return flag;
	}//updateTime
	
	public boolean noUpdateTime(String id, int left_time) throws SQLException{
		boolean flag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConn();
			
			String updateStatus = "update nomember set left_time=? where nomem_id=?";
			pstmt = con.prepareStatement(updateStatus);
			
			pstmt.setInt(1, left_time);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				flag = true;
			}
			
		}finally {
			dbClose(con, pstmt, null);
		}
		
		return flag;
	}//updateTime
	
	
}
