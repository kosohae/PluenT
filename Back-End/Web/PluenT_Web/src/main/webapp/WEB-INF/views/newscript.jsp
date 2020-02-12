<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
					"http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">


<head>
<meta charset="UTF-8">
<title>HTML Layouts</title>
<script type="text/javascript" src='http://code.jquery.com/jquery-1.10.1.min.js'></script>
<script type="text/javascript">
$(function(){
	$("#translation").click(function(){
		var t = $("#content").val();
		$("#script").text(t);
	});
});
</script>
</head>

<body>

	<header> <img id="logo" alt=""
		src="https://mblogthumb-phinf.pstatic.net/20160622_73/hhtthh82_1466581509899OrBaG_PNG/%B3%D7%C0%CC%B9%F6-%B7%CE%B0%ED-%B0%ED%C8%AD%C1%FA.png?type=w800"
		width="5%"> 
		<a href="mypage">마이페이지</a> 
		<a href="logout">로그아웃</a> </header>

	<nav > 		
		<ul  id="topMenu">
			<li><a class="menuLink" href="newscript">스크립트번역</a></li>
			<li><a class="menuLink" href="#">발표연습</a></li>
			<li><a class="menuLink" href="alllecture">강의보기</a></li>
			<li><a class="menuLink" href="scriptlist">나의 발표관리</a></li>
		</ul>
	</nav>

<form id="userScript" action="newscript" method="post">
		제목:<input type="text" name="title" value="${trans.title}" /> <br><br>
		<section>
			<!-- 인식된 번역본 받기 -->
	번역될 언어:
		<select name=ori_lang size=1>
        <option value=1>한국어</option>
        <option value=2>영어</option>
        <option value=3>일본어</option>
        <option value=4 selected>베트남어</option>
    </select><br>
		내용넣기:<textarea rows="20" cols="30" name="content" id="content" >${trans.content }</textarea>
		<br><br>번역할 언어:
		<section>
			<select name=target_lang size=1>
	        <option value=1>한국어</option>
	        <option value=2>영어</option>
	        <option value=3>일본어</option>
	        <option value=4 selected>베트남어</option>
    	</select>
<br>
		번역본:
		<textarea rows="20" cols="30" name="script" id="script" readonly="readonly">${trans.script }</textarea>
		<!-- 번역본 받아서 문단 나누기 -->

		<input type="button" id="copy"  value="복사"> &nbsp; 
		<!-- 인식된 오디오 소스 받기 --><br>
		<audio src=""  controls > </audio>  <br>
		
		발표 예상 시간 : <input type="text" id="pttime" name="pttime" value=5000 readonly/>(ms) <br> 
		<input type="button" id="translation"  value="번역"> &nbsp;&nbsp;
		
		<input type="submit" value="저장" />
	</form>
	</section><br>

	<footer>
	회사소개
	</footer>
</body>

</html>