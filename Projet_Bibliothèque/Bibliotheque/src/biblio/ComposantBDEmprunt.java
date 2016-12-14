package biblio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComposantBDEmprunt {

	/**
	 * Récupération de la liste complète des emprunts en fonction de leur statut 
	 * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que d'emprunts dans la base.
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeEmprunts(String statut) throws SQLException {
		
		ArrayList<String[]> emprunts = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from emprunt order by date_retour";
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			String[]  emprunt = new String[4];
			emprunt[0] = rset.getString("id_usager");
			emprunt[1] = rset.getString("id_exemplaire");
			emprunt[2] = rset.getString("date_emprunt");
			emprunt[3] = rset.getString("date_retour");
			

			emprunts.add(emprunt);
		}
		rset.close();
		stmt.close();

		return new ArrayList<String[]>();
	
	}

	/**
	 * Récupération de la liste complète des emprunts en cours 
	 * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que d'emprunts dans la base.
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeEmpruntsEnCours() throws SQLException {
		
		ArrayList<String[]> emprunts = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from emprunt where date_retour is null order by id_exemplaire";
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			String[]  emprunt = new String[4];
			emprunt[0] = rset.getString("id_usager");
			emprunt[1] = rset.getString("id_exemplaire");
			emprunt[2] = rset.getString("date_emprunt");
			emprunt[3] = rset.getString("date_retour");
			

			emprunts.add(emprunt);
		}
		rset.close();
		stmt.close();

		return emprunts;
	
	}

	/**
	 * Récupération de la liste complète des emprunts passés
	 * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que d'emprunts dans la base.
	 * @throws SQLException en cas d'erreur de connexion à la base
	 */
	public static ArrayList<String[]> listeEmpruntsHistorique() throws SQLException {
		ArrayList<String[]> empruntsPasse = new ArrayList<String[]>();

		Statement stmt = Connexion.getConnection().createStatement();
		String query = "select * from emprunt where date_retour is not null"; //select * from emprunt where date_retour is not null
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next()){
			String[]  emprunt = new String[4];
			emprunt[0] = rset.getString("id_usager");
			emprunt[1] = rset.getString("id_exemplaire");
			emprunt[2] = rset.getString("date_emprunt");
			emprunt[3] = rset.getString("date_retour");
			
			empruntsPasse.add(emprunt);
		}
		
		rset.close();
		stmt.close();
		return empruntsPasse;
	}

	/**
	 * Emprunter un exemplaire à partir du n° d'usager et du n° d'exemplaire
	 * @param id_usager
	 * @param id_exemplaire
	 * @throws SQLException
	 */
	public static void emprunter(String id_usager, String id_exemplaire) throws SQLException {
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "insert into emprunt values ("+id_exemplaire+","+id_usager+",CURRENT_DATE)"; //insert into emprunt values( 1,1,CURRENT_DATE)
		stmt.executeUpdate(query);
		stmt.close();
		
	}

	/**
	 * Rendre un exemplaire à partir du n° d'exemplaire
	 * @param id_exemplaire
	 * @throws SQLException
	 */
	public static void rendre(String id_exemplaire) throws SQLException {
		Statement stmt = Connexion.getConnection().createStatement();
		String query = "update emprunt set date_retour = CURRENT_DATE where id_exemplaire =" +id_exemplaire; //update emprunt set date_retour = CURRENT_DATE where id_exemplaire = 1
		stmt.executeUpdate(query);
		stmt.close();
	}
}
