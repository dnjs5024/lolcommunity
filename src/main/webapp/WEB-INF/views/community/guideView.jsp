<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<jsp:include page="/common/head.jsp"/>
<link rel="stylesheet" type="text/css" href="https://nstatic.dcinside.com/dc/w/css/common.css?v=2004211415"/>
	<link rel="stylesheet" type="text/css" href="https://nstatic.dcinside.com/dc/w/css/contents.css?2007011213"/>
	<link rel="stylesheet" type="text/css" href="https://nstatic.dcinside.com/dc/w/css/popup.css?ver=200327"/>
	<link rel="stylesheet" type="text/css" href="//nstatic.dcinside.com/dc/w/css/editor_20141223.css?v=2"/>
	<link href="https://fonts.googleapis.com/css2?family=Hi+Melody&family=Jua&display=swap" rel="stylesheet">
<style>
.visit_history {
   position: relative;
	margin-bottom:10px;
   min-width: 65%;
   height: 36px;
   right:15px;
   line-height: 36px;
   padding: 0 12px;
   background: #f3f3f3;
   border: 1px solid #000000;
   border-width: 3px 1px 1px 1px;
}

.writerinfo{
	min-width: 65%;
	height: 80px;
	position:relative;
    border: 1px solid #000000;
    border-width: 0px 0px 1px 0px;
}

</style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<div id="quick_menu" style="position: absolute; display:none;">	
<div class="left_ad" style="position: relative;">	
<script type="text/javascript">
(function(cl, i, c, k, m, o, n) {
	m = cl.location.protocol + c;
	o = cl.referrer;
	m += '&mon_rf=' + encodeURIComponent(o);
	n = '<'+i+' type="text/javascript" src="'+m+'"></'+i+'>';
	cl.writeln(n);})
		(document,'script','//tab2.clickmon.co.kr/pop/wp_ad_160_js.php?PopAd=CM_M_1003067%7C%5E%7CCM_A_1043805%7C%5E%7CAdver_M_1003115&rt_ad_id_code=RTA_105838');
</script>
</div>
</div>
<script type="text/javascript">
(function($){
 var quick_menu = $('#quick_menu'); // 퀵매뉴의 id명
 var quick_top = 120; // 위에서부터 떨어져야 되는 거리
 var quick_right = 570; // 중앙에서 떨어져야 되는 거리
 var quick_speed = 1000; // 이동속도
 $(document).ready(function(){
     quick_menu.css('top', quick_top + "px");
     quick_menu.css('right', (document.body.clientWidth / 2) + quick_right + "px" );
     quick_menu.css('display', '');
 
     $(window).resize(function(){
         quick_menu.css('right', (document.body.clientWidth / 2) + quick_right + "px" );
     });
 
     $(window).scroll(function(){
         quick_menu.stop();
         quick_menu.animate( { "top": $(document).scrollTop() + quick_top + "px" }, quick_speed );
     });
 });
})(jQuery); 
 </script>


<section id ="sc">
<div class="container" style="width: 100%">
<div class="game-info">
	<div class="game-info_background">
		<div class="game-content">
			<img src="https://talk.op.gg/images/game/icon-community-lol.png"
				alt="" class="game-info__icon">
			<h1 class="my-4">공략게시판</h1>
		</div>
	</div>
</div>
</div>
<div class= "container" style="min-width:60%; border: 1px solid #f3f3f3;
	border-width: 2px 2px 2px 2px; background-color: white;">


<div class="visit_history"  ></div>

