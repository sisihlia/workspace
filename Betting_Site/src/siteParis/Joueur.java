package siteParis;

import java.util.LinkedList;
import org.apache.commons.lang.RandomStringUtils;


public class Joueur {
	private String nom;
	private String prenom;
	private String pseudo;
	private long sommeEnJetons =0;
	private String passwordJoueur;
	private LinkedList<Pari> LLpari;
	//private long montantJetonsMise =0;
	
	public Joueur (String nom, String prenom, String pseudo){
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		LLpari = new LinkedList<Pari>();
	}
	
	public Joueur (String pseudo,String passwordJoueur ){
		this.pseudo = pseudo;
		this.passwordJoueur = passwordJoueur;
		LLpari = new LinkedList<Pari>();
		
	}
	public String getNom(){
		return nom;
	}
	
	public String getPrenom(){
		return prenom;
	}
	
	public String getPseudo(){
		return pseudo;
	}
	
	public String getPasswordJoueur(){
		return passwordJoueur;
	}
	
	public void crediteurJoueur(long miseEnJetons){
		this.sommeEnJetons += miseEnJetons;	
	}
	
	public void debiteurJoueur(long miseEnJetons){
		this.sommeEnJetons -= miseEnJetons;
	}
	
	public long getJetons(){
		return this.sommeEnJetons;
	}
	
	public String returnJetons(){
		return ""+this.sommeEnJetons; // par ajoutant "" converter long à string
	}
	
	public void supprimerPari(Pari p){
		LLpari.remove(p);
	}
	
	public long returnJetonsMise(){
		Pari p = null;
		long MJM = 0; // on doit mettre var dans le method, pas global var parce que on va calculer le debut du montantJetons pour chaque solderVainqueur initialize 0
		for(int i=0; i<LLpari.size(); i++){
			p = LLpari.get(i);
			MJM += p.getJetons();
		}
		return MJM;
	}
	
	public String toStringReturnJetonsMise(){
		//long jeton = returnJetonsMise();
		return (""+returnJetonsMise());
	}
	
	public String generatePassword(){
		for (int i = 0; i < 8; i++) {
			passwordJoueur = RandomStringUtils.randomAlphanumeric(8);   
		}
		 return passwordJoueur;
		
	}
	public void ajouterPari(Pari pari){
		LLpari.add(pari);
	}
	public static void main (String [] args){
		Joueur a = new Joueur("yuniyarti","sisihlia","abc1234");
		a.getNom();
		a.getPrenom();
		a.getPseudo();
		System.out.println(a.getNom()+ " "+ a.getPrenom()+" "+ a.getPseudo());
	}
}

