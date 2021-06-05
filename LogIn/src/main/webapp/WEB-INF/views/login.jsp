<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>로그인</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
</head>
<body>
    <section class="wrap">
    <h1>SIGN UP</h1>
        <form action="login_action" method="post">
            <label> ID
            <br> 
                <input type="text" name="id" placeholder="id" />
            </label>
            <label> PWD  
            <br> 
                <input type="password" name="pwd" placeholder="pwd" />
            </label>
            <input type="submit" value="Sign In" />
        </form>
        
    </section>
</body>
</html>