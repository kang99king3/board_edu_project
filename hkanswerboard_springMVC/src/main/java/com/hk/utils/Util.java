package com.hk.utils;

public class Util {

	//<jsp:usebean> : dto를 불러올때 사용, set/get메서드 형태로 작성
	//<jsp:setProperty>, <jsp:getProperty>
	
	private String arrowNbsp;//depth의 크기만큼 공백만든거 저장할 필드

	public String getArrowNbsp() {
		return arrowNbsp;
	}

	public void setArrowNbsp(String depth) {
		String nbsp="";
		int depthInt=Integer.parseInt(depth);//String--> int변환
		for (int i = 0; i < depthInt; i++) {
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		this.arrowNbsp=depthInt>0?nbsp+"<img src='img/arrow01.png' alt='답글'/>":"";
	}
	
	
}









