<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>목록</title>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/list.css'>
  </head>
  <body>
    <section class="wrap">
      <table>
        <thead>
          <tr>
            <th>번호</th><th>id</th><th>비밀번호</th><th>이름</th><th>생일</th><th>주소</th><th>생성일</th><th>수정일</th>
          </tr>
        </thead>
        <tbody>
          ${searchResult}
         
        </tbody>
      </table>
      <a href="/com" style="margin-top: 50px;">홈으로</a>
    </section>

  </body>
</html>