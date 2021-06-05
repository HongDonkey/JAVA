<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>데이터 입력</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
</head>
<body>
    <section class="wrap">
     <h1>UPDATE DATA</h1>
        <form action="update_action">
      <b> ID & BIRTHDAY CANNOT BE CHANGED</b>
        <br>
        <br>
            <label> ID 
             <br> 
                <input type="text" name="id" placeholder="아이디 변경 불가. 기존의 아이디를 입력하세요."/>
            </label>
            <label> PWD 
             <br> 
                <input type="password" name="pwd" placeholder="비밀번호"  />
            </label>
            <label> NAME 
             <br> 
                <input type="text" name="name" placeholder="이름"  />
            </label>
              <label> ADDRESS 
               <br> 
                <input type="text" name="address" placeholder="주소" />
            </label>

            <input type="submit" value="입력 완료" />
            <br>
            <a href="/login/list" style="margin-top: 50px;">뒤로</a>
            
        </form>
    </section>
</body>
</html>