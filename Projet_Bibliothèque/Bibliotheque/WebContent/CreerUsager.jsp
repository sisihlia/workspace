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
	<h1>Mini application biblioth�que<h1>
	<hr/>
	<h2>Cr�ation d'une fiche</h2>
	<form name="form-modifier" id="form-modifier" action="CreerUsager.jsp" method="POST">
	<table border=0>
	<input type="hidden" name="id" value="" disabled>
	<tr><td>Nom</td><td><input type="text" name="nom" size="40" /></td></tr>
	<tr><td>Pr�nom</td><td><input type="text" name="prenom" size="40" value=""/></td></tr>
	<tr><td>Statut</td><td><select name="statut"><option value="Etudiant">Etudiant</option><option value="Enseignant" >Enseignant</option></select></td></tr>
	<tr><td>Email</td><td><input type="text" name="email" size="40" value=""/></td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td><td><input type="submit" name="submit-creer" id="submit" value="Ajouter"></td></tr>
	</table>
	</form>
		
	<% if (request.getParameter("submit-creer")!=null){
		String nom = 	request.getParameter("nom").toUpperCase();
		String prenom =	request.getParameter("prenom");
		String statut =	request.getParameter("statut");
		String email =	request.getParameter("email");
			
		int id = compBD.creerUsager(nom, prenom, statut, email);
		if (id!=0) {
	%>
		<p style="font-weight:bold; color:green">Le compte n�<%=id%> a �t� cr�� pour <%=nom%>. Vous pouvez consulter sa <a href="ModifierUsager.jsp?id=<%=id%>">fiche</a>.</p>
	<% 
		}
		else {
	%>
		<p style="font-weight:bold; color:red">La cr�ation de compte a �chou�e</p>
	<% }} %>
	
	<p align="center"><a href="/Bibliotheque/">Retour...</a></p>

</body>
</html>

