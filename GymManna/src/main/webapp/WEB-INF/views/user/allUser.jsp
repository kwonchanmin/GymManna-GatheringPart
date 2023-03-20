<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체 회원 목록</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>비밀번호</th>
                <th>이름</th>
                <th>폰번호</th>
                <th>닉네임</th>

            </tr>
        </thead>
        <tbody>
           <c:forEach var="user" items="${user}">
                <tr>
                    <td>${user.userId}</td>>
                    <td>${user.userPassword}</td>
                    <td>${user.userName}</td>
                    <td>${user.userPhone}</td>
                    <td>${user.userNickname}</td>
                </tr>
           </c:forEach>
           
        </tbody>
    </table>
    
    
</body>
</html>