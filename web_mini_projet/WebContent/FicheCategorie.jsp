<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page language="java"
	import="domaine.Produit,domaine.Categorie,java.util.ArrayList"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
Categorie c = (Categorie) request.getAttribute("categorie");
String nomCategorie;
int idCategore = 0;

Categorie categ = null;
if (c != null) {
	nomCategorie =c.getNomCategorie();
	
	idCategore = c.getId();
	
} else {
	nomCategorie = "";

}


%>
<form action="FicheCategorieAction" method="POST">
		<table>
			<tr>
				<td><input type="hidden" name="id" value="<%=idCategore%>" /></td>
			</tr>
			<tr>
				<td>Designation:</td>
				<td><input type="text" name="nomCateg" value="<%=nomCategorie%>" /></td>
			</tr>
			<tr>
				<td>Image</td>
				<td><input type="file" name="imageCateg" /></td>
		</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="ok" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
	</form>
</body>
</html>