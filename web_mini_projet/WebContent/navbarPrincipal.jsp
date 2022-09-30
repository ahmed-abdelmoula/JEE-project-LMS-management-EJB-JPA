<%@page import="domaine.Produit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
<%@page language="java"
	import="java.util.ArrayList,java.util.Iterator,domaine.Utilisateur,domaine.Role"%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
	crossorigin="anonymous">
<style type="text/css">
body {
	background: #eeeeee;
	font-family: 'Varela Round', sans-serif;
}

.form-inline {
	display: inline-block;
}

.navbar-header.col {
	padding: 0 !important;
}

.navbar {
	color: #fff;
	background: #ad2321;
	padding: 5px 16px;
	border-radius: 0;
	border: none;
	box-shadow: 0 0 4px rgba(0, 0, 0, .1);
}

.navbar img {
	border-radius: 50%;
	width: 36px;
	height: 36px;
	margin-right: 10px;
}

.navbar .navbar-brand {
	color: #efe5ff;
	padding-left: 0;
	padding-right: 50px;
	font-size: 24px;
}

.navbar .navbar-brand:hover,.navbar .navbar-brand:focus {
	color: #fff;
}

.navbar .navbar-brand i {
	font-size: 25px;
	margin-right: 5px;
}

.search-box {
	position: relative;
}

.search-box input {
	padding-right: 35px;
	min-height: 38px;
	border: none;
	background: #faf7fd;
	border-radius: 3px !important;
}

.search-box input:focus {
	background: #fff;
	box-shadow: none;
}

.search-box .input-group-addon {
	min-width: 35px;
	border: none;
	background: transparent;
	position: absolute;
	right: 0;
	z-index: 9;
	padding: 10px 7px;
	height: 100%;
}

.search-box i {
	color: #a0a5b1;
	font-size: 19px;
}

.navbar .nav-item i {
	font-size: 18px;
}

.navbar .nav-item span {
	position: relative;
	top: 3px;
}

.navbar .nav>li a {
	color: #efe5ff;
	padding: 8px 15px;
	font-size: 14px;
}

.navbar .nav>li a:hover,.navbar .nav>li a:focus {
	color: #fff;
	text-shadow: 0 0 4px rgba(255, 255, 255, 0.3);
}

.navbar .nav>li>a>i {
	display: block;
	text-align: center;
}

.navbar .dropdown-item i {
	font-size: 16px;
	min-width: 22px;
}

.navbar .dropdown-item .material-icons {
	font-size: 21px;
	line-height: 16px;
	vertical-align: middle;
	margin-top: -2px;
}

.navbar .nav-item.open>a,.navbar .nav-item.open>a:hover,.navbar .nav-item.open>a:focus
	{
	color: #fff;
	background: none !important;
}

.navbar .dropdown-menu {
	border-radius: 1px;
	border-color: #e5e5e5;
	box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
}

.navbar .dropdown-menu li a {
	color: #777 !important;
	padding: 8px 20px;
	line-height: normal;
}

.navbar .dropdown-menu li a:hover,.navbar .dropdown-menu li a:focus {
	color: #333 !important;
	background: transparent !important;
}

.navbar .nav .active a,.navbar .nav .active a:hover,.navbar .nav .active a:focus
	{
	color: #fff;
	text-shadow: 0 0 4px rgba(255, 255, 255, 0.2);
	background: transparent !important;
}

.navbar .nav .user-action {
	padding: 9px 15px;
}

.navbar .navbar-toggle {
	border-color: #fff;
}

.navbar .navbar-toggle .icon-bar {
	background: #fff;
}

.navbar .navbar-toggle:focus,.navbar .navbar-toggle:hover {
	background: transparent;
}

.navbar .navbar-nav .open .dropdown-menu {
	background: #faf7fd;
	border-radius: 1px;
	border-color: #faf7fd;
	box-shadow: 0 2px 8px rgba(0, 0, 0, .05);
}