<header>
	  <div class="writerinfo">
		    <h3 class="title ub-word">
			    <span id="guideTitle"></span> 
	        </h3>	
				<div align="right" id="divModify" style="visibility: hidden; text-align: right; position: relative; bottom: 40px; right:30px; margin-top:  ">
			<a href="/views/community/guideUpdate?guideNum=${param.guideNum}"><button class="btn btn-outline-info">수정</button></a>
			<button onclick="guideBoardDelete(${sessionScope.user.userNum})" class="btn btn-outline-danger">삭제</button>
		</div>	
			<div class="fl" style="position:relative;bottom:40px; ">
				<span id='userNick'><em></em></span>
				<span id="ip">(223.33)</span>
				<span id="guideJoin" style="margin-right: 5px;"></span>					
				<span>조회</span>	<span id="guideCnt" style="margin-right: 2px;"></span>
				<span>추천</span><span id="likeCnt" style="margin-right: 2px;"></span> 
				<span>댓글</span><span id="tagCnt"></span>					
			</div>
	</div>	
</header>
<div class="view_contents">
	<div class="inner clear">
		<div class="writing_view_box" style=" min-width: 100%;" >
			<pre></pre>
			<div style="overflow: hidden; min-width: 100%; min-height: 400px; vertical-align: top;">		
				<img id="imgId" style="max-width: 65%; max-height: 1200px; "><input type="hidden" id="filePath">
				<div id="guideContent" style="float:left;"></div>
			</div>
		</div>
	</div>
</div>					


<div style="text-align: justify;">
<div > 
	<c:choose>
<c:when test="${sessionScope.user != null }">
	<div style="float: left;">
	<div>
		<button onclick="likeTo(${sessionScope.user.userNum},this)" id="likeBtn">♥</button>
	</div>
		<div id ="likeNum"></div>
	</div>
	
</c:when>
<c:when test="${sessionScope.user == null }">
	<div style="float: left;">
		<div>
			<button onclick="likeTo(0)" >♥</button>
		</div>
		<div id ="likeNum"></div>
	</div>
</c:when>
</c:choose>
	   
	</div>
</div>




	<div class="card" style=" min-width: 100%; position: relative;" >
<div class="card-header with-border">
	<h4 class="card-title" style="font-size:15px ;">댓글이 <span id="tagCnt2"></span>개 달렸습니다</h4>
</div>
<div class="card-body">
	<div class="row" style="position:relative;  border: 1px solid #f3f3f3;
	border-width: 0px 2px px 2px;">
		<div class="form-group col-sm-8">
		<input class="form-control input-sm" id="tagContent" type="text"placeholder="댓글 입력..."><br>
		<div id="tagInputText"></div>
		</div>	
		<div class="form-group col-sm-2">
		<c:choose>
	  <c:when test="${sessionScope.user !=null }">
		<button type="button" onclick="insertTag()"
			class="btn btn-outline-success">
			<i class="fa fa-save"></i> 댓글작성
		</button>
	  </c:when>	
	  
	  <c:when test="${sessionScope.user ==null }">
	  	<div>
	  	비밀번호를 입력해주세요 <input type="password" id="ghostPwd" ><br>
	  	
	  	<button type="button" onclick="insertGhostTag()"
			class="btn btn-outline-success">
			<i class="fa fa-save"></i> 댓글작성
		</button>
	    <div id="ghostPwdRed"></div>
	    </div>
	  </c:when>
	</c:choose>
		</div>
		<div class="card-footer" id="card-footer" style="min-width: 100%;">
			<ul id="replies"></ul>
			<div class="btn_wrap" align="center" >
				<a href="javascript:commonViewMore();" style="font-size: xx-large;"  class="btn_more" >더보기</a>
			</div>
		</div>	
	</div>
</div>
</div>	

		<div class="card-footer">
			<nav aria-label="Contacts Page Navigation">
				<ul
					class="pagination pagination-sm no-margin justify-content-center m-0">
				</ul>
			</nav>
		</div>

