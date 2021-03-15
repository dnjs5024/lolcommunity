<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<c:if test="${!empty sessionScope.user}" >
${ sessionScope.user.userId }님 어서오세요
<button onclick="logOut()">로그아웃</button> <br>
</c:if>
<c:if test="${empty sessionScope.user}" >
<a href="/views/user/join"><button>회원가입</button></a>
<a href="/views/user/login"><button>로그인</button></a><br>
</c:if>

<a href="/views/community/freeBoard"><button>자유게시판</button></a>


<script type="text/javascript">
function logOut(){
	$.ajax({
		url : '/user/logOut',
		type : 'GET',
		success : function(res){
			if(res == 1){
				alert('로그아웃 했어용');
				location.href='/';
			}
		}
		
	})
}

</script>
</body>
</html>