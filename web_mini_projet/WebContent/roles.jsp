<%@page language="java" import="java.util.ArrayList , domaine.Utilisateur , domaine.Role"%>

<%@include file="entete.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Liste des r�les</title>
</head>
<body bgcolor="#11ccff">

	<form >
		Liste des r�les
		<hr>
			<table border ="1">
			<tr>
				<th>Url:</th>
				<th>Description:</th>
				<th colspan="2">Action:</th>
			</tr>
		<%
			ArrayList roles = (ArrayList) request.getAttribute("roles");
			if (roles != null) {
				
				for (int i = 0; i < roles.size(); i++) 
				{
					out.println("<tr>");
					out.println("<td> " + ((Role) roles.get(i)).getUrl() + "</td>");
					out.println("<td> " + ((Role) roles.get(i)).getTexte() + "</td>");
					out.print("<td>  <a href ='RoleEdition?id="+((Role) roles.get(i)).getId()+"'>Modifier</a> </td>");
					out.println("<td>  <a href ='RoleSuppression?id="+((Role) roles.get(i)).getId()+"'  onclick='return confirm(\"Voulez vous vraiment supprimer ce r�le ?\")'      >Supprimer</a> </td>");
					out.println("</tr>");
				}
				
			}
		%>
		
			
		</table>
	</form>
	<hr>
	<a href ="RoleEdition">Ajouter un nouveau r�le</a>
</body>
</html>