</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">수정 / 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      			<input type="hidden" id="modalTagNum">
        	작성자 <br><input type="text" id="modalUserNick" readonly="readonly" style="border: none;" ><br>
    	내용 <br><textarea id="modalTagContent" cols="50" rows="10" style="resize:none;"></textarea><br>
    	작성일 <input type="text" id="modaltagJoin" readonly="readonly" style="border: none;"><br>
  </div>
  <div class="modal-footer">

  <c:choose>
  	<c:when test="${sessionScope.user.userNick !=null}">
  		<button  class="btn btn-secondary"  onclick="updateTag()">수정하기</button>
    	<button  class="btn btn-primary" onclick="deleteTag()" >삭제하기</button>
  	</c:when>
  	<c:when test="${sessionScope.user.userNick ==null}">
  		<input type="password" placeholder="비밀번호 적어주세용" id="updatePwd"><br>
  		<button  class="btn btn-secondary"  onclick="updateTag()">수정하기</button>
    	<button  class="btn btn-primary" onclick="deleteTag()" >삭제하기</button>
  	</c:when>
  </c:choose>     
      </div>
    </div>
  </div>
</div>
<div class="container" >
<div class="ad">
<a href="/views/community/guideBoard"><button class="btn_blue concept" style="margin-top:20px; "><span>전체글</span></button></a>
</div>
<div class='board_ad' align="center" style="position:relative; top: 40px; ">	
<script type="text/javascript">
(function(cl,i,c,k,m,o,n){m=cl.location.protocol+c;o=cl.referrer;m+='&mon_rf='+encodeURIComponent(o);
n='<'+i+' type="text/javascript" src="'+m+'"></'+i+'>';cl.writeln(n);
})(document,'script','//tab2.clickmon.co.kr/pop/wp_ad_728_js.php?PopAd=CM_M_1003067%7C%5E%7CCM_A_1043805%7C%5E%7CAdver_M_1003115&rt_ad_id_code=RTA_105837');
</script>
</div>
</div>
<div class="container" id="all-views">
<div class="container">
	<div class="row">
		<!-- /.col-lg-3 -->
		<div class="col-lg-9" >
			<table class="table table-hover" style="background-color: white; border:1px solid #f3f3f3; border-width: 0px 2px 2px 2px;">
				<thead>
					<tr>
						<th>번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>사진</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
<!-- <div class="right_ban" align="right"> -->
<!-- <div id="right_nav" style="position:absolute; "> -->
<!-- <script type="text/javascript"> -->
<!--  (function(cl, i, c, k, m, o, n) { -->
<!--  	m = cl.location.protocol + c; -->
<!--  	o = cl.referrer; -->
<!--  	m += '&mon_rf=' + encodeURIComponent(o); -->
<!--  	n = '<'+i+' type="text/javascript" src="'+m+'"></'+i+'>'; -->
<!--  	cl.writeln(n);}) -->
<!--  (document,'script','//tab2.clickmon.co.kr/pop/wp_ad_160_js.php?PopAd=CM_M_1003067%7C%5E%7CCM_A_1043803%7C%5E%7CAdver_M_1003115&rt_ad_id_code=RTA_105845'); -->
<!--            </script> -->
<!-- 			</div> -->
<!-- </div> -->
<div id="viewsrigthmenu">
<div class="viewsrdiv">
		<div style="text-align: right;">
	<div class="card" style="text-align: center; font-size:large ; border: 2px solid #000000; border-width: 2px 0px 2px 0px;">
				<c:choose>
					<c:when test="${sessionScope.user == null }">아이디<br>
							<input type="text" class="form-control" id="userId">
							<br>비밀번호<br>
							<input type="password" class="form-control" id="userPwd">
							<br>
							<div>
								<button class="btn btn-outline-success" onclick="login()">로그인</button>
							<a href="/views/user/join"><button
									class="btn btn-outline-success">회원가입</button></a>
						</div>
						<div id="check_login"></div>
					</c:when>
					<c:when test="${sessionScope.user != null }">	
						<div style="text-align: left;">
							<div style="float: left;">
								<div  style="min-width:240px; margin-top: 10px;">
									<img src="${sessionScope.user.userIcon}"
										style="height: 100px; border-radius: 70%;">									
									<div style="width: 130px; float: right; position: relative; margin-top: 15px; ">
										<h2 style="font-size:20px; font-weight: bolder;">${sessionScope.user.userNick}</h2>
									    <div id="userLev"></div>
								    </div>
								   	<div>					   
										<div id="userExt" style="margin-top:5px "></div>	
								    </div>								 	
								</div>
						    </div>
						</div>
						<div  style="margin-top: 10px;">		
							<a href="/views/user/lookUp"><button class="btn btn-outline-success">내가 쓴 글</button></a>
							<a href="/views/user/lookUp"><button class="btn btn-outline-success">내가 쓴 댓글</button></a>
							<div style="margin-top: 10px;">
								<button class="btn btn-outline-success" style=" border-color: #46cfa7; background-color: #16ae81; color: #fff; margin-bottom: 10px; " onclick="logOut()">로그아웃</button>
							</div>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="rightbanner">
		<ins class="kakao_ad_area" style="display:none;" 
			 data-ad-unit    = "DAN-rhr57o3r03e9" 
			 data-ad-width   = "300" 
			 data-ad-height  = "250"></ins> 
		<script type="text/javascript" src="//t1.daumcdn.net/kas/static/ba.min.js" async></script>	
	</div>
		<div class="container">
		<article class="viewsnewsarticle">
			<div class="news">
			<header style="min-height:30px; border: 2px dashed #000000; border-width: 0px 0px 2px 0px;">
			<h3 class="newsHeaderText">오늘의 롤소식</h3> <div id="nextNews"></div></header>
				<div class="newsBody">
					<div id="newsimg"></div>
					<div class="newsTextBox">
						<div id="title"></div>
						<div id="content"></div>
					</div>
				</div>
			</div>
		</article>
	</div>
