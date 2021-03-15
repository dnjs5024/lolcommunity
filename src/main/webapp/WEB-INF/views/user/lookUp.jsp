<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/head.jsp"/>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<section class="mysc">
	<div class=container style="margin-top: 100px; min-width:50%; max-width:50%;min-height:960px; " align="center" > 
	<div class="myinfo">
			<div style="max-width:20%; float: left;" >
				<img src="${sessionScope.user.userIcon}"style="height: 100px; border-radius: 70%;"> 
			</div>			
		    <div style="max-width:60%; float: left;"align="left">
		    	<div style="margin-left:5px">
		    		<h3><span>${sessionScope.user.userNick}(${sessionScope.user.userId})</span></h3>
		  		 </div>		
		   	 	<div style="margin-left:10px">
		   	 		<span>가입일 ${sessionScope.user.userJoin}</span><br>
					<span id="freeNumUser"></span><span id="tagNumUser"></span>							
				</div>
		    </div>		
							
	</div>
	
  <div class="myMenu">
  		<div class="mybutton" align="left" style="margin-bottom:50px; margin-top:20px; ">
   			<nav>

   				<button type="button" class="btn btn-outline-success" onclick="location.href='/views/user/update'">내정보수정</button> 

   				<button type="button" onclick="goUserFreeList()" class="btn btn-outline-success">내 게시글</button>
   				<button type="button" onclick="goFreeTagUserList()" class="btn btn-outline-success">내 댓글</button>   				
   			</nav>
   		</div>
   </div>
   <div id="span" align="left" style=" margin-bottom: 10px;"></div>
   <div class="mybox" style="border:1px solid #000000; border-width: 3px 0px 1px 0px; ">
		<div class="myList">
		
		<table class="table table-hover" id="userFreeList" style="display: ;">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>추천수</th>
			</tr>
			<tbody id="tuserFreeList"></tbody>
		</table>
		<table class="table table-hover" id="userFreeComment" style="display: none; ">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>작성내용</th>
				<th>작성일</th>
				
			</tr>
		<tbody id="userFreeTagList"></tbody>
		</table>
		<table class="table table-hover" id="userGuideList" style="display: none;">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>추천수</th>
			</tr>
			<tbody id="tuserGuideList"></tbody>
		</table>
			<table class="table table-hover" id="userGuideComment" style="display: none; ">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>작성내용</th>
				<th>작성일</th>
				
			</tr>
		<tbody id="userGuideTagList"></tbody>
		</table>
			<div class="hiddenMsg" id="hiddenMsg" style="display: none;"></div>
		</div>
		<div style="margin-bottom: 60px;">
			<div align="left" style="position: relative; left:12px; bottom:10px; "><input type="checkbox" id="check_all"><span> 전체선택</span></div>
			<div align="right" style="position: relative; right:9%;" id="deleteButton"></div>
			<div style="position: relative; right: 10%;">
				<nav aria-label="Page navigation example">
					<ul class="pagination" id="page">
					</ul>
				</nav>
			</div>
		</div>
	</div>	
	</div>
</section>	
<script type="text/javascript">
var userNum = ${sessionScope.user.userNum};
window.addEventListener('load',selectUserFreeList);
window.addEventListener('load',selectFreeBoardUser);
window.addEventListener('load',selectTagCntUser);


