<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글상세보기</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
	#replyform{
 		display: none; 
	}
	#container{
		border:1px solid red;
		height: 380px;
		width: 800px;
 		overflow: auto; 
	}
</style>
</head>
<body>
<div id="container">
<h1>게시글상세보기</h1>
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
		<td>${dto.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onclick="updateForm('${dto.seq}')">수정</button>
			<button type="button" onclick="replyForm()">답글</button>
			<button type="button" onclick="delBoard('${dto.seq}')">삭제</button>
			<input type="button" value="목록" 
			onclick="location.href='boardlist.do'"/>
		</td>
	</tr>
</table>
<div id="replyform">
	<h1>답글달기</h1>
	<form action="replyboard.do" method="post">
	<input type="hidden" name="seq" value="${dto.seq}"/>
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
				<input type="submit" value="답글"/>
				<input type="button" value="목록" 
				  onclick="location.href='boardlist.do'"/>
			</td>
		</tr>
	</table>
</form>
</div>
</div>
<script type="text/javascript">
	//수정폼이동
	function updateForm(seq){
		location.href="updateform.do?seq="+seq;
	}
	//삭제하기
	function delBoard(seq){
		location.href="muldel.do?chk="+seq;
	}
	//답글폼 보여주기
	function replyForm(){
		$("#replyform").show();//show(),hide(),toggle(),slideDown()slideUp()
		var replyPosition=$("#replyform").offset().top;//div태그의 상단위치를 구함
		$("#container").animate({
			"scrollTop":replyPosition
		},1000);
		//animate({css속성정의},지연시간,움직임의 패턴)
		//json: {key:value,key:value,key:value,key:value} --> aaa["key"]
	}
</script>
</body>
</html>










