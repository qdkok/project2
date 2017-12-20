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
			prop.load(new FileReader("C:/dev/git/project2/Project_2/src/kr/co/sist/pcbmaster/dao/database.properties"));//kdy
//			prop.load(new FileReader("C:/dev/git/project2_sub/Project_2/src/kr/co/sist/pcbmaster/dao/database.properties"));
		
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
	
	public List<SetSeatsVO> seats() throws SQLException{
		List<SetSeatsVO> seatList=new ArrayList<SetSeatsVO>();
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			con=getConn();
			String seat="select s.seats_num,m.mem_id,m.name,m.left_time from  seats s, member m where (s.mem_id=m.mem_id) and login_status='Y'"; 
			
			pstmt=con.prepareStatement( seat );
			rs=pstmt.executeQuery();                
			SetSeatsVO splVO =null; 
			while(rs.next()) {
				splVO=new SetSeatsVO(rs.getString("mem_id"),rs.getString("seats_num"), rs.getString("name"), rs.getInt("left_time"));
				seatList.add(splVO);
			}//while
		}finally {
			dbClose(con, pstmt, rs);
		}//finally
		
		return seatList;
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
	
	//모든 주문 목록을 조회하는 일
	public List<SetOrdListVO> ordList() throws SQLException{
		List<SetOrdListVO> sol= new ArrayList<SetOrdListVO>();
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			//1.드라이버 로딩
			//2.커넥션 얻기
			con=getConn();
			//3.쿼리문 생성 객체 얻기
			String listord="select o.order_num,o.seats_num,p.prd_name,o.quantity,p.price*o.quantity price,o.status,to_char(o.order_time,'yyyy-mm-dd hh:mm') order_time from  orderlist o,product p where (o.prd_num=p.prd_num) order by order_time"; 
			
			pstmt=con.prepareStatement( listord );
			//4.쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			SetOrdListVO solVO =null; 
			while(rs.next()) {
				solVO=new SetOrdListVO(rs.getString("order_num"), rs.getString("seats_num"), rs.getString("prd_name"), rs.getString("status"), rs.getString("order_time"), rs.getInt("quantity"), rs.getInt("price"));
				sol.add(solVO);
			}//while
		}finally {
			//5.연결 끊기
			dbClose(con, pstmt, rs);
		}//finally
		
		
		return sol;
	}//ordList
	
	public void ordDelete(String ordNum) throws SQLException {//주문취소 전환

		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			con=getConn();
			String ordDel="delete from orderlist where order_num=?"; 
			
			pstmt=con.prepareStatement( ordDel );
			pstmt.setString(1, ordNum);//index 1번부터
			pstmt.executeUpdate();
			
		}finally {
			dbClose(con, pstmt, rs);
		}//finally
		
	}//ordDelete
	
	public void ordClear(String ordNum) throws SQLException {//주문완료 전환
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			con=getConn();
			String ordDel="update orderlist set end_time=sysdate, status='Y'  where order_num=?"; 
			
			pstmt=con.prepareStatement( ordDel );
			pstmt.setString(1, ordNum);//index 1번부터
			pstmt.executeUpdate();
			
		}finally {
			dbClose(con, pstmt, rs);
		}//finally
	}//ordClear
	
	public List<SetPrdListVO> prdList() throws SQLException{
		List<SetPrdListVO> spl=new ArrayList<SetPrdListVO>();
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			con=getConn();
			String listprd="select prd_num, prd_name, price, category, to_char(input_date,'yyyy-mm-dd hh:MM')input_date from product order by input_date"; 
			
			pstmt=con.prepareStatement( listprd );
			rs=pstmt.executeQuery();                
			SetPrdListVO splVO =null; 
			while(rs.next()) {
				splVO=new SetPrdListVO(rs.getString("prd_num"), rs.getString("category"), rs.getString("prd_name"),  rs.getString("input_date"), rs.getInt("price"));
				spl.add(splVO);
			}//while
		}finally {
			dbClose(con, pstmt, rs);
		}//finally
		
		
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
	
	public SetUserVO setUser(String seatNum) throws SQLException {
		
		
		Connection con = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		SetUserVO su=null;
		try {
			con=getConn();
			StringBuffer seat = new StringBuffer(); 
			seat.append("select to_char(s.LOGIN_TIME,'yyyy-mm-dd hh:mm')login_time,m.mem_id,m.name,m.left_time")
				.append(" from  seats s, member m").append(" where (s.mem_id=m.mem_id) and LOGIN_STATUS='Y' and s.seats_num=? "); 
			pstmt=con.prepareStatement( seat.toString() );
			pstmt.setString(1, seatNum);
			rs=pstmt.executeQuery();                
			if(rs.next()) {
				su=new SetUserVO(rs.getString("mem_id"), rs.getString("name"),rs.getString("login_time"), rs.getInt("left_time"));
			}//while
		}finally {
			dbClose(con, pstmt, rs);
		}//finally
		
		
		return su; 
	}
}//class
