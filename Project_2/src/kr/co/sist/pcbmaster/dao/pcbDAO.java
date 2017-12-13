package kr.co.sist.pcbmaster.dao;

import java.sql.Connection;
import java.util.List;

import kr.co.sist.pcbmaster.vo.AddTimeVO;
import kr.co.sist.pcbmaster.vo.PcbMasterLoginVO;
import kr.co.sist.pcbmaster.vo.PrdItemVO;
import kr.co.sist.pcbmaster.vo.SearchIdVO;
import kr.co.sist.pcbmaster.vo.SetOrdListVO;
import kr.co.sist.pcbmaster.vo.SetPrdListVO;
import kr.co.sist.pcbmaster.vo.SetSeatsVO;
import kr.co.sist.pcbmaster.vo.SetUserVO;

public class pcbDAO {
	private static pcbDAO p_dao;

	private pcbDAO() {
		
	}//pcbDAO
	
	public Connection getConn(){
		Connection con=null;
		return con;
	}//getConn
	
	static pcbDAO getInstance() {
		p_dao=null;
		return p_dao; 
	}//getInstance
	
	public boolean masterLogin(PcbMasterLoginVO pml) {
		return false;
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
	
	public List<SetPrdListVO> prdList(){
		List<SetPrdListVO> spl=null;
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
