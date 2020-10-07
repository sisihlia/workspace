<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=iso-8859-1" %>

<%-- Import de la classe ArrayList --%>
<%@ page import="java.util.ArrayList"%>

<%-- Instantiation du Bean permettant l'acc�s � la base de donn�es --%>
<jsp:useBean id="compBD" class="biblio.ComposantBDLivre" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>Projet Biblioth�que</title>
	<link href="styles/styles.css" media="all" rel="stylesheet" type="text/css">
</head>

<body>
	<h1>Mini application biblioth�que<h1>
	<hr/>

	<h2>Liste de tous les livres :</h2>

	<%
	ArrayList<String[]> livres;
	Boolean listeVide = true;
	// Appel de la m�thode "listeTousEmployes"
	if (request.getParameter("titre-rechercher")==null){
		livres = compBD.listeTousLivres();
		if (livres.size()!=0) listeVide=false;
	}
	else {
		String titre = request.getParameter("titre-rechercher");
		livres = compBD.listeLivres(titre);
		if (livres.size()!=0) listeVide=false;
	}
	
	if(!listeVide) {
	%>
	<%-- Tableau HTML avec les titres de colonnes --%>
	<table border="1">
		<tr style="font-weight: bold">
			<td align="center">ISBN-10</td>
			<td align="center">ISBN-13</td>
			<td align="center">Titre</td>
			<td align="center">Auteur</td>
			<td align="center">Modifier</td>
		</tr>
	<%
		// On parcours la liste des livres
		for(int i=0;i<livres.size();i++) {
		// A chaque "tour" de la boucle, on r�cup�re les infos
		// sur un livre (dans un tableau de 3 cha�nes de caract�res)
		// Cf. m�thode "listeTousLivres" dans "ComposantBD"
		String[] livre = (String[])livres.get(i);
	%>
		<tr>
			<td><%=livre[1]==null ? "" : livre[1] %></td>
			<td><%=livre[2]==null ? "" : livre[2] %></td>
			<td><%=livre[3]%></td>
			<td><%=livre[4]%></td>
			<td align="center"><a href="ModifierLivre.jsp?id=<%=livre[0]%>"><img src="images/building_edit.png" border=0/></a></td>
		</tr>
	<%
		}
	}
	%>
	</table>
	<p align="center"><a href="/Bibliotheque/">Retour...</a></p>

</body>
</html>