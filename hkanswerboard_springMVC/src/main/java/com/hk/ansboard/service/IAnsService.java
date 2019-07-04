package com.hk.ansboard.service;

import java.util.List;

import com.hk.ansboard.dtos.AnsDto;

public interface IAnsService {
	public List<AnsDto> getAllList();
	public boolean insertBoard(AnsDto dto) ;
	public AnsDto getBoard(int seq) ;
	public boolean updateBoard(AnsDto dto) ;
	public boolean mulDel(String[] seqs) ;
	
	//답글달기 기능: update,insert에 대한 메서드 두개 선언
	public boolean replyBoard(AnsDto dto);
	
	public boolean readCount(int seq) ;
}
