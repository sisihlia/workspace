package biblio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class ComposantBDUsager {

	/**
	 * Récupération de la liste complète des employés triée par nom
	 * 
	 * @return un <code>ArrayList<String[]></code> contenant autant de tableaux de String (avec id, nom, prenom, statut, email) que d'employés
	 *         dans la base de données
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeTousUsagers() throws SQLException {
		
		ArrayList<String[]> usagers = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from usager order by id_usager";
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			String[] usager = new String[5];
			usager[0] = rset.getString("id_usager");
			usager[1] = rset.getString("nom");
			usager[2] = rset.getString("prenom");
			usager[3] = rset.getString("statut");
			usager[4] = rset.getString("email");

			usagers.add(usager);
		}
		rset.close();
		stmt.close();

		return usagers;
		
	}

	/**
	 * Récupération de la liste complète des employés ayant un nom donné
	 * 
	 * @param nom
	 * @return un <code>ArrayList<String[]></code> contenant autant de tableaux de String (avec id, nom, prenom, statut, email) que d'employés
	 *         ayant le nom passé en paramètre
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeUsagers(String nom) throws SQLException {
		
		ArrayList<String[]> usagers = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from usager where nom ='"+nom+"'";
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			String[] usager = new String[5];
			usager[0] = rset.getString("id_usager");
			usager[1] = rset.getString("nom");
			usager[2] = rset.getString("prenom");
			usager[3] = rset.getString("statut");
			usager[4] = rset.getString("email");

			usagers.add(usager);
		}
		rset.close();
		stmt.close();

		return usagers;
		//return new ArrayList<String[]>();
	}

	/**
	 * Récupération des informations sur un usager à partir de son ID
	 * 
	 * @return un tableau de <code>String</code> contenant l'id, le nom, le prénom, le statut et l'email de l'usager
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static String[] getUsager(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from usager where id_usager='"+id+"'";
		ResultSet rset = stmt.executeQuery(query);
		
		String[] usager = new String[5];
		while(rset.next()){
			usager[0] = rset.getString("id_usager");
			usager[1] = rset.getString("nom");
			usager[2] = rset.getString("prenom");
			usager[3] = rset.getString("statut");
			usager[4] = rset.getString("email");
		}
		rset.close();
		stmt.close();
	
		
		return usager;
		
	}

	/**
	 * Récupération de la liste de noms des usagers (ayant un nom DISTINCT) à partir du début du nom
	 * 
	 * @param Un <code>String</code> correspondand au début du nom de l'usager
	 * @return Un <code>ArrayList<String></code> contenant autant de <String> que d'usager commençant par la chaine de caractère donnée
	 */
	public static ArrayList<String> debutUsager(String debut) throws SQLException { // je sais paa pourquoi pas marcher juste pour rechercher
		
		ArrayList<String> listUsager = new ArrayList<String>();
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from usager where nom like '%"+debut+"%'"; //select * from livre where titre like '%le%'
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next()){ 
			String usager = rset.getString("nom");
			listUsager.add(usager);
		}
		
		rset.close();
		stmt.close();
		return listUsager;
		//return new ArrayList<String>();
	}

	/**
	 * Crée un usager
	 * 
	 * @param nom
	 * @param prenom
	 * @param statut (deux valeurs possibles "Etudiant" et "Enseignant")
	 * @param email
	 * @return le n° de l'usager créé
	 */
	public static int creerUsager(String nom, String prenom, String statut, String email) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "insert into usager values(nextval('usager_id_usager_seq'),'" +nom+ "','" +prenom+ "', '" +email+ "', '"+statut+"')"; //insert into usager values(nextval('usager_id_usager_seq'),'yuniyarti','sisihlia','etudiant','sisihlia.yuniyarti@telecom-bretagne.eu')
		stmt.executeUpdate(query);
		
		String query2 = "select currval('usager_id_usager_seq') as valeur_courante_id_usager";
		ResultSet rset = stmt.executeQuery(query2);
		rset.next();
		int id = rset.getInt("valeur_courante_id_usager");
		
		rset.close();
		stmt.close();
		return id;
		//return -1;
	}

	/**
	 * Modifie les informations d'un usager
	 * 
	 * @param id identifiant de l'utilisateur
	 * @param nom
	 * @param prenom
	 * @param statut (deux valeurs possibles "Etudiant" et "Enseignant")
	 * @param email
	 */
	public static void modifierUsager(String id, String nom, String prenom, String statut, String email) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "update usager set nom = '"+nom+"', prenom = '"+prenom+"', statut = '"+statut+"', email= '"+email+"'where id_usager= '"+id+"'"; //update usager set nom = 'abdulah', prenom = 'hasaan', statut = 'etudiant', email= 'hasaan.abdullah@telecom-bretagne.com' where id_usager=3;
		stmt.executeUpdate(query);
		stmt.close();
	}

	/**
	 * Supprime un usager
	 * 
	 * @param id identifiant de l'utilisateur
	 */
	public static void supprimerUsager(String id) throws SQLException {
		
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "delete from usager where id_usager =" +id; 
		stmt.executeUpdate(query);
		stmt.close();
	}
}
