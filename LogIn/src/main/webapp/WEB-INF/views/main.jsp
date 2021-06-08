<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Main</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/main.css'>
</head>
<body  bgcolor = #91A8d0   >
<br>
    <center>
    <h1>HELLO!</h1>
    <h2>WELCOME TO JAVA WORLD</h2>
  </center>

    <section class="wrap">
        <a href="login">SIGN IN(로그인)</a>
        <a href="create">CREATE TABLE(테이블 생성)</a>
        <a href="list">MEMBER LIST(회원목록)</a>
        <a href="insert">SIGN UP(신규회원 등록)</a>
        <a href="select">SEARCH MEMBER(검색)</a>
        <a href="update">UPDATE DATA(수정)</a>
        <a href="update2">YOUR ACCOUNT(수정)</a>
        <a href="logout">LOG OUT</a>
    </section>
 
</body>
</html>