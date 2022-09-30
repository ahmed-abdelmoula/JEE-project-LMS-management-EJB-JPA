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
		String idcateg = request.getParameter("value");
		System.out.println("testetstetz" + idcateg);

		ArrayList<Categorie> listCategorie = (ArrayList<Categorie>) session
				.getAttribute("listOfCategorie");

		Produit p = (Produit) request.getAttribute("produit");
		String description, designiation, image, prix;
		int idProduit = 0;

		Categorie categ = null;
		if (p != null) {
			description = p.getDescription();
			image = p.getImage();
			designiation = p.getDesignation();
			prix = String.valueOf(p.getPrix());
			idProduit = p.getId();
			categ = p.getCategorie();
		} else {
			description = "";
			designiation = "";
			image = "";
			prix = "";
			categ = null;
		}
	%>

	<form action="FicheProduitAction" method="POST">
		<table>
			<tr>
				<td><input type="hidden" name="id" value="<%=idProduit%>" /></td>
			</tr>
			<tr>
				<td>Designation:</td>
				<td><input type="text" name="design" value="<%=designiation%>" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><input type="text" name="description"
					value="<%=description%>" /></td>
			</tr>
			<tr>
				<td>Prix:</td>
				<td><input type="text" name="prix" value="<%=prix%>" /></td>
			</tr>
			<tr>
				<td>Categorie:</td>
				<td><select name="categorieListe" id="categ-select">
						<%
							if (listCategorie != null) {

								

									for (int x = 0; x < listCategorie.size(); x++) {
						%>
						<option value="<%=listCategorie.get(x).getId()%>" <%
								 System.out.println("list"+listCategorie.get(x).getId());
						 System.out.println("l id"+idcateg);
						 
						if ((idcateg!=null) && (Integer.valueOf(idcateg)==(listCategorie.get(x).getId()))){ %>
							selected
							<%
						}
							%> ><%=listCategorie.get(x).getNomCategorie()%></option>
						<%
							
									}

								

							}

							else {
						%>
						<option></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>				
			
				<td>Image</td>
				<td><input type="file" name="image" /></td>
					
				
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="ok" />
					<input type="reset" value="Annuler" /></td>
			</tr>

		</table>
	</form>

</body>
</html>