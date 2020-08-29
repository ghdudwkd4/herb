package com.will.herb.board.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.herb.common.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDao;
		
	public int insertBoard(BoardVO vo){
		return boardDao.insertBoard(vo);
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo){
		return boardDao.selectAll(searchVo);
	}
	
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return boardDao.selectTotalRecord(searchVo);
	}
	
	public int updateReadCount(int no){
		return boardDao.updateReadCount(no);
	}
	
	public BoardVO selectByNo(int no){
		return boardDao.selectByNo(no);
	}
	
	public int updateBoard(BoardVO vo){
		return boardDao.updateBoard(vo);
	}
		
	public boolean checkPwd(int no, String pwd){
		String dbPwd=boardDao.selectPwd(no);
		
		boolean bool=false;
		if(dbPwd.equals(pwd)) {
			bool=true;
		}
		
		return bool;
	}
	
	public int deleteBoard(int no) {
		return boardDao.deleteBoard(no);
	}
	
	public List<BoardVO> selectMainNotice(){
		return boardDao.selectMainNotice();
	}
	
}










