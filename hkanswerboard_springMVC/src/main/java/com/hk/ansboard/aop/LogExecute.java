package com.hk.ansboard.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExecute {

	/*
	 * JoinPoint객체에 구현되어 있는 메서드
	 * getArgs():메서드의 아규먼트를 반환
	 * getTarget():대상 객체를 반환
	 * getSignature():호출되는 메서드의 대한 정보
	 * 		-->getName():메서드의 이름을 구함
	 *      -->toLongName():메서드의 풀네임(메서드의 리턴타입, 파리미터 타입 모두 표시)
	 *      -->toShortName():메서드를 축약해서 표현(메서드이름만)
	 * getThis(): 프록시 객체를 반환 
	 */
	
	//대상 메서드가 실행 되기 전에 bofore()메서드를 실행하겠다
	public void before(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("info:시작"+join.getSignature().getName());
	}
	
	public void afterReturning(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("info:끝");
	}
	
	public void daoError(JoinPoint join) {
		Logger log=LoggerFactory.getLogger(join.getTarget()+"");
		log.info("info:에러"+join.getArgs());
	}
}









