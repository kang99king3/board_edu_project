<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류처리</title>
</head>
<body>
<h1>시스템 오류입니다.(오류메시지:${msg})</h1>
<h2>관리자에게 문의하세요(02-1234-5678)</h2>
<a href="index.jsp">메인으로 이동</a>
</body>
</html>