.navbar .divider {
	background-color: #e9ecef !important;
}

@media ( min-width : 1200px) {
	.form-inline .input-group {
		width: 350px;
		margin-left: 30px;
	}
}

@media ( max-width : 1199px) {
	.navbar .nav>li>a>i {
		display: inline-block;
		text-align: left;
		min-width: 30px;
		position: relative;
		top: 4px;
	}
	.navbar .navbar-collapse {
		border: none;
		box-shadow: none;
		padding: 0;
	}
	.navbar .navbar-form {
		border: none;
		display: block;
		margin: 10px 0;
		padding: 0;
	}
	.navbar .navbar-nav {
		margin: 8px 0;
	}
	.navbar .navbar-toggle {
		margin-right: 0;
	}
	.input-group {
		width: 100%;
	}
}
</style>
</head>
<body>

	<style>
$
main-color
:
 
#6394F8
;


$
light-text
:
 
#ABB0BE
;



@import url(https://fonts.googleapis.com/css?family=Lato:300,400,700);

@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css)
	;

*,*:before,*:after {
	box-sizing: border-box;
}

body {
	font: 14px/22px "Lato", Arial, sans-serif;
	background: #6394F8;
}

.lighter-text {
	color: #ABB0BE;
}

.main-color-text {
	color: $main-color;
}

nav {
	padding: 20px 0 40px 0;
	background: #F8F8F8;
	font-size: 16px;
	.
	navbar-left
	{
	float
	:
	left;
}

.navbar-right {
	float: right;
}

ul {li { display:inline;
	padding-left: 20px; a { color : #777777;
	text-decoration: none;
	&:
	hover
	{
	color
	:
	black;
}

}
}
}
}
.container {
	margin: auto;
	width: 80%;
}

.badge {
	background-color: #6394F8;
	border-radius: 10px;
	color: white;
	display: inline-block;
	font-size: 12px;
	line-height: 1;
	padding: 3px 7px;
	text-align: center;
	vertical-align: middle;
	white-space: nowrap;
}

.shopping-cart {
	margin: 20px 0;
	float: right;
	background: white;
	width: 320px;
	position: relative;
	border-radius: 3px;
	padding: 20px; . shopping-cart-header { border-bottom : 1px solid
	#E8E8E8;
	padding-bottom: 15px;
	.
	shopping-cart-total
	{
	float
	:
	right;
}

}
.shopping-cart-items {
	padding-top: 20px;
	li
	{
	margin-bottom
	:
	18px;
}

img {
	float: left;
	margin-right: 12px;
}

.item-name {
	display: block;
	padding-top: 10px;
	font-size: 16px;
}

.item-price {
	color: $main-color;
	margin-right: 8px;
}

.item-quantity {
	color: $light-text;
}

}
}
.shopping-cart:after {
	bottom: 100%;
	left: 89%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
	border-bottom-color: white;
	border-width: 8px;
	margin-left: -8px;
}

.cart-icon {
	color: #515783;
	font-size: 24px;
	margin-right: 7px;
	float: left;
}

.clearfix:after {
	content: "";
	display: table;
	clear: both;
}
</style>

	<script type="text/javascript">

$(document).ready(function(){
	
	$(".shopping-cart").fadeOut("fast");
	 
	  $("#cart").on("click", function() {
	    $(".shopping-cart").fadeToggle( "fast");
	  });
	  
	})();
</script>


<%

