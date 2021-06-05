<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>삭제</title>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
  </head>
  <body>
    <section class="wrap">
    <form action="delete_action">
    
     <label> 이름 입력:
        <input type="text" name="name" placeholder="이름" value="${name }" />
      </label>
      <br>
      <label> 성별 입력:
        <input type="text" name="sex" placeholder="성별" value="${sex }" />
      </label>
      <br>
      <label> 주소 입력:
        <input type="text" name="address" placeholder="주소" value="${address }" />
      </label>
      <br>
      <label> 소속 입력:
        <input type="text" name="part" placeholder="소속" value="${part }" />
      </label>
      <br>
   
      <input type="submit" value="입력완료" />
    </form>
    </section>

  </body>
</html>