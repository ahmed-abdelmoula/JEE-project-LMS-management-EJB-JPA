<%@page import="domaine.Categorie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 
ArrayList<Categorie> ltrait = (ArrayList<Categorie>)session.getAttribute("listOfCategorie");

Utilisateur user=(Utilisateur)session.getAttribute("user") ;



if (user!=null)
{%>


<%@include file="navbarPrincipal.jsp" %> 

<%} else {%> 
<%@include file="navbar.jsp" %>

<%} %>


<style type="text/css">
body {
	background: #ebebeb;
}
h2 {
	color: #696969;
	font-size: 26px;
	font-weight: 300;
	text-align: center;
	position: relative;
	margin: 40px 70px;
	text-transform: uppercase;
	font-family: 'Open Sans', sans-serif;
}
h2::after {
	content: "";
    width: 100%;
    position: absolute;
    margin: 0 auto;
    height: 1px;
    border-radius: 1px;
    background: #d4d4d4;
    left: 0;
    right: 0;
    bottom: 14px;
}
h2 span {
	display: inline-block;
	padding: 0 25px;
	background:#ebebeb;
	position:relative;
	z-index:2;
}
.col-center {
	margin: 0 auto;
	float: none !important;
}
.carousel {	
	margin: 30px auto 50px;
	padding: 0 68px;
}
.carousel .item {
    text-align: center;
	overflow: hidden;
    height: 160px;
}
.carousel .item .img-box {
	background: #fff;
	padding: 9px;
	box-shadow: 0 6px 20px -6px rgba(0,0,0,0.4);
}
.carousel .item img {
    margin: 0 auto;
}
.carousel .carousel-control {
	width: 68px;
	background: none;
}
.carousel .carousel-control i {
    font-size: 28px;
    position: absolute;
    top: 50%;
    display: inline-block;
    margin-top: -15px;
    z-index: 5;
    left: 0;
    right: 0;
	color: rgba(0, 0, 0, 0.8);
    text-shadow: 0 3px 3px #e6e6e6, 0 0 0 #000;
}
.carousel .carousel-indicators {
	bottom: -40px;
}
.carousel-indicators li, .carousel-indicators li.active {
	width: 10px;
	height: 10px;
	border-radius: 50%;
	margin: 1px 4px;
	box-shadow: inset 0 2px 1px rgba(0,0,0,0.2);
}
.carousel-indicators li {	
	background: #999;
	border-color: transparent;
}
.carousel-indicators li.active {
	background: #555;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-9 col-center m-auto">
			<h2><span>Our Menu<b> Categorie</b></span></h2>
			<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="0">
				<!-- Carousel indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>   
				<!-- Wrapper for carousel items -->
		
				<div class="carousel-inner">
		
					<div class="item carousel-item active">
						<div class="row">
										<%
						int j=0;
			if (ltrait.size() != 0 || ltrait != null) {
				for (int i = 0; i < ltrait.size(); i++) {
					
					
		%>
							<div class="col-sm-4"><div class="img-box"><img src="<%=ltrait.get(i).getImageCategorie()%>" class="img-responsive img-fluid" alt=""></div><a href='FicheCategorieAction?mode=ProduitCorresp&id=<%=ltrait.get(i).getId()%>'><%=ltrait.get(i).getNomCategorie()%></a></div>
							<%
					
				  
				}
					
			} 
			
			%>
					
						</div>
					</div>
			
				 	<div class="item carousel-item ">
						<div class="row">
							<div class="col-sm-4"><div class="img-box"><img src="/examples/images/thumbs/7.jpg" class="img-responsive img-fluid" alt=""></div></div>
							<div class="col-sm-4"><div class="img-box"><img src="/examples/images/thumbs/8.jpg" class="img-responsive img-fluid" alt=""></div></div>
							<div class="col-sm-4"><div class="img-box"><img src="/examples/images/thumbs/9.jpg" class="img-responsive img-fluid" alt=""></div></div>
						</div> 
					</div>
				</div>
				<!-- Carousel controls -->
				<a class="carousel-control left carousel-control-prev" href="#myCarousel" data-slide="prev">
					<i class="fa fa-chevron-left"></i>
				</a>
				<a class="carousel-control right carousel-control-next" href="#myCarousel" data-slide="next">
					<i class="fa fa-chevron-right"></i>
				</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>