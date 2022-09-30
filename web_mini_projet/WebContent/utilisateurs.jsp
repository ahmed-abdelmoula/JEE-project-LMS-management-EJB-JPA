<%@page import="domaine.Role"%>
<%@page language="java" import="java.util.ArrayList , domaine.Utilisateur,domaine.Role"%>

<%@include file="entete.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Liste des utilisateurs</title>
</head>
<body bgcolor="#11ccff">

	<form action="ConnexionAction" method="POST">
		Liste des utilisateurs
		<hr>
			<table border ="1">
			<tr>
				<th>Nom:</th>
				<th>Prenom:</th>
				<th>Login:</th>
				<th>Mot de passe:</th>
				<th>Type:</th>
				<th colspan="3">Action:</th>
			</tr>
		<%
			ArrayList<Utilisateur> users = (ArrayList) request.getAttribute("users");
		
			if (users != null) {
				
				for (int i = 0; i < users.size(); i++) 
				{
					out.println("<tr>");
					out.println("<td> " + ((Utilisateur) users.get(i)).getNom() + "</td>");
					out.println("<td> " + ((Utilisateur) users.get(i)).getPrenom() + "</td>");
					out.println("<td> " + ((Utilisateur) users.get(i)).getLogin() + "</td>");
					out.println("<td> " + ((Utilisateur) users.get(i)).getPassword() + "</td>");
					out.println("<td> " + ((Utilisateur) users.get(i)).getType() + "</td>");
				
					out.print("<td>  <a href ='UtilisateurEdition?id="+((Utilisateur) users.get(i)).getId()+"'>Modifier</a> </td>");
					out.println("<td>  <a href ='UtilisateurSuppression?id="+((Utilisateur) users.get(i)).getId()+"'  onclick='return confirm(\"Voulez vous vraiment supprimer cet utilisateur ?\")'      >Supprimer</a> </td>");
					out.print("<td>  <a href ='UtilisateurRolesConsultation?id="+((Utilisateur) users.get(i)).getId()+"'>Gérer rôles</a> </td>");

					out.println("</tr>");
				}
				
			}
		%>
		
			
		</table>
	</form>

</body>
</html>