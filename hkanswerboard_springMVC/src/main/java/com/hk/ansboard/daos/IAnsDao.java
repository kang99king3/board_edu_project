package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.ansboard.dtos.AnsDto;

public interface IAnsDao {
		
		public List<AnsDto> getAllList();
		public boolean insertBoard(AnsDto dto) ;
		public AnsDto getBoard(int seq) ;
		public boolean updateBoard(AnsDto dto) ;
		public boolean mulDel(String[] seqs) ;
		
		//답글달기 기능: update,insert에 대한 메서드 두개 선언
		public int replyUpdate(int seq);
		public int replyInsert(AnsDto dto);
		
		public boolean readCount(int seq) ;
}








