<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>아이디 찾기</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
</head>
<body>
    <section class="wrap">
    <h1>find ID</h1>
        <form action="findID_action" method="get">
            <label> 이름
            <br> 
                <input type="text" name="name" placeholder="name" />
            </label>
            <label> 생년월일  
            <br> 
                <input type="text" name="birthday" placeholder="birthday" />
            </label>
            <input type="submit" value="FIND" />
            <br>
        <a href="/com" style="margin-top: 50px;">홈으로</a>
        </form>
        
    </section>
</body>
</html>