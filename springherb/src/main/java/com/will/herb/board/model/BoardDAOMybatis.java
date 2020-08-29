package com.will.herb.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.will.herb.common.SearchVO;

@Repository
public class BoardDAOMybatis implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
		
	private String namespace="config.mybatis.mapper.oracle.board.";
	
	public int insertBoard(BoardVO vo){
		int cnt=sqlSession.insert(namespace+"insertBoard", vo);
		return cnt;
	}
	
	
	public List<BoardVO> selectAll(SearchVO searchVo){
//		String sql="select * from board";
//		if(keyword!=null && !keyword.isEmpty()) { //검색의 경우
//			sql+=" where "+ condition +" like '%' || ? || '%'";
//		}		
//		sql+=" order by no desc";
		
		return sqlSession.selectList(namespace+"selectAll", searchVo);
	}


	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return sqlSession.selectOne(namespace+"selectTotalRecord", 
			searchVo);
	}
	
	public int updateReadCount(int no){
		return sqlSession.update(namespace+"updateReadCount", no);		
	}
	
	public BoardVO selectByNo(int no){
		return sqlSession.selectOne(namespace+"selectByNo",no);
	}
	
	public String selectPwd(int no){
		return sqlSession.selectOne(namespace+"selectPwd",no);
	}
		
	public int updateBoard(BoardVO vo) {
		return sqlSession.update(namespace+"updateBoard", vo);		
	}
	
	public int deleteBoard(int no){
		return sqlSession.delete(namespace+"deleteBoard", no);		
	}
	
	public List<BoardVO> selectMainNotice(){
		return sqlSession.selectList(namespace+"selectMainNotice");
	}
	
}