</div>
	</div>
</div>
<div class="container">
<div class="guideview-bottom" style="background-color: white;" style="margin:0px; padding:0px;">
	<div style="position: relative; top: 45px;">
		<div class="bottom-2">
			<div class="search row" id="search_row">
				<div class="col-xs-2 col-sm-2">
					<select id="type" class="form-control">
						<option value="T">제목</option>
						<option value="C">내용</option>
						<option value="W">작성자</option>
						<option value="TC">제목+내용</option>
					</select>
				</div>
				  
			
			 <div  class="bottom_search fl clear" id="zz">
				 <div class="inner_search">
			 		  <input class="in_keyword" type="text" id="keyword" name="search_keyword" title="검색어 입력" value="">	 	 	
			 	 </div>  
			 	 <button class="sp_img bnt_search" onClick="doSearch()"><span class="blind">검색</span></button>
		    </div>
		
			</div>
			<nav aria-label="Page navigation example">
				<ul class="pagination" id="page">
				</ul>
			</nav>
	   </div>
	</div>
	</div>
</div>	
</div>

<script>
var guideNum = ${param.guideNum};
var param={
		guideNum:guideNum,
		userNum:${(sessionScope.user eq null)?0:sessionScope.user.userNum}
}
window.addEventListener('load',getNews);
window.addEventListener('load',getNewsimg);
window.addEventListener('load',selectOne);
window.addEventListener('load',likeCheck);
window.addEventListener('load',selectTag);
window.addEventListener('load',selectUserOne);
var userNum = ${(sessionScope.user eq null)?0:sessionScope.user.userNum};
function selectUserOne(){
	$.ajax({
		url : '/user/selectUserOne',
		data : {userNum : userNum},
		type :'POST',
		success : function(res){
			$('#userLev').html('<h2 style="font-size:15px; color:#16ae81;">레벨 '+res.userLevel+'</h2>');
			$('#userExt').html('<div class="progress"><div class="progress-bar progress-bar-striped" role="progressbar" style="width: '+res.userExperience+'%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div></div>')    
			$('#userExt').html('<div class="progress"><div class="progress-bar progress-bar-striped bg-danger progress-bar-animated" role="progressbar" style="width: '+res.userExperience+'%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div></div>')    
			if(res.userExperience < 80){
				$('#userExt').html('<div class="progress"><div class="progress-bar progress-bar-striped bg-warning progress-bar-animated" role="progressbar" style="width: '+res.userExperience+'%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div></div>') 
			}if(res.userExperience  < 40){
				$('#userExt').html('<div class="progress"><div class="progress-bar progress-bar-striped  progress-bar-animated" role="progressbar" style="width: '+res.userExperience+'%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div></div>') 
			}
		}
	})
}

