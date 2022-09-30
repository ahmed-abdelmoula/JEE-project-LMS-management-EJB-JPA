<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@page import="domaine.Commande"%>
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
		Liste des Commande Du Restaurant 
		<hr>
			<table border ="1">
			<tr>
			<th> Id Commade </th>
				<th>Date:</th>
			
				<th> Utilisateur </th>
			<th>Total:</th>
				<th colspan="3">Actions:</th>
				
			</tr>
		<%
			ArrayList Commandes = (ArrayList) session.getAttribute("listeCommande");
	
	
		if (Commandes != null) {
		
				for (int i = 0; i <Commandes.size(); i++) 
				{	System.out.println("eeeee2");	
					out.println("<tr>");
					out.println("<td> " +((Commande)Commandes.get(i)).getId()+ "</td>");
					out.println("<td> " +((Commande)Commandes.get(i)).getDate()+ "</td>");
					out.println("<td> " + ((Commande)Commandes.get(i)).getUtilisateur().getPrenom() +  "--"+((Commande)Commandes.get(i)).getUtilisateur().getNom() +"</td>");
					out.println("<td> " + ((Commande)Commandes.get(i)).getTotal() + "</td>");
				
					
				
					out.print("<td>  <a href ='CommandeAction?id="+((Commande)Commandes.get(i)).getId()+"&mode=Edition"+"'>Modifier</a> </td>");
					out.println("<td>  <a href ='CommandeAction?id="+((Commande)Commandes.get(i)).getId()+"&mode=Suppression"+"'  onclick='return confirm(\"Voulez vous vraiment supprimer cet utilisateur ?\")'      >Supprimer</a> </td>");
					
					out.println("<td>  <a href ='FicheProduitAction?id="+((Commande)Commandes.get(i)).getId()+"&mode=ProduitInCommande'>Liste Produit dans Commande</a> </td>");

					out.println("</tr>");
				}
				
			}
		%>