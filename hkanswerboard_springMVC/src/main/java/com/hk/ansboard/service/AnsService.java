package com.hk.ansboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.ansboard.daos.AnsDao;
import com.hk.ansboard.daos.IAnsDao;
import com.hk.ansboard.dtos.AnsDto;

@Service
public class AnsService implements IAnsService {

	@Autowired
	private IAnsDao ansDao;
	
	@Override
	public List<AnsDto> getAllList() {
		return ansDao.getAllList();
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		return ansDao.insertBoard(dto);
	}

	@Override
	public AnsDto getBoard(int seq) {
		return ansDao.getBoard(seq);
	}
	
	@Override
	public boolean updateBoard(AnsDto dto) {
		return ansDao.updateBoard(dto);
	}
	
	@Override
	public boolean mulDel(String[] seqs) {
		return ansDao.mulDel(seqs);
	}

	@Override
	public boolean replyBoard(AnsDto dto) {
		ansDao.replyUpdate(dto.getSeq()); // update문실행
		int count=ansDao.replyInsert(dto);// insert문실행
		return count>0?true:false;
	}

	@Override
	public boolean readCount(int seq) {
		return ansDao.readCount(seq);
	}

}