function commonViewMore(){
	  if(($('#replies li').length % 10) !=0 ){
		  return;
	  };
	
	  
	  $.ajax({
			url : '/community/selectTagGuideList',
			data : {
				guideNum:guideNum,
				currentPageNo :$('#replies li').length
			},
			type : 'GET',
			success : function(res){
				var tagList = '';
				for(var idx in res){
					tagList += '<li class="replyLi">';
					tagList += "<p>" + res[idx].userNick+" ["+res[idx].tagJoin+"]</p>";
					tagList += "<p>" + res[idx].tagContent + "</p>";
					tagList += "<button  onclick='selectTagOne("+res[idx].guideTagNum+")'class='btn btn-outline-success' data-toggle='modal' data-target='#exampleModal' type='button'>수정 / 삭제</button>";
					tagList += '</li>';
					tagList += '<hr/>';
						
				}
				 
				$('#replies').append(tagList);
				
				
			}
		});
}



	
  function selectTag(){
	  $.ajax({
		url : '/community/selectTagGuideList',
		data : param,
		type : 'GET',
		success : function(res){
			var tagList = '';
			for(var idx in res){
				tagList += '<li class="replyLi">';
				tagList += "<p>" + res[idx].userNick+" ["+res[idx].tagJoin+"]</p>";
				tagList += "<p>" + res[idx].tagContent + "</p>";
				tagList += "<button  onclick='selectTagOne("+res[idx].guideTagNum+")'class='btn btn-outline-success' data-toggle='modal' data-target='#exampleModal' type='button'>수정 / 삭제</button>";
				tagList += "</li>";
				tagList += '<hr/>';
				
			}
			$('#replies').html(tagList);
			if($('#replies li').length <10){
				$('.btn_more').remove();
			}
			
		}
	});
  }
  
  function insertGhostTag(){
	  var ghostPwd = $('#ghostPwd').val().trim();
	  var tagContent = $('#tagContent').val().trim();
	  if(ghostPwd.length <3 ){
		  $('#ghostPwdRed').html('비밀번호는3 자리이상 입력해주세요').css('color','red');
		  $('#ghostPwd').focus();
		  return;
	  }
	  if(tagContent.length <3){
		 $('#tagInputText').html('댓글을 3자리이상 입렵해주세용').css('color','red');
		 $('#tagContent').focus();
		  return;
	  }
	  
	  var params = {
			  ghostPwd : ghostPwd,
			  tagContent : tagContent,
			  guideNum : guideNum
	  }
	  
	  $.ajax({
		  url : '/community/insertGuideTag',
		  type : 'POST',
		  data : params,
		  success : function(res){
			  if(res ==1){
					location.href='/views/community/guideView?guideNum='+guideNum;
				}
		  }
	  })
	  
	  
  }
	
  
  function deleteTag(){
	  var ghostPwd = $('#updatePwd').val();
	  var tagNum = $('#modalTagNum').val();
	  var userNick = $('#modalUserNick').val();
	  var params = {
			  guideNum : guideNum ,
			  guideTagNum : tagNum,
			  userNick:userNick,
			  ghostPwd : ghostPwd
	  }
	 
	  $.ajax({
		  url : '/community/deleteGuideTag',
		  type : 'POST',
		  data : params,
		  success : function(res){
			  if(res == 1){
				  alert('삭제성공');
				  location.href='/views/community/guideView?guideNum='+guideNum;
			  }
			  if(res == 2){
				  alert('다른 아이디로는 삭제가 불가합니다');
				  location.href='/views/community/guideView?guideNum='+guideNum;
			  }
			  if(res == 0){
				  alert('비밀번호가 틀렸습니다');
			  }
			  
		  }
	  })
	  
  }
  
  function selectTagOne(cnt){
	  
	  $.ajax({
		  url : '/community/selectGuideTagOne',
		  type : 'POST',
		  data :{guideTagNum :cnt},
		  success : function(res){
			  $('.modal-body > #modalUserNick').val(res.userNick);
			  $('.modal-body > #modalTagContent').val(res.tagContent);
			  $('.modal-body > #modaltagJoin').val(res.tagJoin);
			  $('.modal-body > #modalTagNum').val(res.guideTagNum);
		  }
	  })
  }

   
  
  function updateTag(){
	var ghostPwd = $('#updatePwd').val();
	var tagNum = $('#modalTagNum').val();
	var tagContent = $('#modalTagContent').val();
	var userNick = $('#modalUserNick').val();
	var params = {
			ghostPwd : ghostPwd,
			guideTagNum : tagNum,
			userNick : userNick,
			tagContent : tagContent
	}
	$.ajax({
		url : '/community/updateGuideTag',
		type : 'POST',
		data : params,
		success : function(res){
			if(res == 1){
				alert('수정 완료');
				location.href='/views/community/guideView?guideNum='+guideNum;
			}
			if(res == 2){
				alert('본인 계정만 수정할수있습니다');
				location.href='/views/community/guideView?guideNum='+guideNum;
			}
			if(res == 0){
				alert('비밀번호가 틀렸습니다');
			}
		} 
	})
  }

 
 
  function selectOne(){
	$.ajax({
		url : '/community/selectOne1',
		type : 'POST',
		data : param,
		success : function(res){
			document.getElementById('guideTitle').innerHTML=res.guideTitle;
			document.getElementById('userNick').innerHTML=res.userNick;
			document.getElementById('guideJoin').innerHTML=res.guideJoin;
			document.getElementById('guideContent').innerHTML=res.guideContent;	
			document.getElementById('guideCnt').innerHTML=res.guideCnt;
			document.getElementById('tagCnt').innerHTML=res.tagCnt;
			document.getElementById('tagCnt2').innerHTML=res.tagCnt;
			if(res.userNum == '${sessionScope.user.userNum}'){
				$('#divModify').css('visibility','visible');
			};
			if(res.guideFilepath !=null){
				$('#imgId').attr('src','/resources/guideBoardimg/'+res.guideFilepath);
				$('#filePath').val(res.guideFilepath);
			}	
			if(res.like){
				$('#likeBtn').css({'color':'red'});
			}
		}
	})
  }
  
  function likeCheck(){
	  
	  $.ajax({
		  url : '/communtiy/selectLike1',
		  data : {guideNum :guideNum},
		  success : function(res){
		  	  $('#likeNum').html(res).css('color','green');
		  	  $('#likeCnt').html(res);
		  	 
		  	 
		  }
	  })
  }
  

