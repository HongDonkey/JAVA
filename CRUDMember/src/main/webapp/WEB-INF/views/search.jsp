<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>검색</title>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
  </head>
  <body>
    <section class="wrap">
    <form action="search_action">
      <label> 이름 입력:
        <input type="text" name="name" placeholder="이름" />
      </label>
      <br>
     
   
      <input type="submit" value="검색" />
    </form>
    </section>

  </body>
</html>