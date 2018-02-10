<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>

<%-- Import de la classe ArrayList --%>
<%@ page import="java.util.ArrayList"%>

<%-- Instantiation du Bean permettant l'acc�s � la base de donn�es --%>
<jsp:useBean id="compBD" class="biblio.ComposantBDUsager" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Projet Biblioth�que</title>
	<link href="styles/styles.css" media="all" rel="stylesheet" type="text/css">
</head>

<body>
	<h1>Mini application biblioth�que</h1>
	<hr/>

	<h2>Liste de tous les usagers :</h2>

	<%
	ArrayList usagers;
	Boolean listeVide = true;
	// Appel de la m�thode "listeTousEmployes"
	if (request.getParameter("nom-rechercher")==null){
		usagers = compBD.listeTousUsagers();
		if (usagers.size()!=0) listeVide=false;
	}
	else {
		String nom = request.getParameter("nom-rechercher");
		usagers = compBD.listeUsagers(nom);
		if (usagers.size()!=0) listeVide=false;
	}
	
	if(!listeVide) {
	%>
	<%-- Tableau HTML avec les titres de colonnes --%>
	<table border="1">
		<tr style="font-weight: bold">
			<td align="center">ID</td>
			<td align="center">Nom</td>
			<td align="center">Pr�nom</td>
			<td align="center">Statut</td>
			<td align="center">Email</td>
			<td align="center">Modifier</td>
		</tr>
	<%
		// On parcours la liste des usagers
		for(int i=0;i<usagers.size();i++) {
		// A chaque "tour" de la boucle, on r�cup�re les infos
		// sur un usag� (dans un tableau de 5 cha�nes de caract�res)
		// Cf. m�thode "listeTousUsagers" dans "ComposantBD"
		String[] usager = (String[])usagers.get(i);
	%>
		<tr>
			<td><%=usager[0]%></td>
			<td><%=usager[1]%></td>
			<td><%=usager[2]%></td>
			<td><%=usager[3]%></td>
			<td><%=usager[4]%></td>
			<td align="center"><a href="ModifierUsager.jsp?id=<%=usager[0]%>"><img src="images/building_edit.png" border=0/></a></td>
		</tr>
	<%
		}
	}
	%>
	</table>
	<p align="center"><a href="/Bibliotheque/">Retour...</a></p>

</body>
</html>