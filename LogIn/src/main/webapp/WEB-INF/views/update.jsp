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
        <form action="update_action" method="post">
      <b> ID CANNOT BE CHANGED</b>
        <br>
        <br>
            <label>  
             <br> 
               ID = ${id }
            </label>
            <label> 현재 비밀번호:
             <br>              	
                <input type="password" name="pwd" placeholder="현재 비밀번호" />
            </label> 
              <label> 새 비밀번호: 
             <br> 
                <input type="password" name="new_pwd" placeholder="새 비밀번호"  />
            </label>
            <label> NAME 
             <br> 
                <input type="text" name="new_name" placeholder="이름" value="${original_name }"  />
            </label>
              <label> ADDRESS 
               <br> 
                <input type="text" name="new_address" placeholder="주소" value="${original_address }"/>
            </label>

            <input type="submit" value="수정하기" />
            <br>
            <a href="/login/list" style="margin-top: 50px;">뒤로</a>
            
        </form>
    </section>
</body>
</html>