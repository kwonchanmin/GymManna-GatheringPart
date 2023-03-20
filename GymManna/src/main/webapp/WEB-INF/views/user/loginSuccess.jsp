<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function go_submit(f) {
	f.action = 'search/' +f.centerName.value
}

function goToNext() {
    const pathVar = document.getElementById('center-name-input').value;
    window.location.href = '/mygym/article/search/' + pathVar;
}

function goDetail() {
    const pathVar = document.getElementById('articleGnum').value;
    window.location.href = '/mygym/article/detail/' + pathVar;
}
</script> 
</head>
<body>
<h1> 센터별 게시글 카운트(@PathVariable)</h1>
센터이름: <input type="text" name="centerName" id="center-name-input">
<button onclick="goToNext()">요청</button> 

<h1>센터 게시글 카운트(@RequestParam)</h1>
<form action="/mygym/article/search" method="get">
    센터이름: <input type="text" name="centerName">
    <br><br>
    <input type="submit" value="요청"/>
</form> 

<h1>센터 게시글 리스트</h1>
<form action="/mygym/article/searchlist" method="get">
    센터이름: <input type="text" name="centerName">
    게시글 번호: <input type="text" name="articleGnum">
    <br><br>
    <input type="submit" value="요청"/>
</form> 

<h1>만나 상세페이지(@PathVariable)</h1>
    게시글 번호: <input type="text" name="articleGnum" id="articleGnum">
    <br><br>
    <button onclick="goDetail()">요청</button> 

<h1>만나 게시글 작성</h1>
<form action="/mygym/article/create" method="post">
    게시글 타이틀: <input type="text" name="title">
    <br><br>
    게시글 내용: <input type="text" name="content">
    <br><br>
    작성자 아이디: <input type="text" name="userId">
    <br><br>
    카테고리: <input type="text" name="category">
    <br><br>
    센터 이름: <input type="text" name="centerName">
    <br><br>
    <input type="submit" value="요청"/>
</form> 

</body>
</html>