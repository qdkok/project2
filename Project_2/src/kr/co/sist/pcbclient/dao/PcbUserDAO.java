package kr.co.sist.pcbclient.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.pcbclient.vo.PcbNoMemLoginVO;
import kr.co.sist.pcbclient.vo.PcbOrderVO;
import kr.co.sist.pcbclient.vo.PcbStatusVO;
import kr.co.sist.pcbclient.vo.PcbUserJoinVO;
import kr.co.sist.pcbclient.vo.PcbUserLoginVO;

public class PcbUserDAO {
	private static PcbUserDAO pu_dao;
	
	public PcbUserDAO() {
		
	}
	
	public Connection getConn() {
		Connection con = null;
		
		return con;
	}
	
	public static PcbUserDAO getInstance() {
			if(pu_dao == null) {
				pu_dao = new PcbUserDAO();
			}
			return pu_dao;
	}
	
	public boolean userIdChk() {
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
