<%@page language="java" import="domaine.Utilisateur"%>
<%
Utilisateur u= (Utilisateur)session.getAttribute("user");
if (u==null)
{
	System.out.println("nulllllll");
request.getRequestDispatcher("connexion.jsp").forward(request, response);
}
else
{
	System.out.println("non null");


%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title></title>
</head>
<body bgcolor="#F6CECE">
   
   <table border ="1" width="100%">
	<tr><td  bgcolor ="#AABDBA">BienVenue    :[<%=u.getNom()%>][<%=u.getPrenom()%>]</td>
		<td bgcolor ="#BDBDBD"><a  href ="accueil.jsp"> Accueil</a></td>
		<td bgcolor ="#BDBDBD"><a  href ="Deconnexion" onclick="return confirm('Voulez vous vraiment quitter ?')"> Déconnecter</a></td>
	</tr>
	</table>
	
</body>
</html>
<%
}
%>