<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script src="/resources/assets/js/summemote/summernote-lite.js"></script>
<script src="/resources/assets/js/summemote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/resources/assets/css/summemote/summernote-lite.css"> 
</head>
<body>
 <form method="post">
  <textarea id="summernote" name="editordata"></textarea>
  <button type="button" onclick="test()"> 글작성</button>
</form>

	<div id="write">
	</div>
	<textarea id="ttee"></textarea>

<script type="text/javascript">
window.addEventListener('load',text);
window.addEventListener('load',text1);

function text() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
};

function text1() {
	//여기 아래 부분
	$('#ttee').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
	});
};




function test(){
	var test1 =$('#summernote').val();
	
	$('#ttee').summernote('code', test1);
}
</script>
</body>
</html>