%>

	<nav class="navbar navbar-inverse navbar-expand-xl navbar-dark">
	<div class="navbar-header d-flex col">
		<a class="navbar-brand" href="#"><i class="fas fa-pizza-slice"></i></i></i>
			Pizz<b>A</b></a>
		<button type="button" data-target="#navbarCollapse"
			data-toggle="collapse" class="navbar-toggle navbar-toggler ml-auto">
			<span class="navbar-toggler-icon"></span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
	</div>
	<!-- Collection of nav links, forms, and other content for toggling -->
	<div id="navbarCollapse"
		class="collapse navbar-collapse justify-content-start">
		<form class="navbar-form form-inline">
			<div class="input-group search-box">
				<input type="text" id="search" class="form-control"
					placeholder="Search here..."> <span
					class="input-group-addon"><i class="material-icons">&#xE8B6;</i></span>
			</div>
		</form>
		<ul class="nav navbar-nav navbar-right ml-auto">
			</tr>
			<%
				// la liste de tous les  roles existants
				Utilisateur u = (Utilisateur) session.getAttribute("user");
			

				if (u != null) {
					for (Iterator<Role> it = u.getRoles().iterator(); it.hasNext();) {
						Role role = it.next();
			%>




			<li class="nav-item"><a href="<%=role.getUrl()%>"
				class="nav-link"><i class="far fa-user"></i></i><span><%=role.getTexte()%></span></a></li>

			<%
				}
					System.out.println("type uti" + u.getType());
					String ch = "1";
					if (ch.equals(u.getType())) {
			%>

			<li><a href="#" id="cart"><i class="fa fa-shopping-cart"></i>
					Cart <span class="badge"><%
							ArrayList<Produit> panier = (ArrayList<Produit>) session
							.getAttribute("panierProduit");
					
					
				if(panier==null){ %>
					<%=0%>
					
					<% }
				else { %> 
					<%=panier.size()%>
<%}
				
				 %></span></a></li>



			<%
				}
				}
			%>



		</ul>
	</div>

	</nav>

	<!-- <div class="container">
  <div class="shopping-cart">
    <div class="shopping-cart-header">
      <i class="fa fa-shopping-cart cart-icon"></i><span class="badge"></span>
      <div class="shopping-cart-total">
        <span class="lighter-text">Total:</span>
        <span class="main-color-text"></span>
      </div>
    </div> 

    <ul class="shopping-cart-items">
      <li class="clearfix">
        <img src="" alt="item1" />
        <span class="item-name"></span>
        <span class="item-price"></span>
        <span class="item-quantity">Quantity: 01</span>
      </li>
    </ul>

    			<a href="#" class="button">Checkout</a>
  </div> 
</div>  -->

	<%
	String ch = "1";

		if (ch.equals(u.getType())) {

			ArrayList<Produit> panier = (ArrayList<Produit>) session
					.getAttribute("panierProduit");
			
			System.out.println("panier est" + panier);
			
			if (panier != null) {

	%>
	<div class="container">
		<div class="shopping-cart">
			<div class="shopping-cart-header">
				<i class="fa fa-shopping-cart cart-icon"></i><span class="badge"><%=panier.size()%></span>
				<div class="shopping-cart-total">
					<span class="lighter-text">Total:</span> <span
						class="main-color-text"></span>
				</div>
			</div>
			<!--end shopping-cart-header -->
			<%
				
						for (int i = 0; i < panier.size(); i++) {
							System.out.println("le panier " + panier);
			%>
			<ul class="shopping-cart-items">
				<li class="clearfix"><img src="<%=panier.get(i).getImage()%>"
					/> <span class="item-name"><%=panier.get(i).getDesignation()%></span>
					<span class="item-price"><%=panier.get(i).getPrix()%></span> 
			</ul>
			<%
				}
			%>
			<a
				href='CommandeAction?mode=Checkout'>Checkout</a>
		</div>
	</div>
	
	<%
		} else {
	%>



<div class="container">
  <div class="shopping-cart">
    <div class="shopping-cart-header">
      <i class="fa fa-shopping-cart cart-icon"></i><span class="badge">0</span>
      <div class="shopping-cart-total">
        <span class="lighter-text">Total:</span>
        <span class="main-color-text">0</span>
      </div>
    </div> 
  </div> 
</div> 


	<% }
			
		}
	%>


</body>
</body>
</html>