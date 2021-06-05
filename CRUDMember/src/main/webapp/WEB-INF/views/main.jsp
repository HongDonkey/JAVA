<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- html버전 명시 -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Main</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/main.css'>
    <!-- 'href='로 css파일 연결함 -->
  </head>
  <body bgcolor = #91A8d0 >
  <br>
    <!-- # 실제 화면에 표시되는 내용 -->
    <section class="wrap">
      <!-- 'wrap'이라는 이름으로 영역을 지정 -->
       <a href="logon">로그인</a>
       <a href="create">테이블 생성</a>
      <a href="list">회원 명부 확인</a>
      <a href="insert">회원 정보 입력</a>
      <a href="search">회원 검색</a>
      
       

    </section>


  </body>
</html>