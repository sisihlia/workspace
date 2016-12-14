package siteParis;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SiteDeParisMetier {
	private String passwordGestionnaire;
	private LinkedList<Competition> LLcompetition;
	private LinkedList<Joueur> LLjoueur;
	private LinkedList<Competiteur> LLcompetiteur;
	private LinkedList<Pari> LLpari;

	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException  {
		this.passwordGestionnaire = passwordGestionnaire;
		if (passwordGestionnaire == null )throw new MetierException(); // == est just utilisé enn tous les type sauf string
		if (!passwordGestionnaire.matches("[0-9a-zA-Z]{8,}")) throw new MetierException(); // matches est juste utilisé en regular expression
		 LLcompetition = new LinkedList<Competition>();
		 LLjoueur = new LinkedList<Joueur>();
		 LLcompetiteur = new LinkedList<Competiteur>();
		 LLpari = new LinkedList<Pari>();
	}

	public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		
		for (int i =0; i< LLjoueur.size(); i++){ // verifier chaque joueur dans LinkedList
			if(LLjoueur.get(i).getNom().equals(nom) &&(LLjoueur.get(i).getPrenom().equals(prenom))) throw new JoueurExistantException();
			if(LLjoueur.get(i).getPseudo().equals(pseudo)) throw new JoueurExistantException();
		}
		
		if (nom == null) throw new JoueurException();
		if (prenom == null) throw new JoueurException();
		if (pseudo == null) throw new JoueurException();
	 
		if (!nom.matches("[a-zA-Z-]{1,}"))throw new JoueurException();
		if (!prenom.matches("[a-zA-Z-]{1,}"))throw new JoueurException();
		if (!pseudo.matches("[0-9a-zA-Z]{4,}"))throw new JoueurException();
		
		Joueur joueur = new Joueur(nom, prenom, pseudo);
		//if((nom.matches("[a-zA-Z]{1,}"))&&(prenom.matches("[a-zA-Z]{1,}"))&&(pseudo.matches("[0-9a-zA-Z]{4,}"))){ // on doit pas ecrirer parce que on a deja descrire avant
			LLjoueur.add(joueur);
			joueur.generatePassword();
		
		return "unPasswordUnique";
	}
	
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		
		if (nom == null) throw new JoueurException();
		if (prenom == null)throw new JoueurException();
		if (pseudo == null)throw new JoueurException();
		
		Joueur j = null; // c'est comme la position qui on veut supprimer, au debut c'est null
		for (int i =0; i< LLjoueur.size(); i++){ // verifier chaque joueur dans LinkedList
			if((LLjoueur.get(i).getNom().equals(nom))&&(LLjoueur.get(i).getPrenom().equals(prenom))&&(LLjoueur.get(i).getPseudo().equals(pseudo))){
				j = LLjoueur.get(i);
				LLjoueur.remove(j);	
			}
		}
		if(j == null)throw new JoueurInexistantException();// on peut pas mettre ça dans le buckle parce que si il trouvre pas le nom, prenom, et pseudo à le premiere lien, il va montrer JoueurInexistantException	
		
		return 0;
	}

	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		if(competiteurs == null) throw new MetierException();
		
		for (int i =0; i< LLcompetition.size(); i++){ 
			if(LLcompetition.get(i).getName().equals(competition)) throw new CompetitionExistanteException();
		}
		
		if(competition == null)throw new CompetitionException();
		if(!competition.matches("[0-9a-zA-Z-._]{4,}"))throw new CompetitionException();	
		
		for(int i = 0; i<competiteurs.length; i++){ // length pour array, size pour LinkedList
			if(competiteurs[i] == null)throw new CompetitionException(); // dans array, on peut pas utiliser get, c'est pour LinkedList
			if(!competiteurs[i].matches("[0-9a-zA-Z-._]{4,}"))throw new CompetitionException();
			if(competiteurs.length<2)throw new CompetitionException();
		}
		
		for (int i = 0; i <competiteurs.length; i++){ // on doit utiliser index i&j parce que on va comparer entre chaque list(c1-c2,c1-c3,c1-c4,c1-c5 ; c2-c3,c2-c4,c2-c5; c3-c4, c4-c5;....)
			for(int j = i+1; j <competiteurs.length; j++){
				if(competiteurs[i] == competiteurs[j]) throw new CompetitionException();
			}
		}
		
		if(dateCloture == null)throw new CompetitionException();//dateCloture est un objet de la class DateFrancaise, donc c'est deja compris tous les attributs et methods
		if (dateCloture.estDansLePasse())throw new CompetitionException() ;
		
		Competition a = new Competition(competition,dateCloture); 
		for(int i = 0; i<competiteurs.length; i++){ // transfere le type de String[]competiteurs à LLcompeiteur
			Competiteur c = new Competiteur(competiteurs[i]);// creer l'objet de String[] competiteurs 
			a.ajouterCompetiteur(c); //ajouter l'objet de competiteur par competition
		}
		LLcompetition.add(a); // on doit faire le ajouter pour verifier les exeption
	}

	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException  {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		
		Competition c = null;
    	for (int i =0;i<LLcompetition.size();i++){
    		if(LLcompetition.get(i).getName().equals(competition))
    			c=LLcompetition.get(i);
    	}
    	if(c == null)throw new CompetitionInexistanteException();
    	
    	if(!c.getCompetiteur(vainqueur))throw new CompetitionException();    			
		
    	if (c.getDate().estDansLeFutur())throw new CompetitionException() ;
    	
    	
    	c.removePari(); // apres solder vainqueur, il doit supprimer le pari qu'a deja vainqueur pour chaque joueur
    	c.repartitionJeton(vainqueur);
    	LLcompetition.remove(c);
    	
    	
    	
    	
	}	
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		if(sommeEnJetons<0)throw new MetierException();
		
		if (nom == null) throw new JoueurException();
		if (prenom == null)throw new JoueurException();
		if (pseudo == null)throw new JoueurException();
		
		if(sommeEnJetons<0) throw new MetierException();
		Joueur j = null;
		for (int i =0; i< LLjoueur.size(); i++){ // verifier chaque joueur dans LinkedList
			if((LLjoueur.get(i).getNom().equals(nom))&&(LLjoueur.get(i).getPrenom().equals(prenom))&&(LLjoueur.get(i).getPseudo().equals(pseudo))) {
				j = LLjoueur.get(i);
				j.crediteurJoueur(sommeEnJetons);
			}
		}	// on peut pas faire exception dans le buckle, sinon on va jetter exception quand il n'y a pas le joueur à premiere [1]
		if(j == null)	throw new JoueurInexistantException(); // j==null, c'est à dire que on trouve pas le joueur
	}
	
	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {
		
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		if(sommeEnJetons<0)throw new MetierException();
		
		if (nom == null) throw new JoueurException();
		if (prenom == null)throw new JoueurException();
		if (pseudo == null)throw new JoueurException();
		
		Joueur j = null;
		for (int i =0; i< LLjoueur.size(); i++){ // verifier chaque joueur dans LinkedList
			if((LLjoueur.get(i).getNom().equals(nom))&&(LLjoueur.get(i).getPrenom().equals(prenom))&&(LLjoueur.get(i).getPseudo().equals(pseudo))) {
				j = LLjoueur.get(i);
			}
		}
		if(j == null) throw new JoueurInexistantException();
		if (j.getJetons()- sommeEnJetons < 0) throw new JoueurException();
		j.debiteurJoueur(sommeEnJetons); // il doit debiteur apres il verifier si les jetons est assez , donc on peut pas remplacer ça dans for loop
	}
	
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {
		/** 
		 * consulter les  joueurs.
		 * 
		 * @param passwordGestionnaire  le password du gestionnaire du site de paris 

		 * @throws MetierException   levée  
		 * si le <code>passwordGestionnaire</code>  est invalide,
		 * si le <code>passwordGestionnaire</code> est incorrect.
		 * 
		 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre : 
		 *  <ul>
		 *  <li>       le nom du joueur  </li>
		 *  <li>       le prénom du joueur </li>
		 *  <li>       le pseudo du joueur  </li>
		 *  <li>       son compte en jetons restant disponibles </li>
		 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
		 *  </ul>
		 */
		if(passwordGestionnaire == null )throw new MetierException();
		if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();
		
		
		LinkedList <LinkedList <String>> listJoueurs = new LinkedList <LinkedList <String>>();
		
		for(int i=0; i<LLjoueur.size(); i++){
			LinkedList <String> LLjo = new LinkedList <String>();
			Joueur j = LLjoueur.get(i);
			LLjo.add(j.getNom());
			LLjo.add(j.getPrenom());
			LLjo.add(j.getPseudo());
			LLjo.add(j.returnJetons());
			LLjo.add(j.toStringReturnJetonsMise());
			listJoueurs.add(LLjo);
		}
		return listJoueurs;
	}


    public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {
    	
    	if((pseudo == null)||(passwordJoueur == null))throw new JoueurException();
    	if (!(pseudo.matches("[0-9a-zA-Z]{4,}") || passwordJoueur.matches("[0-9a-zA-Z]{8,}")))throw new JoueurException();
	
    	Joueur j = null;
    	for (int i =0;i<LLjoueur.size();i++){
    		if(LLjoueur.get(i).getPseudo().equals(pseudo)){
    			j = LLjoueur.get(i); 
    		}	
    	}
    	if (j.getJetons()- miseEnJetons < 0) throw new JoueurException();
    			
    	Competition c = null;
		for(int i =0; i<LLcompetition.size();i++){
			if(LLcompetition.get(i).getName().equals(competition)){
				c=LLcompetition.get(i);
			}
		}	
		if(c == null) throw new CompetitionInexistanteException();
		
		for(int i=0;i<LLcompetiteur.size();i++){
			if(!c.getCompetiteur(vainqueurEnvisage))throw new CompetitionException();	
		}
		
		for (int i =0; i< LLjoueur.size(); i++){ // verifier chaque joueur dans LinkedList
			if((LLjoueur.get(i).getPseudo().equals(pseudo)&&LLjoueur.get(i).getPasswordJoueur().equals(passwordJoueur))){ 
				j=LLjoueur.get(i);
			}
		}	
		
		if (j == null) throw new JoueurInexistantException();
		Pari p = new Pari(miseEnJetons,competition, vainqueurEnvisage,j);
		c.ajouterPari(p);
		j.ajouterPari(p);
		j.debiteurJoueur(miseEnJetons);
	}
    
   public LinkedList<Joueur> regresarListaJugador(){
    	
    	return LLjoueur;
    }
    
	public LinkedList <LinkedList <String>> consulterCompetitions(){
		
		/**
		 * connaître les compétitions en cours.
		 * 
		 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre : 
		 *  <ul>
		 *  <li>       le nom de la compétition,  </li>
		 *  <li>       la date de clôture de la compétition. </li>
		 *  </ul>
		 */
		
		
		LinkedList<LinkedList<String>> List = new LinkedList<LinkedList<String>>();
		
		for(int i = 0;i<LLcompetition.size();i++){
			LinkedList<String> LLnomDate = new LinkedList<String>();
			Competition c = LLcompetition.get(i);
			LLnomDate.add(c.getName());
			LLnomDate.add(c.getDate().toString());
			List.add(LLnomDate);
		}
		return List;
	} 
	
	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.  
	 * 
	 * @param competition   le nom de la compétition  
	 * 
	 * @throws CompetitionException   levée  
	 * si le nom de la compétition est invalide.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * 
	 * @return la liste des compétiteurs de la  compétition.
	 */
	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
		
		if(competition == null) throw new CompetitionException();
		if(!competition.matches("[0-9a-zA-Z-._]{4,}"))throw new CompetitionException();	
		
		Competition c = null;
		for(int i=0; i<LLcompetition.size();i++){
			if(LLcompetition.get(i).getName().equals(competition)){
				c = LLcompetition.get(i);
			}
		}
		if(c == null)throw new CompetitionInexistanteException();	
		
		return c.returnCompetiteur();
	}

	/**
	 * vérifier la validité du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code> est invalide,  
	 * si le <code>passwordGestionnaire</code> à vérifier n'est pas correct.
	 */
	
	
	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
	    if (passwordGestionnaire==null) throw new MetierException();
	    if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
	    if (!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();//equals est juste utilisé en string type
	}
}
	
