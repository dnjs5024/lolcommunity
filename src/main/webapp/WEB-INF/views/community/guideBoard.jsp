<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<section style="padding-bottom: 300px" id ="pysSection">


<div class="all">
<div class="container" id="container_board">
<div class="container" id="infocontainer" style="width: 100%">
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
<div class="container" >
<div class="rightmenu">
<div>
<article class="newsarticle">
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
<div>
<article class="bestarticle">
<div class="bestlist">
<header style="min-height:50px; border: 2px dashed #000000; border-width: 0px 0px 2px 0px;">
<h3 class="bestHeaderText">공략게시판 인기글</h3><div id="nextList"></div></header>
	<div class="bestBody">
		<ul id="bestList">
		</ul>	
	</div>
</div>
</article>
</div>
<div class="boardbanner">
		<ins class="kakao_ad_area" style="display:none;" 
			 data-ad-unit    = "DAN-rhr57o3r03e9" 
			 data-ad-width   = "300" 
			 data-ad-height  = "250"></ins> 
		<script type="text/javascript" src="//t1.daumcdn.net/kas/static/ba.min.js" async></script>	
</div>
</div>
</div>
	<div class="row">
<div class="col-lg-3" id="sidemenu">
	<div id="rdiv">
		<div style="text-align: right;">
			<div class="card" style="text-align: center; font-size:large ; border: 2px solid #000000; border-width: 2px 2px 2px 2px;
			border-left-color: #f8f9fa; border-right-color:#f8f9fa;">
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
	<!-- 			<h1 class="my-4">자유게시판</h1>		 -->
	<div class="list-group" id="list-group">
		<a href="/views/community/freeBoard" class="list-group-item">자유게시판</a> 
		<a href="/views/community/guideBoard" class="list-group-item">공략게시판</a>
		<a href="javascript:void(0)" class="list-group-item">유머</a>
		<a href="javascript:void(0)" class="list-group-item">영상게시판</a>
		<a href="javascript:void(0)" class="list-group-item">팬 아트</a>
		<a href="javascript:void(0)" class="list-group-item">유저찾기</a>
	</div>
	<div class="newList">
		<table class="table table-hover" style="background-color:white; border: 2px solid #000000;
	border-width: 2px 0px 0px 0px;">
			<thead>
				<tr>
					<th colspan="2">자유게시판 최신글</th>
				</tr>
			</thead>
			<tbody id="newTable"></tbody>		
		</table>
	</div>
</div>
		<!-- /.col-lg-3 -->
		<div class="col-lg-9" >
			<table class="table table-hover" style="background-color:white;border: 2px solid #000000;
	border-width: 2px 0px 0px 0px; ">
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
				<tbody id="guideList"></tbody>
			</table>
			<div class="hiddenMsg" id="boardHiddenMsg" style="display: none;"></div>
		<div class="container">
<div class="container-bottom" style="background-color: white;" style="margin:0px; padding:0px;">
	<div style="position: relative; top: 45px;">
		<div class="write">
			<c:if test="${!empty sessionScope.user}" >
				<ui><a href="/views/community/guide_write"><button type="button" class="btn_blue write">글쓰기</button></a></ui>
			</c:if>
		</div>
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
	</div>
</div>
</div>	
</section>
<script type="text/javascript">
window.addEventListener('load',list);
window.addEventListener('load',getNews);
window.addEventListener('load',getNewsimg);
window.addEventListener('load',selectGuideBoardUser);
window.addEventListener('load',selectTagCntUser);
window.addEventListener('load',selectNewList);
window.addEventListener('load',selectUserOne);
window.addEventListener('load',getBestList);
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



function selectTagCntUser(){
	$.ajax({
		url :'/community/selectTagCntUser',
		type : 'POST',
		data :{userNum : userNum},
		success : function(res){
			$('#tagNumUser').html('<a href="/views/user/lookUp">'+res+'</a>');
		}
	})
}


function selectGuideBoardUser(){
	$.ajax({
		url :'/community/selectFreeBoardUser',
		type : 'POST',
		data :{userNum : userNum},
		success : function(res){
			
			$('#guideNumUser').html('<a href="/views/user/lookUp">'+res+'</a>');
		}
	})
}

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
					document.querySelector("#guideList").innerHTML = html;
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

