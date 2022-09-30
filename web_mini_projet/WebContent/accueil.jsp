<%@page language="java" import="domaine.*,java.util.ArrayList,java.util.Iterator"%>

<%@include file="entete.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Accueil</title>
</head>
<body bgcolor="#11ccff">


<%

/*
if(u.getRoles()==null)
{
	System.out.println("null");
}
else
{
	System.out.println("plein:");
}
*/

if (u!=null)
{
	// Gestion des rôles : Afficher un menu dynamique selon les droits de l'utilisateurs
	out.println("<ul>");
	for (Iterator <Role> it = u.getRoles().iterator(); it.hasNext();)
	{
		Role role =it.next();
		out.println("<li> <a href ='"+role.getUrl()+"' >"+role.getTexte()+"</a><br></li>");
	}
	out.println("<ul>");
}
		
%>

	
</body>
</html>