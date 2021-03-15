<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&family=Jua&display=swap" rel="stylesheet">
<style type="text/css">
#pysSection{
height: 250px;

background-repeat: no-repeat;
background-size: 100%;
background-position: center;
background-image: url("/resources/freeBoardimg/ya.jpg");

}

.modal-body{
height: 250px;
overflow-y: auto;
}



</style>

</head>
<body>
<jsp:include page="/common/header.jsp"/>

<section style="padding-bottom: 800px" id ="pysSection">




<div class= container style="padding-bottom: 800px;height: 1060px; font-family: 'Hi Melody', cursive;
font-family: 'Jua', sans-serif; font-size: x-large; color: black;" align="center" >
	
	<div class="card" style="width: 602px;text-align: center;background-color: #000000d1;padding-top: 40px;margin-top: 50px;">
	
		
		<div style="margin-bottom: -;height: 36px; width: 320px; ">
			<div id ="chick_id" style="float: left;">
			</div> 
			
			 <div class="userIcon"style="width: 150px;  z-index:500; ">	
				<div style=" padding-left: 400px;"> 
					<img  src="${sessionScope.user.userIcon}" id="userIcon" style="width: 200px;padding-top: 30px; z-index:1000;">
				</div>
			 <div style="padding-left: 400px;">
					<button type="button" class="btn btn-outline-light" data-toggle="modal" data-target="#exampleModal" style="margin-right: -; width: 200px;" >
		 				아이콘 선택
					</button>
				</div>
		    </div>
			
		
		</div>
		
		<div style=" height: 40px; bottom: 1px; width: 180px;">
			<input type="text" class="form-control" id="userNick" name ="userNick" value="${sessionScope.user.userNick}" style="width: 180px;" placeholder="유저 닉네임"> <br>
		</div>	
		<div style="margin-bottom: 10px;height: 30px;width: 400px;">
			<div id="nick_check" style="float: left;"></div>
		</div>
			<div style=" height: 40px; bottom: 1px; width: 180px;">
		 	<input type="password" class="form-control" id="userPwd" name = "userPwd" onkeyup="pwdval()" style="width: 180px;" placeholder="비밀번호"><br>
		</div>
		<div style="margin-bottom: -; height: 36px; width: 320px;">
			<div  id ="pwd_value" style="float: left;">
			</div>
		</div>
		
		<div style=" height: 40px; bottom: 1px; width: 180px;" >
			<input type="password" class="form-control" id="chickPwd" name ="chickPwd" style="width: 180px;" placeholder="비밀번호 확인"><br>
		</div>
		<div style="margin-bottom: -; height: 36px; width: 320px;">
			<div id="pwd_check" style="float: left;"></div>
		</div>	
<button class="btn btn-outline-light" onclick="userUpdate()" style="margin-top: 50px;">정보수정</button>
<button class="btn btn-outline-light" onclick="userDelete()">회원탈퇴</button>
</div>

</div>	




<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" >
    <div class="modal-content" style="margin-left: -;width: 1003px;margin-right: 0px;right: 650px;margin-left: 400px;padding-left: 0px;height: 721px;top: 100px;">
      <div class="modal-header" style="background-color:black; ">
        <h5 class="modal-title" id="exampleModalLabel" style="color: #599ab5">ICON</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
        <div class="modal-body" style="text-align: center; background-color: black;">
      			<c:forEach items="${icon}" var="test">
      			<button onclick="icon('${test}')" onmouseover="imgColor(${fn:replace(test,'.jpg','')})" id ="${fn:replace(test,'.jpg','')}" onmouseout="imgOut(${fn:replace(test,'.jpg','')})" style="background-color: black;border: none; margin-bottom: 10px; padding: 6px 6px 6px 6px;"><img src="/resources/icon/${test}" style="width: 110px;"   data-dismiss="modal"  ></button>
      			</c:forEach>
      	</div>
  		 <div class="modal-footer" style="background-color:black;">
      
      </div>
    </div>
  </div>
</div>

</section>




