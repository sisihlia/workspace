package biblio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComposantBDLivre {

	/**
	 * Récupération de la liste complète des livres triée par titre
	 * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que de livres dans la base.
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeTousLivres() throws SQLException {

		ArrayList<String[]> livres = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from livre order by titre";
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			String[] livre = new String[5];
			livre[0] = rset.getString("id");
			livre[1] = rset.getString("isbn10");
			livre[2] = rset.getString("isbn13");
			livre[3] = rset.getString("titre");
			livre[4] = rset.getString("auteur");

			livres.add(livre);
		}
		rset.close();
		stmt.close();

		return livres;
	}

	/**
	 * Récupération de la liste des livres ayant un titre donné
	 * @param titre
	 * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que de livres dans la base.
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeLivres(String titre) throws SQLException {
		
		ArrayList<String[]> livres = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from livre where titre like '"+titre+"'";
		ResultSet rset = stmt.executeQuery(query);

		while(rset.next()){ 
			String[] livre = new String[5];
			livre[0] = rset.getString("id");
			livre[1] = rset.getString("isbn10");
			livre[2] = rset.getString("isbn13");
			livre[3] = rset.getString("titre");
			livre[4] = rset.getString("auteur");
			
			livres.add(livre);
		}
		
		rset.close();
		stmt.close();

		return livres;

	}

	/**
	 * Modifie les informations du livre ayant l'identifiant id
	 * @param id
	 * @param isbn10
	 * @param isbn13
	 * @param titre
	 * @param auteur
	 * @throws SQLException
	 */
	public static void modifierLivre(String id, String isbn10, String isbn13, String titre, String auteur)
			throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "update livre set isbn10 = '"+isbn10+"', isbn13 = '"+isbn13+"', titre = '"+titre+"', auteur = '"+auteur+"'where id='"+id+"'";
		stmt.executeUpdate(query);
		stmt.close();
	}

	/**
	 * Supprime un livre à partir de son n° d'identifiant
	 * @param id
	 * @throws SQLException
	 */
	public static void supprimerLivre(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement(); // pouquoi il y a les erreurs pour chaque fois on fais supprimation mais ça marche??
		String query = "delete from livre where id=" +id;
		stmt.executeUpdate(query);
	
		stmt.close();
	
	}

	/**
	 * Récupère les informations d'un livre à partir de son n° d'identifiant
	 * @param id
	 * @return un tableau de <code>String</code> contenant les informations d'un livre 
	 * @throws SQLException
	 */
	public static String[] getLivre(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from livre where id=" +id;
		ResultSet rset = stmt.executeQuery(query);
		
		String[] livre = new String[5];
		while(rset.next()){ 
		
			livre[0] = rset.getString("id");
			livre[1] = rset.getString("isbn10");
			livre[2] = rset.getString("isbn13");
			livre[3] = rset.getString("titre");
			livre[4] = rset.getString("auteur");
			
		}
		rset.close();
		stmt.close();
	
		
		return livre;
	}

	/**
	 * Retourne le nombre d'exemplaire d'un livre à partir de son numéro d'identifiant
	 * @param id
	 * @return le nombre d'exemplaires
	 * @throws SQLException
	 */
	public static int nbExemplaires(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select count(id_exemplaire) from exemplaire where id_livre=" +id; // count pour indiqué la quantité 
		ResultSet rset = stmt.executeQuery(query);
		
		int i =0;
		while(rset.next()){  // on doit utiliser while pour chaque id_livre
			i = rset.getInt(1);
		}
		rset.close();
		stmt.close();
		
		return i;
	}

	/**
	 * Récupération de la liste des identifiants des exemplaires d'un livre
	 * @param id : n° d'identifiant d'un livre
	 * @return un <code>ArrayList</code> contenant les n° d'identifiant des exemplaires
	 * @throws SQLException
	 */
	public static ArrayList<Integer> listeExemplaires(String id) throws SQLException {
		
		ArrayList<Integer> listExm = new ArrayList<Integer>();
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select id_exemplaire from exemplaire where id_livre=" +id;
		 ResultSet rset = stmt.executeQuery(query);
		
		while (rset.next()){
			String Exm = rset.getString("id_exemplaire");
		
			listExm.add(Integer.parseInt(Exm));
		}	
	
	
		rset.close();
		stmt.close();
		return listExm;
		//return new ArrayList<Integer>();
		
	}

	/**
	 * Ajoute un exemplaire
	 * @param id
	 */
	public static void ajouterExemplaire(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "insert into exemplaire values(nextval('exemplaire_id_exemplaire_seq'),'"+id+ "')"; //insert into exemplaire values(nextval('exemplaire_id_exemplaire_seq'),1)
		stmt.executeUpdate(query); 
		stmt.close();
		
	}

	/**
	 * Supprime un exemplaire
	 * @param id
	 */
	public static void supprimerExemplaire(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query1 = "delete from emprunt where id_exemplaire =" +id; 
		stmt.executeUpdate(query1);
		String query = "delete from exemplaire where id_exemplaire =" +id; 
		stmt.executeUpdate(query);
		stmt.close();
	}

	/**
	 * Détermine si un exemplaire est actuellement emprunté
	 * @param id
	 * @return "true" si l'exemplaire est emprunté, "false" sinon
	 */
	public static Boolean estEmprunte(int id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select date_retour from emprunt where id_exemplaire ='" +id+"'"; //select date_retour from emprunt where id_exemplaire =
		ResultSet rset = stmt.executeQuery(query);
		
		boolean emprunte = false;
		while(rset.next()){
			String a = rset.getString("date_retour");
			if(a == null) {
				emprunte = true;
			}
		}
		rset.next();
		stmt.close();
		return emprunte;
		//return false;
	}

	/**
	 * Détermine si un exemplaire est actuellement emprunté
	 * @param id
	 * @return "true" si l'exemplaire est emprunté
	 */
	public static Boolean aEteEmprunte(int id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select date_retour from emprunt where id_exemplaire ='" +id+"'"; //select date_retour from emprunt where id_exemplaire =
		ResultSet rset = stmt.executeQuery(query);
		
		boolean eteEmprunte = false;
		while(rset.next()){
			String a = rset.getString("date_retour");
			if(a != null) {
				eteEmprunte = true;
			}
		}
		rset.next();
		stmt.close();
		return eteEmprunte;
		//return false;
	}

	/**
	 * Récupération de la liste de titre des livres à partir du début du titre
	 * @param Un <code>String</code> correspond au début du titre du livre
	 * @return Un <code>ArrayList<String></code> contenant autant de <String> que de livres potentiels
	 */
	public static ArrayList<String> debutLivre(String debut) throws SQLException {
		ArrayList<String> listDebut = new ArrayList<String>();
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from livre where titre like '%"+debut+"%'"; //select * from livre where titre like '%le%'
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next()){ 
			String livre = rset.getString("titre");
			listDebut.add(livre);
		}
		
		rset.close();
		stmt.close();
		return listDebut;
		//return new ArrayList<String>();
	}

	/**
	 * Crée un livre	 * 
	 * @param isbn10
	 * @param isbn13
	 * @param titre
	 * @param auteur
	 * @return le n° du livre créé
	 */
	public static int creerLivre(String isbn10, String isbn13, String titre, String auteur) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "insert into livre values(nextval('livre_id_seq'),'" +isbn10+ "','" +isbn13+ "', '" +titre+ "', '"+auteur +"')"; //insert into livre values(nextval('livre_id_seq'), '111','333', 'cccl', 'didid')
		stmt.executeUpdate(query);// il faut changer executeQuery à executeUpdate pour converter ResultSet à int
		
		String query2 = "select currval('livre_id_seq') as valeur_courante_id_livre";
		ResultSet rset = stmt.executeQuery(query2);
		rset.next();
		int id = rset.getInt("valeur_courante_id_livre");
		
		rset.close();
		stmt.close();
		return id;
		//return -1;
		
		
	}
}
