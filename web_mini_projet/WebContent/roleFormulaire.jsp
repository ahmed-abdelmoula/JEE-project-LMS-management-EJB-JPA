<%@page language="java" import="domaine.Utilisateur , domaine.Role, java.util.ArrayList"%>

<%@include file="entete.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Edition Role</title>
</head>


 
<body bgcolor="#11ccff">

	<form action="RoleFormulaireAction" method="POST">
		Profil Role
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
			Role role  = (Role) request.getAttribute("role");
		    String url , texte;
		    String id;
		    if (role!=null)
		    {
		    	url =role.getUrl();
		    	texte =role.getTexte();
		    	
		    	id = String.valueOf(role.getId());
		    }
		    else
		    {
		    	url ="";
		    	texte ="";
		    	
		    	id="";
		    }
			
		%>
		
		<input  type="hidden" name="id"  value="<%=id%>"/>
		<table>
			<tr>
				<td>Url:</td>
				<td><input type="text" name="url"  value="<%=url%>"/></td>
			</tr>
			
			<tr>
				<td>Texte:</td>
				<td><input type="text" name="texte"  value="<%=texte%>"/></td>
			</tr>
			
					
			<tr>
				<td align="center" colspan="2"><input type="submit" value="Valider" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
	</form>

</body>
</html>