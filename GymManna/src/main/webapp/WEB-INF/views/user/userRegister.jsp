<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 회원가입  -->
<h1>횐가입 하ㅅ요</h1>
	<form action="/mygym/user/register" method="post">
      <p>아이디: <input type="text" name="userId"></p>
        <p>비밀번호: <input type="password" name="userPassword"></p>
        <p>이름: <input type="text" name="userName"></p>
        <p>닉네임: <input type="text" name="userNickname"></p>
        <p>폰번호: <input type="text" name="userPhone"></p>
        <p><button type="submit">가입하기</button></p>
</form>

</body>
</html>