function insertTag(){
	if($('#tagContent').val().trim().length <1){
		alert('1글자 이상은 작성해주세용');
		return;
	}
	
	var tagContent = $('#tagContent').val();
	var params ={
			tagContent :tagContent,
			guideNum : guideNum
	}
	$.ajax({
		url : '/community/insertGuideTag',
		type : 'POST',
		data : params,
		success:function(res){
			if(res ==1){
				location.href='/views/community/guideView?guideNum='+guideNum;
			}
		}
	})
}  
  
  
function likeTo(cnt,btn){
	
	
	var userNum = cnt;
	if(!cnt){
		alert('로그인 이후에만 가능합니다');
		return;
	}
	var param = {
			userNum:userNum,
			guideNum:guideNum
	}
	$.ajax({
		data: param,
		url :'/community/likeTo1',
		type : 'POST',
		success : function(res){
		
			if(res == 1 || res == -1){
				if(res==1){
					btn.style.color='red';
				}else{
					btn.style.color='gray';
				}
				likeCheck();
			}
		}
	})
}

function guideBoardDelete(cnt){
	var guideFilepath = $('#filePath').val();
	var userNum = cnt;
	var param = {
			guideNum:guideNum,
			guideFilepath:guideFilepath,
			userNum :userNum	
	}
	$.ajax({
		url : '/community/delete1',
		type : 'POST',
		data : param,
		success : function(res){
			if(res == 1 ){
				alert('삭제 성공');
				location.href='/views/community/guideBoard';
			}
		}
	}) 
}

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
				location.reload();
			}else if (res === 0){
				$('#check_login').text('없는 아이디거나 비밀번호가 틀렸습니다').css('color','red').css('font-size','small');
			}
		}
	})
}
function logOut(){
	$.ajax({
		url : '/user/logOut',
		type : 'GET',
		success : function(res){
			if(res == 1){
				
				location.reload();
			}
		}
		
	})
}
window.addEventListener('load',list);
var param;
function list(cnt) {

	if (isNaN(cnt)) {
		cnt = 1;
	}
	$.ajax({	
		url : '/community/selectList1',
				data : {
					currentPageNo : cnt
				},
				success : function(res) {
					var html = '';
					for ( var idx in res.rList) {
						var col = res.rList[idx];
						html += '<tr>';
						html += '<td>' + col.guideNum + '</td>';
						html += '<td>' + col.userNick + '</td>';
						if(col.tagCnt !=0){
						
						html += '<td><a href="javascript:void(0)" onclick="guideUpdate('
								+ col.guideNum + ')">' + col.guideTitle+'  ['+col.tagCnt+']'
								+ '</a></td>';
						}else {
							html += '<td><a href="javascript:void(0)" onclick="guideUpdate('
								+ col.guideNum + ')">' + col.guideTitle;
								+ '</a></td>';
						}
						if (col.guideFilepath != null) {
							html += '<td><img src="/resources/guideBoardimg/'+col.guideFilepath+' "width="50px"></td>';
						} else {
							html += '<td></td>';
						}
						html += '<td>' + col.guideJoin + '</td>';
						html += '<td>' + col.guideCnt + '</td>';
						html += '</tr>'
					}
					document.querySelector("tbody").innerHTML = html;
					var page = '';
					page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="list('
							+ 1 + ')">처음</a></li>';
					for ( var idx in res.pList) {
						var col = res.pList[idx];
						if (col.currentPageNo > 1) {
							page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="list('
									+ (col.currentPageNo - 1)
									+ ')">이전</a></li>';
						} else {
							page += '<li class="page-item disabled">'
									+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">이전</a></li>';
						}
						for (var i = col.startPageNo; i <= col.lastPageNo; i++) {
							page += '<li class="page-item">';

							page += ' <a class="page-link" href="javascript:void(0)" onclick="page('
									+ i + ')">' + i + '</a>';
							page += '</li>';
						}
						if (col.currentPageNo == col.lastPageNo) {
							page += '<li class="page-item disabled">'
									+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">다음</a></li>';
						} else {
							page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="list('
									+ (col.currentPageNo + 1)
									+ ')">다음</a></li>';
						}

						page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="list('
								+ (col.endPageNo) + ')">마지막</a></li>';
					}
					document.getElementById('page').innerHTML = page;
				}

			})
}

