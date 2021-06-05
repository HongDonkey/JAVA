<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>입력</title>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
  </head>
  <body>
    <section class="wrap">
    <form action="update_action">
      <label> id 입력:
        <input type="text" name="id" placeholder="아이디" value="${id }"/>
      </label>
      <br>
      <label> pwd 입력:
        <input type="password" name="pwd" placeholder="비밀번호" value="${pwd }"/>
      </label>
      <br>
       <label> 이름 입력:
        <input type="text" name="name" placeholder="이름" value="${name }"/>
      </label>
      <br> 
      <label> 생일 입력:
        <input type="date" name="birthday" placeholder="생일" value="${birthday }"/>
      </label>
      <br>
      <label> 주소 입력:
        <input type="text" name="address" placeholder="주소" value="${address }"/>
      </label>
      <br>
      
   
      <input type="submit" value="입력완료" />
    </form>
    </section>

  </body>
</html>
</html>