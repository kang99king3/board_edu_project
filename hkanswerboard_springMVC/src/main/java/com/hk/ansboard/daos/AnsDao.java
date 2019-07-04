package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.ansboard.dtos.AnsDto;

@Repository
public class AnsDao implements IAnsDao{

	private String namespace="com.hk.ansboard.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<AnsDto> getAllList() {
		return sqlSession.selectList(namespace+"boardlist");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=sqlSession.insert(namespace+"insertboard", dto);
		return count>0?true:false;
	}

	@Override
	public AnsDto getBoard(int seq) {
		Map<String, Integer>map=new HashMap<String, Integer>();
		map.put("seq", seq);
		return sqlSession.selectOne(namespace+"boardlist", map);
	}

	@Override
	public boolean updateBoard(AnsDto dto) {
		int count=sqlSession.update(namespace+"updateboard", dto);
		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		Map<String, String[]>map=new HashMap<String, String[]>();
		map.put("seqs", seqs);
		int count=sqlSession.update(namespace+"muldel", map);
		return count>0?true:false;
	}

	//답글 추가시 step을 수정하는 쿼리 실행
	@Override
	public int replyUpdate(int seq) {
		return sqlSession.update(namespace+"replyupdate", seq);
	}
	//답글추가시 새로운 답글을 추가하는 쿼리 실행
	@Override
	public int replyInsert(AnsDto dto) {
		return sqlSession.insert(namespace+"replyinsert", dto);
	}

	@Override
	public boolean readCount(int seq) {
		int count=sqlSession.update(namespace+"readcount", seq);
		return count>0?true:false;
	}
  
		
}