<script type="text/javascript">
var userNum = ${sessionScope.user.userNum};
var userId = "${sessionScope.user.userId}";
var userNick = "${sessionScope.user.userNick}";
var pattern = /[~!@#$%^&()_+|<>?:{}]/; 

function imgOut(test){
	$('#'+test).css('background-color','black');
}

function imgColor(test){
	$('#'+test).css('background-color','#fbfafa91');
}


function icon(img){
	$('#userIcon').attr('src','/resources/icon/'+img);
	
}


function pwdval(){
	
	var pwd = $('#userPwd').val().replace(/ /gi,'');
	var userPwd = $('#userPwd').val().trim().length;
	userPwd <=4 ? $('#pwd_value').text('5자리 이상으로 작성').css('color','#d02121') : userPwd >=12 ? $('#pwd_value').text('12자리 이하로 작성').css('color','#d02121'):!pattern.test(pwd)? $('#pwd_value').text('특수문자가 하나 이상 들어가야함').css('color','#d02121') : $('#pwd_value').text('사용가능 비번').css('color','blue') ;
	if($('#userPwd').val().search(/\s/) != -1){
		$('#pwd_value').text('스페이스 사용 불가').css('color','#d02121');
		return;
	}
	
	
}

$('#chickPwd').blur(function(){
	var userPwd = $('#userPwd').val();
	var chickPwd = $('#chickPwd').val();
	if(userPwd != chickPwd){
		$('#pwd_check').css('color','#d02121');
		return $('#pwd_check').text('비밀번호가 일치하지않아요');
	}else{
		$('#pwd_check').text('비밀번호 일치');
		$('#pwd_check').css('color','blue');
		
	}
})




$('#userNick').blur(function(){
	var userNick = $('#userNick').val().trim(); 
	var param = {
			userNick : userNick
	}
	$.ajax({
		url :'/user/check_nick',
		type : 'GET',
		data : param,
		success : function(res){
			if(res == 1){
				$('#nick_check').text('있는 닉네임 입니다').css('color','#d02121');
				return $('#userNick').focus();
			}
			else if (userNick.length <4 || userNick.length >10){
				$('#nick_check').text(' 5글자 이상 9글자 이하로 해주세').css('color','#d02121');
			}else {
				$('#nick_check').text('사용가능한 닉네임 입니다').css('color','blue');
			}
		}
	})
})


function pwdval(){
	
	var pwd = $('#userPwd').val().replace(/ /gi,'');
	var userPwd = $('#userPwd').val().trim().length;
	userPwd <=4 ? $('#pwd_value').text('5자리 이상으로 작성').css('color','#d02121') : userPwd >=12 ? $('#pwd_value').text('12자리 이하로 작성').css('color','#d02121'):!pattern.test(pwd)? $('#pwd_value').text('특수문자가 하나 이상 들어가야함').css('color','#d02121') : $('#pwd_value').text('사용가능 비번').css('color','blue') ;
	if($('#userPwd').val().search(/\s/) != -1){
		$('#pwd_value').text('스페이스 사용 불가').css('color','#d02121');
		return;
	}
	
	
}

$('#chickPwd').blur(function(){
	var userPwd = $('#userPwd').val();
	var chickPwd = $('#chickPwd').val();
	if(userPwd != chickPwd){
		$('#pwd_check').css('color','#d02121');
		return $('#pwd_check').text('비밀번호가 일치하지않아요');
	}else{
		$('#pwd_check').text('비밀번호 일치');
		$('#pwd_check').css('color','blue');
		
	}
})

function userDelete(){
	var params = {
			userNum : userNum,
			userNick : userNick
	}
	$.ajax({
		url : '/user/delete',
		type :'POST',
		data : params,
		success:function(res){
			if(res == 1){
				alert('그동안 감사했습니다');
				location.href='/';
			}
		}
	})
}



function userUpdate(){
	
    var userIcon =  $('#userIcon').attr('src');
	var userPwdlength =$('#userPwd').val().trim().length;
	var chickPwd = $("#chickPwd").val();
	var userNick = $('#userNick').val().trim();
	if($('#userPwd').val().search(/\s/) != -1){
		alert('비밀번호는 공백없이 입력해주세요');
		return;
	}
	if(userPwdlength <= 4 || userPwdlength >= 12 || !pattern.test($('#userPwd').val()) || $('#userPwd').val() != chickPwd){
		alert('비밀번호 확인 요망');
		$('#userPwd').focus();
		return;
	}
	if(userNick.length < 4 || userNick.length >10 ){
		alert('닉네임은 5글자 이상 9글자 이하로 해주세요')
		$('#userNick').focus();
		return $('#nick_check').text('닉네임은 5글자 이상 9글자 이하로 해주세요').css('color','#d02121');
	}
	
	
	var data = {
			userNum : userNum,
			userNick : $('#userNick').val(),
			userPwd : $('#userPwd').val().replace(/ /gi, ''),
			userIcon : userIcon,
			userId :userId
	}
	$.ajax({
		url : '/user/update',
		type : 'POST',
		data : data,
		success:function(res){
			if(res === 1 ){
				alert('정보수정 완료');
				location.href='/';
			}else{
				alert('정보수정 중 에러');
			}
		}
	})
}



</script>

<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>