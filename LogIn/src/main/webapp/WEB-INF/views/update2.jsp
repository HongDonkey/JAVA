<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta http-equiv='X-UA-Compatible' content='IE=edge'>
	<title>데이터 입력</title>
	<meta name='viewport' content='width=device-width, initial-scale=1'>
	<link rel='stylesheet' type='text/css' media='screen' href='${pageContext.request.contextPath}/resources/insert.css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).on('click', '#submit_button', function(event){
			var pwd1 = $('input[name="new_pwd"]').val();
			var pwd2 = $('input[name="new_pwd2"]').val();
			if (pwd1 != pwd2) {
				alert("패스워드가 다릅니다. 다시 입력해주세요.");
			} else {
				$('#update_form').submit();
			}
		});
	</script>
</head>
<body>
	<section class="wrap">
		<h1>UPDATE DATA</h1>
		<form action="update_action2" method="post" id="update_form">
			<b> ID CANNOT BE CHANGED</b>
		    <br /> <br /> 
		    <input type="hidden" name="idx" value="${idx }" /> 
			<label> 새 비밀번호: <br>
				<input type="password" name="new_pwd" placeholder="새 비밀번호" />
			</label>
			<label> 새 비밀번호 확인: <br>
				<input type="password" name="new_pwd2" placeholder="새 비밀번호 확인" />
			</label>
			<label> NAME <br> 
				<input type="text" name="new_name"	placeholder="이름" value="${original_name }" />
			</label> 
			<label> ADDRESS <br> 
				<input type="text"	name="new_address" placeholder="주소" value="${original_address }" />
			</label> 
			<input type="button" value="수정하기" id="submit_button" /> <br> 
			<a	href="/login/list" style="margin-top: 50px;">뒤로</a>
		</form>
	</section>
</body>
</html>