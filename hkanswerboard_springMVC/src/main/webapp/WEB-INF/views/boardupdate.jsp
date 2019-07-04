<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글수정하기</title>
</head>
<body>
<h1>게시글수정하기</h1>
<form action="updateboard.do" method="post">
<input type="hidden" name="seq" value="${dto.seq}"/>
<table border="1">
	<tr>
		<th>번호</th>
		<td>${dto.seq}</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>${dto.id}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" value="${dto.title}"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" name="content">${dto.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="submit">수정완료</button>
			<input type="reset" value="작성취소"/>
			<input type="button" value="목록" 
			onclick="location.href='boardlist.do'"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>