function searchPage(cnt) {
	doSearch(cnt);
}
function guideUpdate(guideNum, userNum) {
	location.href = '/views/community/guideView?guideNum=' + guideNum;

}
function doSearch(cnt) {
	
	if (isNaN(cnt)) {
		cnt = 1;
	}
	var type = $('#type').val();
	var keyword = $('#keyword').val();
			$.ajax({
				url : '/community/selectSearchList1',
				data : {
					type : type,
					keyword : keyword,
					currentPageNo : cnt
				},
				success : function(res) {
					var html = '';
					if (res.rList.length == 0) {
						$('#boardHiddenMsg').css('display','');
						document.getElementById("guideList").innerHTML = html;
						html += '<span class="noListText" align="center">등록된 게시글이 없습니다</span>';
						document.getElementById('boardHiddenMsg').innerHTML = html;	
					}
					if (res.rList.length > 0) {	
					for ( var idx in res.rList) {
						$('#boardHiddenMsg').css('display','none');
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
					document.getElementById("guideList").innerHTML = html;
				}
					
					
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

							page += ' <a class="page-link" href="javascript:void(0)" onclick="searchPage('
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
function selectNewList(){
	$.ajax({
		url : '/community/selectNewList',
		type : 'GET',
		success : function(res){
			var html ='';
			for(var idx in res){
			var col =res[idx];
			console.log(col.freeTitle);
			html += '<tr>';
			html += '<th>제목</th>';
			if(col.freeTitle.length>=11){
			html += '<td><a href="/views/community/freeView?freeNum=' + col.freeNum
			+ userNum+'">'+col.freeTitle.substring(0,7)+'...</a></td>';
			}else{		
				if(col.freeTitle=="게시글이 없습니다"){
					html +='<td>'+col.freeTitle+'</td>';
				}else{
					html += '<td><a href="/views/community/freeView?freeNum=' + col.freeNum
					+ '">'+col.freeTitle+'</td>';
					}	
			}				
			html += '</tr>';				
			}
			document.getElementById('newTable').innerHTML= html;
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

function getBestList(cnt){
	 if (isNaN(cnt)) {
			cnt = 1;
		}
	$.ajax({
		url:"/community/selectBestGuideList",
		data:{ cnt : cnt
			},
		success : function(res){
			var html ='';
			for(var idx in res.rList){
				var col = res.rList[idx];
				if(col.guideTitle=="인기글이 없습니다"){
					html +='<li class="bestlistText">'+col.guideTitle+'</li>';
				}else{
					if(col.guideTitle.length>=11){
						html +='<li class="bestlistText"><a href="/views/community/guideView?guideNum='+col.guideNum+'">'+ col.guideTitle.substring(0,7) +'...</a></li>';						
						}else{		
							if(col.guideTitle=="인기글이 없습니다"){
								html +='<li class="bestlistText">'+col.guideTitle+'</li>';
							}else{
								html +='<li class="bestlistText"><a href="/views/community/guideView?guideNum='+col.guideNum+'">'+ col.guideTitle +'</a></li>';						
								}	
						}
					}				
			}
			document.getElementById('bestList').innerHTML =html;
			if(cnt==1){
				$('#nextList').html('<span><button class="bestListButton" onclick="nextBestList('+ (res.pList[0].endPageNo) +')"><em class="sp_img icon_blueprev"></em></button></span>' +
		 		 '<span><button class="bestListButton" onclick="nextBestList('+ (cnt+1) +')"><em class="sp_img icon_bluenext"></em></button></span>');
			}else if(cnt==res.pList[0].endPageNo){
				$('#nextList').html('<span><button class="bestListButton" onclick="nextBestList('+ (cnt-1) +')"><em class="sp_img icon_blueprev"></em></button></span>' +
				'<span><button class="bestListButton" onclick="nextBestList('+ 1 +')"><em class="sp_img icon_bluenext"></em></button></span>');	
			}else{
				$('#nextList').html('<span><button class="bestListButton" onclick="nextBestList('+ (cnt-1) +')"><em class="sp_img icon_blueprev"></em></button></span>' +
				 '<span><button class="bestListButton" onclick="nextBestList('+ (cnt+1) +')"><em class="sp_img icon_bluenext"></em></button></span>');				
			}
			if(res.pList[0].endPageNo==1){
				$('#nextList').html('<span><button class="bestListButton" onclick="nextBestList('+ 1 +')"><em class="sp_img icon_blueprev"></em></button></span>' +
				'<span><button class="bestListButton" onclick="nextBestList('+ 1 +')"><em class="sp_img icon_bluenext"></em></button></span>');
			}
		}
	})
}
function nextBestList(cnt){
		getBestList(cnt);		
}
</script>
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>