<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>회원 가입</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
</head>
<body>
    <section class="wrap">
    <h1>SIGN UP</h1>
        <form action="insert_action" method="post">
            <label> ID
            <br> 
                <input type="text" name="id" placeholder="id" />
            </label>
            <label> PWD  
            <br> 
                <input type="password" name="pwd" placeholder="pwd" />
            </label>
            <label> NAME
            <br> 
                <input type="text" name="name" placeholder="name" />
            </label>
            <label> BIRTHDAY 
            <br>  
                <input type="date" name="birthday" placeholder="birthday" />
            </label>
            <label> ADDRESS 
            <br>  
                <input type="text" name="address" placeholder="Address" />
            </label>
            <input type="submit" value="Sign Up" />
            <br>
        <a href="/login" style="margin-top: 50px;">홈으로</a>
        </form>
        
    </section>
</body>
</html>