<%@page language="java" import="java.util.ArrayList, domaine.Utilisateur"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="navbar.jsp" %>

<body bgcolor="#11ccff">
<div class="container">
<div class="row">

		<div class="col-md-10">
		
		Veuillez saisir vos paramètres de connexion
		<hr>
		<%
	      //gestion des erreurs
			ArrayList erreurs = (ArrayList) request.getAttribute("err");
			if (erreurs != null) 
			{
				out.println("<ul>");
				for (int i = 0; i < erreurs.size(); i++) {
					out.println("<li> " + (String) erreurs.get(i) + "</li>");
				}
				out.println("</ul>");
			}
			// gestion des valeurs saisies par l'utilisateur
			
			String login =null;
			String password =null;
			
			Utilisateur u = (Utilisateur)request.getAttribute("user");
			if (u!=null)
			{
			 login = u.getLogin();
			 password = u.getPassword();
			
			}
			
			// Traitement des valleurs nulles
			if (login==null)login="";
			if (password==null)password="";
		
		%>
		
		<form action="ConnexionAction" method="POST">
		
			<table>
			<tr>
				<td><center><h5>Login:</h5></center></td>
				<td><input type="text" name="login" value ="<%=login%>"/></td>
			</tr>
			<tr>
				<td><h5><center>Mot de passe:</center></h5></td>
				<td><input type="password" name="password" value ="<%=password%>"/><br></td>
			</tr>
			
			<tr>
				<td align="center" colspan="2"><input type="submit" value="ok" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
		
	</form>
</div></div>
<div class="col-md-12">

<div class="col-md-2"><img src="image/bg_2.png"/><br/><br/> </div>
</div>
</body>
</html>