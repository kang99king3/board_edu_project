<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글작성하기</title>
</head>
<body>
<h1>게시글작성하기</h1>
<form action="insertboard.do" method="post">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="id" required="required"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required="required"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="content" required="required"></textarea> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="글등록"/>
				<input type="button" value="목록" 
				  onclick="location.href='boardlist.do'"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>