function page(cnt) {
	list(cnt);
}

function guideUpdate(guideNum) {
	location.href = '/views/community/guideView?guideNum=' + guideNum;

}
function doSearch() {
	var type = $('#type').val();
	var keyword = $('#keyword').val();
	
			$.ajax({
				url : '/community/selectSearchList1',
				data : {
					type : type,
					keyword : keyword
				},
				success : function(res) {
					var html = '';
					if (res.rList.length == 0) {
						html += '<h2 align="center">등록된 게시글이 없습니다</h2>';
					}
					for ( var idx in res.rList) {
						var col = res.rList[idx];
						html += '<tr>';
						html += '<td>' + col.guideNum + '</td>';
						html += '<td>' + col.userNick + '</td>';
						html += '<td><a href="javascript:void(0)" onclick="guideUpdate('
								+ col.guideNum + ')">' + col.guideTitle
								+ '</a></td>';
						if (col.guideFilepath != null) {
							html += '<td><img src="/resources/'+col.guideFilepath+'"width="50px"></td>';
						} else {
							html += '<td></td>';
						}
						html += '<td>' + col.guideJoin + '</td>';
						html += '<td>' + col.guideCnt + '</td>';
						html += '</tr>'
					}
					document.querySelector("tbody").innerHTML = html;
					var page = '';
					page += '<li><a href="javascript:void(0)" onclick="list(' + 1
							+ ')">처음</a></li>';
					for ( var idx in res.pList) {
						var col = res.pList[idx];
						if (col.currentPageNo > 1) {
							page += '<li><a href="javascript:void(0)" onclick="list('
									+ (col.currentPageNo - 1)
									+ ')">이전</a></li>';
						} else {
							page += '이전';
						}
						for (var i = col.startPageNo; i <= col.lastPageNo; i++) {
							page += '<li>';
							page += '<li><a href="javascript:void(0)" onclick="page('
									+ i + ')">' + '[' + i + ']'
									+ '</a></li>';
							page += '</li>';
						}
						if (col.currentPageNo == col.lastPageNo) {
							page += '다음';
						} else {
							page += '<li><a href="javascript:void(0)" onclick="list('
									+ (col.currentPageNo + 1)
									+ ')">다음</a></li>';
						}

						page += '<li><a href="javascript:void(0)" onclick="list('
								+ (col.endPageNo) + ')">마지막</a></li>';
					}
					document.getElementById('page').innerHTML = page;
				}

			})
}  
function nextNews(cnt){
	if(cnt==0){
		cnt=5;
		getNews(cnt);
		getNewsimg(cnt)
	}else if(cnt==6){
 		cnt=1;
 		getNews(cnt);
 		getNewsimg(cnt)
 	}else{
 		getNews(cnt); 	
 		getNewsimg(cnt)
 	}
 	}
