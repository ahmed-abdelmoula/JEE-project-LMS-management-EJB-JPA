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
		ArrayList<Categorie> ltrait = (ArrayList<Categorie>)session.getAttribute("listOfCategorie");
	%>
	<form action="FicheCategorieAction" method="GET">
		<input type="hidden" name="mode" value="Recherche" /> <input
			type="text" name="valCher" /> <input type="submit"
			Value="Rechercher" />
	</form>

	<table border="1">

		<tr>
			<th>ID</th>
			<th>NomCategorie</th>
			<th>Action</th>
		</tr>
		<%
			if (ltrait.size() != 0 || ltrait != null) {
				for (int i = 0; i < ltrait.size(); i++) {
		%>
		<tr>
			<td><%=ltrait.get(i).getId()%></td>
			<td><%=ltrait.get(i).getNomCategorie()%></td>
					<td><a
				href='FicheCategorieAction?mode=Edition&id=<%=ltrait.get(i).getId()%>'>Editer</a>
			<a
				href='FicheCategorieAction?mode=Suppression&id=<%=ltrait.get(i).getId()%>'>Supprimer</a>
				<a
				href='FicheCategorieAction?mode=ProduitCorresp&id=<%=ltrait.get(i).getId()%>'>	Produit Correspondant</a>
				
			</td>
		</tr>
		<%
			}
			}
		%>
		<tr>
			<td colspan=5><center>
					<a href="FicheCategorie.jsp">Ajouter </a>
				</center></td>

		</tr>
	</table>
</body>
</html>