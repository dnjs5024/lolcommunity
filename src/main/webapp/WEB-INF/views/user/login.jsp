<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<section>
	<div class=container style="margin-top: 200px" align="center"> 
		<div class="card" style="width: 18rem;">
				
				아이디<br>
				<input type="text" class="form-control" id="userId"><br>
				
				비밀번호<br>
				<input type="password" class="form-control" id ="userPwd"><br>
				<div>
				<button class="btn btn-outline-success"  onclick="login()">로그인</button>
				<a href="/views/user/join"><button class="btn btn-outline-success">회원가입</button></a>
				</div>
				<div id = "check_login"></div>
			
		</div>
	</div>

	
</section>	














<script type="text/javascript">
function login(){
	var params={
			userId : $('#userId').val().trim(),
			userPwd : $('#userPwd').val().trim()
	}
	
	$.ajax({
		url : '/user/login',
		type : 'POST',
		data :params,
		success : function(res){
			if(res === 1){
				location.href='/';
			}else if (res === 0){
				$('#check_login').text('없는 아이디거나 비밀번호가 틀렸습니다').css('color','red');
			}
		}
	})
}
</script>	
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>