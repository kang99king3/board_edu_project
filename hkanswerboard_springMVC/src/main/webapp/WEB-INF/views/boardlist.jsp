<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=utf-8"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글목록</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function allSel(bool){//bool은 전체체크박스의 체크여부 전달(true/false)
// 		alert(bool);
		//태그의 name속성의 이름으로 탐색: 반환값은 배열(list) [input,input,input...]
		var chks=document.getElementsByName("chk");
		for (var i = 0; i < chks.length; i++) {
			chks[i].checked=bool;
		}
	}
	function allSelJQ(bool){
		//엘리먼트의 속성을 정의하는 메서드: attr("속성명","값"),prop("속성명","값")
		$("input[name=chk]").prop("checked",bool);
	}
	
// 	$(document).ready(function(){})
// 	window.onload=function(){}
	$(function(){
// 		alert("페이지가 로딩되면 실행");
		//엘리먼트에서 클릭이벤트가 발생한다면 함수를 실행해라~~
		$("#attrview").click(function(){
			//refer,step,depth를 사라지게, 나타나게 구현
			$("table th").slice(5,8).toggle().end().slice(9).toggle();
			$("table tr").each(function(){
				$(this).children("td").slice(5,8).toggle().end().slice(9).toggle();
			});
		});
		
		//이벤트중에 onmouseover, onmouseout: mouseover(), mouseout()
		// ---->하나로 묶어서 구현한 hover()
		$(".preview").hover(function(){
			//서버쪽에 전달할 파라미터:해당 글의 seq--> data속성에서 정의
			// <a>--> <td>--> <td>--> <td>seq</td>
			var aTag=$(this);//내가 선택한 글제목 a태그
			var seq=$(this).parent().prev().prev().text();
// 			alert("전달할 값:"+seq);//confirm(), prompt()
			$.ajax({
				method:"post",
				url:"contentAjax.do", //요청URL
				data:{"seq":seq},    // 파라미터 전달할때 형식 boardlist.do?key=value
				dateType:"json",     //서버로부터 받을 데이터 타입 
				success:function(obj){  //서버에서 데이터를 받는데 성공했다면 실행해라->obj에서받음
					//obj--> json형태로 변환되어 받아진다.
					//obj={'dto':{'seq':seq,'id':id,'titile':title....}}
// 					var dto=obj["dto"];
// 					var content=dto["content"];
// 					alert(obj["dto"]["content"]);
					var content=obj["dto"]["content"];
					aTag.prevAll("div").show().text(content);
					
				},
				error:function(){
					alert("서버통신 실패");
				}
			});
		},function(){
			$(this).prevAll("div").hide().text("");
		});
		
	});
	
	
</script>
<style type="text/css">
/* 	.preview:hover { */
/* 		background: red; */
/* 	} */
	td {
		position: relative;
	}
	
	.content {
		position: absolute;
		top:-50px;
		left:100px;
		background: orange;
		width:50px;
		height:30px;
		border-radius:25px 25px 25px 0px; 
		display: none;
		font-size: 6px;
		text-align: center;
		padding-top: 20px;
		font-weight: bold;
	}
</style>
</head>
<body>
<jsp:useBean id="util" class="com.hk.utils.Util" />
<h1>답변형게시판 글목록</h1>
<button id="attrview">속성</button>
<form action="muldel.do" method="post">
<table border="1">
	<col width="50px">
	<col width="50px"><col width="50px"><col width="300px"><col width="100px">
	<col width="50px"><col width="50px"><col width="50px"><col width="50px">
	<col width="50px">
	<tr>               
		<th><input type="checkbox" name="all" onclick="allSelJQ(this.checked)"/></th>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th><th>refer</th>
		<th>step</th><th>depth</th><th>조회수</th><th>삭제여부</th>
	</tr>
	<c:choose>
		<c:when test="${empty list}">
			<tr><td colspan="10">---작성된 글이 없습니다.---</td></tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td><input type="checkbox" name="chk" value="${dto.seq}"/></td>
					<td>${dto.seq}</td>
					<td>${dto.id}</td>
					<c:choose>
						<c:when test="${dto.delflag=='Y'}">
							<td>---삭제된 글입니다.---</td>
						</c:when>
						<c:otherwise>
							<td>
							<div class="content"></div>
							<jsp:setProperty property="arrowNbsp" name="util" value="${dto.depth}"/>
							<jsp:getProperty property="arrowNbsp" name="util"/>
							<a class="preview" href="boarddetail.do?seq=${dto.seq}">${dto.title}</a>
							</td>						
						</c:otherwise>
					</c:choose>
					<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd" /> </td>
					<td>${dto.refer}</td>
					<td>${dto.step}</td>
					<td>${dto.depth}</td>
					<td>${dto.readcount}</td>
					<td>${dto.delflag}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="10">
			<input type="button" value="글추가" 
			 onclick="location.href='insertForm.do'"/>
			<input type="submit" value="글삭제"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>










