<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>


<script>
$.ajax({
	
	url:'/test',
	method:'GET',
	success:function(res){
		alert(res);
	}
});
$.ajax({
	
	url:'/test1',
	method:'GET',
	success:function(res){
		alert(res);
	}
});

</script>

</body>
</html>