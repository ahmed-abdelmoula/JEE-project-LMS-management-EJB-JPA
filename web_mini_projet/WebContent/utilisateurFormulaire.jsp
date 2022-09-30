<%@page language="java" import="domaine.Utilisateur , java.util.ArrayList"%>

<%@include file="entete.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Edition Utilisateur</title>
</head>


 
<body bgcolor="#11ccff">

	<form action="UtilisateurFormulaireAction" method="POST">
		Profil Utilisateur
		<hr>
		
		<%
			ArrayList erreurs = (ArrayList) request.getAttribute("erreurs");
			if (erreurs != null) {
				out.println("<ul>");
				for (int i = 0; i < erreurs.size(); i++) {
					out.println("<li> " + (String) erreurs.get(i) + "</li>");
				}
				out.println("</ul>");
			}
		%>

		<%
			Utilisateur user  = (Utilisateur) request.getAttribute("Utilisateur");
		    String nom, prenom, login , password, type;
		    String id;
		    if (user!=null)
		    {
		    	nom =user.getNom();
		    	prenom =user.getPrenom();
		    	login =user.getLogin();
		    	password =user.getPassword();
		    	id = String.valueOf(user.getId());
		    	type =user.getType();
		    }
		    else
		    {
		    	nom ="";
		    	prenom ="";
		    	login ="";
		    	password ="";
		    	id="";
		    	type ="";
		    }
			
		%>
		
		<input  type="hidden" name="id"  value="<%=id%>"/>
		<table>
			<tr>
				<td>Nom:</td>
				<td><input type="text" name="nom"  value="<%=nom%>"/></td>
			</tr>
			<tr>
				<td>Prénom:</td>
				<td><input type="text" name="prenom"  value="<%=prenom%>"/></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login"  value="<%=login%>"/></td>
			</tr>
			<tr>
				<td>Mot de passe:</td>
				<td><input type="text" name="password"  value="<%=password%>"/></td>
			</tr>
					
			<tr>
				<td>Type:</td>
				<td>		<select name="type"> 
								<option  value="user">Simple utilisateur</option>
								<option  value="admin">administrateur</option>
		
						</select> 
				</td>
			</tr>
					
			<tr>
				<td align="center" colspan="2"><input type="submit" value="Valider" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
	</form>

</body>
</html>