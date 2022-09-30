<%@page import="domaine.Restaurant"%>
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
// gestion de la saisie de  l'utilisateur en cours
			
			String adresse,tel;
			int id =0;
		
			Restaurant u = (Restaurant)request.getAttribute("res");
			if (u!=null)
			{
				System.out.println("eee");
			 id=u.getId();
			 adresse=u.getAdresse();
			 tel=u.getTel();
			 
			}else{
				// Correction des  valleurs nulles
				 adresse ="";
			tel="";
			}
			
		
			
		%>
<form method="POST" action ="RestaurantAction">

<table>
<tr>
<td><input type="hidden" value="<%=id%>" name="id"   /></td>
<td>Adresse :</td>
<td><input type="text" value="<%=adresse%>" name="adresse" /></td>
<tr/>
<tr>
<td>Tel :</td>
<td><input type="text" value="<%=tel%>" name="tel" /></td>
</tr>




</table>
<input type="submit" value="Envoyer" />
<input type="reset" value="Annuler" />
</form>
</html>