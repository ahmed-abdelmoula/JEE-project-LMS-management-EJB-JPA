<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="domaine.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="ListUserAction">
<table border="0">
<tr><td>Chercher:</td><td><input type="text" name="nom"/></td><td><input type="submit" value="Chercher" name="btnRechrcheh"/></td></tr>
</table>

</form>
</br>
		Liste des Restaurant
		<hr>
			<table border ="1">
			<tr>
				
				<th>ID:</th>
				<th>Adresse:</th>
				<th>Tel:</th>
		
				<th colspan="3">Actions:</th>
				
			</tr>
		<%
			ArrayList Restaurants = (ArrayList) session.getAttribute("listeResto");
		System.out.println(Restaurants);
	
		if (Restaurants != null) {
		
				for (int i = 0; i <Restaurants.size(); i++) 
				{	System.out.println("eeeee2");	
					out.println("<tr>");
					
					out.println("<td> " +((Restaurant) Restaurants.get(i)).getId()+ "</td>");
					out.println("<td> " + ((Restaurant) Restaurants.get(i)).getAdresse() + "</td>");
					out.println("<td> " + ((Restaurant) Restaurants.get(i)).getTel() + "</td>");

					out.print("<td>  <a href ='RestaurantAction?id="+((Restaurant) Restaurants.get(i)).getId()+"&mode=Edition"+"'>Modifier</a> </td>");
					out.println("<td>  <a href ='RestaurantAction?id="+((Restaurant) Restaurants.get(i)).getId()+"&mode=Suppression"+"'  onclick='return confirm(\"Voulez vous vraiment supprimer cet utilisateur ?\")'      >Supprimer</a> </td>");
					out.print("<td>  <a href ='RestaurantAction?id="+((Restaurant) Restaurants.get(i)).getId()+"&mode=Commande"+"'>ListeCommande</a> </td>");
					
					out.println("</tr>");
				}
				
			}
		%>
		
			
		</table>

<hr>
<a href ="FicheRestaurant.jsp">Ajouter</a>
</body>
</html>