package siteParis;


public class Pari {
	private long Jetons;
	private String competition;
	private String competiteur;
	private Joueur joueur;
	
	public Pari(long Jetons, String competition, String competiteur, Joueur joueur){
		this.Jetons = Jetons;
		this.competition = competition;
		this.competiteur = competiteur;
		this.joueur = joueur;
	}
	
	public long getJetons(){
		return Jetons;
	}
	
	public String getCompetition(){
		return competition;
	}
	
	public String getCompetiteur(){
		return competiteur;
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
}