function getNews(cnt){
    if(isNaN(cnt)) {
	 cnt =1;
	}
 $.ajax({
 	url:"/community/lolnews",	
 	type :'POST',
 	data : { cnt : cnt },
 	success:function(res){
 		for(var idx in res){
 			
 		var col =res[idx];	
 		if(col.title.length>17){
 			var str = col.title.substring(0,17)+'...'
 			$('#title').html('<a href="'+col.originallink+'"><h3 class="newsTitle">'+str+'</h3></a>');
 		}else{
 			$('#title').html('<a href="'+col.originallink+'"><h3 class="newsTitle">'+col.title+'</h3></a>');
 		}
 		if(col.description.length>50){
 			var str = col.description.substring(0,50)+'...'
 			$('#content').html('<a href="'+col.originallink+'"><h3 class="newsText">'+str+'</h3></a>');
 		}else{
 			$('#content').html('<a href="'+col.originallink+'"><h3 class="newsText">'+col.description+'</h3></a>');
 		}
 			
 		}
 		$('#nextNews').html('<span><button class="newsButton" onclick="nextNews('+ (cnt-1) +')"><em class="sp_img icon_blueprev"></em></button></span>' +
 		'<span><button class="newsButton" onclick="nextNews('+ (cnt+1) +')"><em class="sp_img icon_bluenext"></em></button></span>');
 	}
 })
 }
function getNewsimg(cnt){
 if (isNaN(cnt)) {
		cnt = 1;
	}
	$.ajax({
		url:"/community/lolnewsimg",	
		type :'POST',
	 	data :{ cnt : cnt },
		success:function(res){

			for(var idx in res){
				var col =res[idx];
			$('#newsimg').html('<img class="max-small" src="'+col.thumbnail+'">');
			}
		}
	})
	}
</script>
	
</section>
<jsp:include page="/common/footer.jsp"></jsp:include>

</body>
</html>