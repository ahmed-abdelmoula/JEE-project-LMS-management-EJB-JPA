<%@page import="java.util.Date"%>
<%@page import="domaine.Commande"%>
<%@page import="domaine.Restaurant"%>
<%@page import="domaine.Utilisateur"%>
<%@page language="java"
	import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


</head>
<body bgcolor="#11ccff">

<% 

Utilisateur user=(Utilisateur)session.getAttribute("user") ;
System.out.println("le panier est "+ session
		.getAttribute("panierProduit") );

float som=0;

if (((ArrayList<Produit>) session.getAttribute("panierProduit"))!=null){

for(int j=0;j<((ArrayList<Produit>) session.getAttribute("panierProduit")).size();j++)
{ 
	som=som+((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getPrix();
}


}


if (user!=null)
{%>


<%@include file="navbarPrincipal.jsp" %> 

<%} else {%> 
<%@include file="navbar.jsp" %>

<%} %>
  <script>
  $(document).ready(function(){
    $( "#datepicker" ).datepicker();
  } );
  </script>
<%
// gestion de la saisie de  l'utilisateur en cours
			
			Date d;
			int id =0;
		    
		    
		    ArrayList<Restaurant> listRestaurant = (ArrayList<Restaurant>) session.getAttribute("restaurant");
		   Restaurant r;
			Commande u = (Commande)request.getAttribute("com");
			//int idUtilisateur=(int) session.getAttribute("idUtilisateur");
			if (u!=null)
			{
				System.out.println("eee");
			 id=u.getId();
		     d=u.getDate();
		     som=u.getTotal();
			 r=u.getRestaurant();
			}else{
				// Correction des  valleurs nulles
			
			}
			
		System.out.println(" !La"+listRestaurant);
			
		%>
<form method="POST" action ="CommandeAction">

<table>
<tr>
<td><input type="hidden" value="<%=id%>" name="id"   /></td>
</tr>
<tr>
<td>Date :</td>
<td>
<input type="text"  name="d" value="" id="datepicker">

</td><tr/>
<tr>
<td>Total :</td>
<td><input type="text" value="<%=som %>" name="total"  /></td>
</tr>
<tr>
<td>Restaurant :</td>
	<td><select name="RestaurantListe" id="rest-select">

						<%
							if (listRestaurant!=null) {
							


								
								for (int x = 0; x < listRestaurant.size(); x++) {
						%>

						<option value="<%=listRestaurant.get(x).getId()%>"><%=listRestaurant.get(x).getAdresse()%></option>
						<%
							}
								
								
								
								
							} else {
						%>

						<option></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
			
				<table border="1">

		<tr>
			<th>Designation</th>
			<th>Description</th>
			<th>Prix</th>
			<th>Categorie</th>
			<th>Image</th>
			
			<th>Action</th>
		</tr>
<%
if (((ArrayList<Produit>) session.getAttribute("panierProduit"))!=null){
	
for(int j=0;j<((ArrayList<Produit>) session.getAttribute("panierProduit")).size();j++)
{ %>
	
	<tr>
			<td><%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getDesignation()%></td>
			<td><%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getDescription()%></td>
			<td><%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getPrix()%></td>
			<td><%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getCategorie().getNomCategorie()%></td>
			<td><img src="<%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getImage()%>" height="200" width="200">
			</td>
			<td>
				<a	href='FicheProduitAction?mode=SuppressionPanier&id=<%=((ArrayList<Produit>) session.getAttribute("panierProduit")).get(j).getId()%>'>Supprimer</a> 
				</td>
				</tr>
	<%
}}

%>



</table>
</tr>
<tr><td>
<input type="submit" value="Envoyer" /></td>
<td><input type="reset" value="Annuler" /></td></tr>
</table>
</form>

</body>
</html>