function goUserFreeList(){
	$('#userFreeList').css('display','');
	$('#userFreeComment').css('display','none');
	$('#userGuideComment').css('display','none');
	$('#userGuideList').css('display','none');
	$('#hiddenMsg').css('display','none');
	$("input").prop('checked', false);
	selectUserFreeList();
}
function goUserGuideList(){
	$('#userGuideList').css('display','');
	$('#userFreeList').css('display','none');
	$('#hiddenMsg').css('display','none');
    $("input").prop('checked', false);
	selectUserGuideList();
}
function goFreeTagUserList(){
	$('#userFreeList').css('display','none');
	$('#userGuideList').css('display','none');
	$('#userFreeComment').css('display','');
	$('#userGuideComment').css('display','none');
	$('#hiddenMsg').css('display','none');
	$("input").prop('checked', false);
	selectFreeTagUserList();
}
function goGuideTagUserList(){
	$('#userGuideComment').css('display','');
	$('#userFreeComment').css('display','none');
	$('#hiddenMsg').css('display','none');
	$("input").prop('checked', false);
	selectGuideTagUserList();
}
function selectUserFreeList(cnt){
	
	if(isNaN(cnt)){
		cnt =1 ;
	}
	
	$.ajax({
		url : '/community/selectUserFreeList',
		type :'POST',
		data : {
			userNum : userNum,
		    currentPageNo : cnt
		},
		
		success : function(res){
			console.log(res);
			var html ='';
			if (res.rList.length == 0) {
				$('#hiddenMsg').css('display','');
				html += '<span class="noListText" align="center">작성한 게시글이 없습니다</span>';
				document.getElementById('hiddenMsg').innerHTML = html;	
			}
			if (res.rList.length > 0) {	
			for(var idx in res.rList){
				var col = res.rList[idx];
				
				html += '<tr>';
				html += '<td><input type="checkbox" name="deleteCheck" value="'+col.freeNum+'"></td>'
				html += '<td>'+col.freeNum+'</td>';
				html += '<td><a href="/views/community/freeView?freeNum='+col.freeNum+'">'+col.freeTitle+'</a></td>';
				html += '<td>'+col.freeJoin+'</td>';
				html += '<td>'+col.freeCnt+'</td>';
				html += '<td>'+col.tagCnt+'</td>';
				html += '<td>'+col.likeCnt+'</td>';
				html += '</tr>'
			}
			
			$('#tuserFreeList').html(html);
			}
			var page = '';
			page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserFreeList('
					+ 1 + ')">처음</a></li>';
			for ( var idx in res.pList) {
				var col = res.pList[idx];
				
				if (col.currentPageNo > 1) {
					page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserFreeList('
							+ (col.currentPageNo - 1)
							+ ')">이전</a></li>';
				} else {
					page += '<li class="page-item disabled">'
							+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">이전</a></li>';
				}
				for (var i = col.startPageNo; i <= col.lastPageNo; i++) {
					page += '<li class="page-item">';

					page += ' <a class="page-link" href="javascript:void(0)" onclick="selectUserFreeList('
							+ i + ')">' + i + '</a>';
					page += '</li>';
				}
				if (col.currentPageNo == col.lastPageNo) {
					page += '<li class="page-item disabled">'
							+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">다음</a></li>';
				} else {
					page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserFreeList('
							+ (col.currentPageNo + 1)
							+ ')">다음</a></li>';
				}

				page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserFreeList('
						+ (col.endPageNo) + ')">마지막</a></li>';
			}
			document.getElementById('page').innerHTML = page;
			var span='';
			span +='<span style="margin-right: 10px;"><button class="hidenbutton" type="button" onclick="goUserFreeList()">자유게시판</button>';
			span +='</span><span style="margin-right: 10px;">';
			span +='<button class="hidenbutton" onclick="goUserGuideList()">공략게시판</button></span>';
			document.getElementById('span').innerHTML = span;
			var div ='';
			div ='<button class="btn btn-outline-success" onclick="checkFreeBoardDelete()">삭제</button>';
			document.getElementById('deleteButton').innerHTML = div;
		}
	})
	
}
function selectFreeTagUserList(cnt){
	if (isNaN(cnt)) {
		cnt = 1;
	}
	$.ajax({
		url : '/community/selectTagUserList',
		type : 'POST',
		data : {userNum:userNum,
				currentPageNo : cnt
		},
		success :function(res){
			var html ='';
			if (res.rList.length == 0) {
				$('#hiddenMsg').css('display','');
				html += '<span class="noTagText" align="center">작성한 댓글이 없습니다</span>';
				document.getElementById('hiddenMsg').innerHTML = html;	
			}
			if (res.rList.length > 0) {	
			for(var idx in res.rList){
				var col = res.rList[idx];
				html += '<tr>';
				html += '<td><input type="checkbox" name="deleteCheck" value="'+col.tagNum+'"></td>'
				html += '<td>'+col.freeNum+'</td>';
				html += '<td><a href="/views/community/freeView?freeNum='+col.freeNum+'">'+col.freeTitle+'</a></td>';
				html += '<td>'+col.tagContent+'</td>';
				html += '<td>'+col.tagJoin+'</td>';
				html += '</tr>';
			}
			$('#userFreeTagList').html(html);
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

					page += ' <a class="page-link" href="javascript:void(0)" onclick="selectFreeTagUserList('
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
			$('#page').html(page);
			var span='';
			span +='<span style="margin-right: 10px;"><button class="hidenbutton" type="button" onclick="goFreeTagUserList()">자유게시판</button>';
			span +='</span><span style="margin-right: 10px;">';
			span +='<button class="hidenbutton" onclick="goGuideTagUserList()">공략게시판</button></span>';
			document.getElementById('span').innerHTML = span;
			var div ='';
			div ='<button class="btn btn-outline-success" onclick="checkFreeTagDelete()">삭제</button>';
			document.getElementById('deleteButton').innerHTML = div;
		}
	})
}
function selectUserGuideList(cnt) {
	
	if (isNaN(cnt)) {
		cnt = 1;
	}
	
	$.ajax({	url : '/community/selectUserGuideList',
				type :'POST',
				data : {
					userNum:userNum,
					currentPageNo : cnt
				},
				success : function(res){
					var html ='';
					if (res.rList.length == 0) {
						$('#hiddenMsg').css('display','');
						html += '<span class="noListText" align="center">작성한 게시글이 없습니다</span>';
						document.getElementById('hiddenMsg').innerHTML = html;
						
				
					}
					if (res.rList.length > 0) {					
					for(var idx in res.rList){
						var col = res.rList[idx];
						console.log(col);
						html += '<tr>';
						html += '<td><input type="checkbox" name="deleteCheck" value="'+col.guideNum+'"></td>'
						html += '<td>'+col.guideNum+'</td>';
						html += '<td><a href="/views/community/guideView?guideNum='+col.guideNum+'">'+col.guideTitle+'</a></td>';
						html += '<td>'+col.guideJoin+'</td>';
						html += '<td>'+col.guideCnt+'</td>';
						html += '<td>'+col.tagCnt+'</td>';
						html += '<td>'+col.likeCnt+'</td>';
						html += '</tr>'
					}				
					$('#tuserGuideList').html(html);
					}
					var page = '';
					page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserGuideList('
							+ 1 + ')">처음</a></li>';
					for ( var idx in res.pList) {
						var col = res.pList[idx];
						
						if (col.currentPageNo > 1) {
							page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserGuideList('
									+ (col.currentPageNo - 1)
									+ ')">이전</a></li>';
						} else {
							page += '<li class="page-item disabled">'
									+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">이전</a></li>';
						}
						for (var i = col.startPageNo; i <= col.lastPageNo; i++) {
							page += '<li class="page-item">';

							page += ' <a class="page-link" href="javascript:void(0)" onclick="selectUserGuideList('
									+ i + ')">' + i + '</a>';
							page += '</li>';
						}
						if (col.currentPageNo == col.lastPageNo) {
							page += '<li class="page-item disabled">'
									+ '<a class="page-link" href="javascript:void(0)" tabindex="-1" aria-disabled="true">다음</a></li>';
						} else {
							page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserGuideList('
									+ (col.currentPageNo + 1)
									+ ')">다음</a></li>';
						}

						page += '<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectUserGuideList('
								+ (col.endPageNo) + ')">마지막</a></li>';
					}
					document.getElementById('page').innerHTML = page;
					var span='';
					span +='<span style="margin-right: 10px;"><button class="hidenbutton" type="button" onclick="goUserFreeList()">자유게시판</button>';
					span +='</span><span style="margin-right: 10px;">';
					span +='<button class="hidenbutton" onclick="selectUserGuideList()">공략게시판</button></span>';
					document.getElementById('span').innerHTML = span;
					var div ='';
					div ='<button class="btn btn-outline-success" onclick="checkGuideBoardDelete()">삭제</button>';
					document.getElementById('deleteButton').innerHTML = div;
				}
			})
			
		}
function selectGuideTagUserList(cnt){
	if (isNaN(cnt)) {
		cnt = 1;
	}
	
	$.ajax({
		url : '/community/selectGuideTagUserList',
		type : 'POST',
		data : {userNum:userNum,
				currentPageNo : cnt
		},
		success :function(res){
			var html =''
			if (res.rList.length == 0) {	
				$('#hiddenMsg').css('display','');
				html += '<span class="noTagText" align="center">작성한 댓글이 없습니다</span>';
				document.getElementById('hiddenMsg').innerHTML = html;	
			}
			if (res.rList.length > 0) {	
			for(var idx in res.rList){
				var col = res.rList[idx];
				html += '<tr>';
				html += '<td><input type="checkbox" name="deleteCheck" value="'+col.guideTagNum+'"></td>'
				html += '<td>'+col.guideNum+'</td>';
				html += '<td><a href="/views/community/guideView?guideNum='+col.guideNum+'">'+col.guideTitle+'</a></td>';
				html += '<td>'+col.tagContent+'</td>';
				html += '<td>'+col.tagJoin+'</td>';
				html += '</tr>';
			}
			$('#userGuideTagList').html(html);
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

					page += ' <a class="page-link" href="javascript:void(0)" onclick="selectGuideTagUserList('
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
			$('#page').html(page);
			var span='';
			span +='<span style="margin-right: 10px;"><button class="hidenbutton" type="button" onclick="goFreeTagUserList()">자유게시판</button>';
			span +='</span><span style="margin-right: 10px;">';
			span +='<button class="hidenbutton" onclick="goGuideTagUserList()">공략게시판</button></span>';
			document.getElementById('span').innerHTML = span;
			var div ='';
			div ='<button class="btn btn-outline-success" onclick="checkGuideTagDelete()">삭제</button>';
			document.getElementById('deleteButton').innerHTML = div;
		}
	})
}

function selectFreeBoardUser(){
	$.ajax({
		url :'/community/selectFreeBoardUser',
		type : 'POST',
		data :{userNum : userNum},
		success : function(res){
			$('#freeNumUser').html('총 작성글 : '+res+'개  ');
		}
	})
}
function selectTagCntUser(){
	$.ajax({
		url :'/community/selectTagCntUser',
		type : 'POST',
		data :{userNum : userNum},
		success : function(res){
			$('#tagNumUser').html('- 총 댓글 : '+res+'개');
		}
	})
}
function checkFreeBoardDelete(){
	  var deleteCheck = document.getElementsByName("deleteCheck");
	  var deleteList = new Array();
      for(var i=0;i<deleteCheck.length;i++){
    	  if(deleteCheck[i].checked == true) {       
    	  for(var j=0;j<deleteCheck.length;j++){
    		  if(deleteList[j]==null){
    		  deleteList[j] = deleteCheck[i].value;   
    		  break;
    		  }
            }
    	  }
      }
     console.log(deleteList);
     $.ajax({
    	 url : "/community/checkFreeBoardDelete",
    	 type :'POST',
    	 data : {deleteList:deleteList
    		 },
    	 success : function(res){
    		 location.href="/views/user/lookUp";
    		 alert('삭제가 완료되었습니다.');
    	 }  	 
     })
}
function checkGuideBoardDelete(){
	  var deleteCheck = document.getElementsByName("deleteCheck");
	  var deleteList = new Array();
    for(var i=0;i<deleteCheck.length;i++){
  	  if(deleteCheck[i].checked == true) {       
  	  for(var j=0;j<deleteCheck.length;j++){
  		  if(deleteList[j]==null){
  		  deleteList[j] = deleteCheck[i].value;   
  		  break;
  		  }
          }
  	  }
    }
   console.log(deleteList);
   $.ajax({
  	 url : "/community/checkGuideBoardDelete",
  	 type :'POST',
  	 data : {deleteList:deleteList
	 },
  	 success : function(res){
  		 location.href="/views/user/lookUp";
		 alert('삭제가 완료되었습니다.');
  	 }  	 
   })
}
function checkFreeTagDelete(){
	  var deleteCheck = document.getElementsByName("deleteCheck");
	  var deleteList = new Array();
    for(var i=0;i<deleteCheck.length;i++){
  	  if(deleteCheck[i].checked == true) {       
  	  for(var j=0;j<deleteCheck.length;j++){
  		  if(deleteList[j]==null){
  		  deleteList[j] = deleteCheck[i].value;   
  		  break;
  		  }
          }
  	  }
    }
   console.log(deleteList);
   $.ajax({
  	 url : "/community/checkFreeTagDelete",
  	 type :'POST',
  	 data : {deleteList:deleteList
	 },
  	 success : function(res){
  		 location.href="/views/user/lookUp";
		 alert('삭제가 완료되었습니다.');
  	 }  	 
   })
}
function checkGuideTagDelete(){
	  var deleteCheck = document.getElementsByName("deleteCheck");
	  var deleteList = new Array();
    for(var i=0;i<deleteCheck.length;i++){
  	  if(deleteCheck[i].checked == true) {       
  	  for(var j=0;j<deleteCheck.length;j++){
  		  if(deleteList[j]==null){
  		  deleteList[j] = deleteCheck[i].value;   
  		  break;
  		  }
          }
  	  }
    }
   console.log(deleteList);
   $.ajax({
  	 url : "/community/checkGuideTagDelete",
  	 type :'POST',
  	 data : {deleteList:deleteList
	 },
  	 success : function(res){
  		 location.href="/views/user/lookUp";
		 alert('삭제가 완료되었습니다.');
  	 }  	 
   })
}
$(function(){
    $("#check_all").click(function(){
        var chk = $(this).is(":checked");
        if(chk){
            $("input").prop('checked', true);
        } else {
            $("input").prop('checked', false);
        }
    });
});


function goPage(num){
	location.href='/views/community/freeView?freeNum='+num;
}
function goUserUpdate(){
	location.href="/views/user/update";
}
</script>	
<jsp:include page="/common/footer.jsp"></jsp:include>
</body>
</html>













