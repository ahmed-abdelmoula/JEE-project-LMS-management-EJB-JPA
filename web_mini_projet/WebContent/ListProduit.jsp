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
		ArrayList<Produit> ltrait = (ArrayList<Produit>)session.getAttribute("listOfProduits");
	System.out.println("list produit"+ltrait);
	
	
	
	%>
	<form action="FicheUserAction" method="GET">
		<input type="hidden" name="mode" value="Recherche" /> <input
			type="text" name="valCher" /> <input type="submit"
			Value="Rechercher" />
	</form>

	<table border="1">

		<tr>
			<th>ID</th>
			<th>Designation</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Categorie</th>
			<th>Image</th>
			
			<th>Action</th>
		</tr>
		<%
			if (ltrait.size() != 0 || ltrait != null) {
				for (int i = 0; i < ltrait.size(); i++) {
					System.out.println("list produit"+ltrait);

		%>
		<tr>
			<td><%=ltrait.get(i).getId()%></td>
			<td><%=ltrait.get(i).getDesignation()%></td>
			<td><%=ltrait.get(i).getDescription()%></td>
			<td><%=ltrait.get(i).getPrix()%></td>
			<td><%=ltrait.get(i).getCategorie().getNomCategorie()%></td>
			<td><img src="<%=ltrait.get(i).getImage()%>" height="200" width="200">
			</td>
			<td><a
				href='FicheProduitAction?mode=Edition&id=<%=ltrait.get(i).getId()%>'>Editer</a>
				<a
				href='FicheProduitAction?mode=Suppression&id=<%=ltrait.get(i).getId()%>'>Supprimer</a>
				
			</td>
		</tr>
		<%
			}
			}
		%>
		<tr>
			<td colspan=5><center>
					<a href="FicheProduit.jsp">Ajouter </a>
				</center></td>

		</tr>
	</table>

</body>
</html>