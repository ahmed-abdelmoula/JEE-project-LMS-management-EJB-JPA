<%@page language="java" import="java.util.ArrayList,java.util.Iterator , domaine.Utilisateur , domaine.Role"%>

<%@include file="entete.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Affectaton des rôles à un utilisateur</title>
</head>
<body bgcolor="#11ccff">

<% 
// récupérer l'utilisateur en question
Utilisateur user = (Utilisateur)request.getAttribute("Utilisateur");
%>
<hr>
Utilisateur :<%=user.getNom() %>::<%=user.getPrenom() %>
<hr>

<form action=UtilisateurRolesAffectation method="POST">
		Liste des rôles
		<hr>
		<input type="hidden" name="utilisateur_id"  value="<%=user.getId()%>"/>
			<table border ="1">
			<tr>
				<th>Url:</th>
				<th>Description:</th>
				<th colspan="1">Attribué:</th>
			</tr>
		<%
		   // la liste de tous les  roles existants
			ArrayList roles = (ArrayList) request.getAttribute("roles");
			if (roles != null) {
				
				for (int i = 0; i < roles.size(); i++) 
				{
					out.println("<tr>");
					out.println("<td> " + ((Role) roles.get(i)).getUrl() + "</td>");
					out.println("<td> " + ((Role) roles.get(i)).getTexte() + "</td>");
					// vérifier si le role est déjà affecté à l'utilisateur
					boolean trouve =false;
					for (Iterator <Role> userRoles = user.getRoles().iterator(); userRoles.hasNext()&& !trouve;)
						{
							Role role =userRoles.next();
							if (role.getId()==((Role) roles.get(i)).getId())
							{
								trouve=true;
							}
						}
				

					out.print("<td 	align='center'><INPUT type='checkbox' name='liens' value='"+((Role) roles.get(i)).getId()+"' "+(trouve?"checked":"")+"> </td>"     ); 
					
					out.println("</tr>");
				}
				
			}
		%>
		
		<tr>
				<td align="center" colspan="3"><input type="submit" value="Valider" />
					<input type="reset" value="Annuler" /></td>
			</tr>	
		</table>
	</form>
	<hr>
	<a href ="RoleEdition">Ajouter un nouveau rôle</a>
</body>
</html>