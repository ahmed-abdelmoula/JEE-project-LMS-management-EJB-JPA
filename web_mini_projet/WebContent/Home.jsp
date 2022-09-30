<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page language="java"
	import="domaine.Produit,domaine.Utilisateur,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 

Utilisateur user=(Utilisateur)session.getAttribute("user") ;
System.out.println("le panier est "+ session
		.getAttribute("panierProduit") );


if (user!=null)
{%>


<%@include file="navbarPrincipal.jsp" %> 

<%} else {%> 
<%@include file="navbar.jsp" %>

<%} %>
<%@include file="header.jsp" %>

 

</body>
</html>