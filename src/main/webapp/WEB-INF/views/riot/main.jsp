<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
   <!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<title>Company Bootstrap Template - Index</title>
<meta content="" name="descriptison">
<meta content="" name="keywords">
<%@ include file="/common/head.jsp" %>
<style type="text/css">
#hero{
width: 100%;
background-repeat: no-repeat;
background-color: black;
background-size: 100%;
background-position: center;
}
 
:root {
  --rad: .7rem;
  --dur: .3s;
  --color-dark: #2f2f2f;
  --color-light: #fff;
  --color-brand: #57bd84;
  --font-fam: 'Lato', sans-serif;
  --height: 5rem;
  --btn-width: 6rem;
  --bez: cubic-bezier(0, 0, 0.43, 1.49);
}

html { box-sizing: border-box; height: 100%; font-size: 10px; } *, *::before, *::after { box-sizing: inherit; }

form {
  position: relative;
  width: 70rem;
  background: #6ec1e4;
  border-radius: var(--rad);
}
input, button {
  height: var(--height);
  font-family: var(--font-fam);
  border: 0;
  color: var(--color-dark);
  font-size: 1.8rem;
}
input[type="search"] {
  outline: 0;
  width: 100%;
  background: var(--color-light);
  padding: 0 1.6rem;
  border-radius: var(--rad);
  appearance: none;
  transition: all var(--dur) var(--bez);
  transition-property: width, border-radius;
  z-index: 1;
  position: relative;
}
button {
  display: none;
  position: absolute;
  top: 0;
  right: 0;
  width: var(--btn-width);
  font-weight: bold;
  background: #6ec1e4;
  border-radius: 0 var(--rad) var(--rad) 0;
}
input:not(:placeholder-shown){
  border-radius: var(--rad) 0 0 var(--rad);
  width: calc(100% - var(--btn-width));
}
input:not(:placeholder-shown) + button {
  display: block;
}
label {
  position: absolute;
  clip: rect(1px, 1px, 1px, 1px);
  padding: 0;
  border: 0;
  height: 1px;
  width: 1px;
  overflow: hidden;
}


</style>
</head>
<body>
<!-- ======= Header ======= -->
<%@ include file="/common/header.jsp" %>   
   <!-- ======= Hero Section ======= -->
   <section id="hero" style="height:100vh;padding-bottom: 0">  
        <div style="margin: 100px 600px 0 600px;"> 
	         <form on autocomplete="off" role="search" action="/summoner/">
			  <label for="search">Search for stuff</label>
			  <input id="search" name="summoner" type="search" placeholder="소환사 검색..."  type="submit"  autofocus required />
			  <button type="submit">GO</button>    
		    </form>
	    </div>
      <div class="swiper-container" style="margin-top: 100px" >
      <div class="swiper-wrapper">
         <div class="swiper-slide">
           <h1 class="logo mr-auto" style="font-size: 95px;">
              <a href="/" style="font; color: white;">
               <span style="-webkit-writing-mode: vertical-rl; ">ROTATION</span> 
              </a>
           </h1>
          </div>
         <c:forEach items="${champs}" var="champ">
           <div class="swiper-slide">
            <a href="/champion/${champ.championInfoId}">
             <img src="http://ddragon.leagueoflegends.com/cdn/img/champion/loading/${champ.championInfoId}_0.jpg" width="250px" />
            </a>
            </div>
         </c:forEach>
         </div>
            <div class="swiper-button-next" style="color: yellow"></div>
         </div>
   </section>
<%@ include file="/common/footer.jsp" %>
<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<!-- Initialize Swiper -->
<script>
   var swiper = new Swiper('.swiper-container', {
     slidesPerView: 8, 
     spaceBetween: 2, 
     slidesPerGroup: 2,  
     loop: true,
     loopFillGroupWithBlank: true,
     pagination: {
       el: '.swiper-pagination',
       clickable: true,
     },
     navigation: {
       nextEl: '.swiper-button-next',
       prevEl: '.swiper-button-prev',
     },
   });
</script>
</body>
</html>