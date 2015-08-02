package siteParis;

import java.util.*;


public class Competition {

	private String CompetitionNom;
	private LinkedList<Competiteur> LLcompetiteur;
	private LinkedList<Pari> LLpari;
	private DateFrancaise dateCloture;
	//private LinkedList<String> LLcomp;

	public Competition (String CompetitionNom, LinkedList<Competiteur> llc){
		this.CompetitionNom = CompetitionNom;
		LLcompetiteur = llc; // on doit pas utiliser this parce que ils sont different nom
		
	}
	
	public Competition (String CompetitionNom,DateFrancaise dateCloture){
		this.CompetitionNom = CompetitionNom;
		this.dateCloture = dateCloture;
		LLcompetiteur = new LinkedList<Competiteur>();
		LLpari = new LinkedList<Pari>();
	}
	
	public boolean getCompetiteur(String nom){
		boolean flag = false;
		for(int i=0; i<LLcompetiteur.size();i++){
			if(LLcompetiteur.get(i).getCompetiteur().equals(nom)){
				flag = true;
			}
		
		}
		return flag;
	}
	
	public LinkedList<String> returnCompetiteur(){
		
		LinkedList<String> competiteurs = new LinkedList<String>();
		for(int i=0;i<LLcompetiteur.size();i++){
			Competiteur c = LLcompetiteur.get(i);
			competiteurs.add(c.getCompetiteur());
		}
		return competiteurs;
	}
	
	public String getName() {
		return CompetitionNom;
	}
	
	public void ajouterCompetiteur(Competiteur competiteur){ // on peut utiliser le different nom parce que c'est juste variable local
		LLcompetiteur.add(competiteur); // add est une methode dans LinkedList , et on peut pas declarer le type de competieur encore parce que deja declarer avant
	}
	
	public void ajouterPari(Pari pari){
		LLpari.add(pari);
	}	
	
	public DateFrancaise getDate(){
		return this.dateCloture;
	}
	
	public long getSommeEnPari(){//dans la competition
		long sommeEnPari = 0;
		for (int i = 0; i<LLpari.size(); i++){
			sommeEnPari += LLpari.get(i).getJetons();
		}
		return sommeEnPari; 
	}
	
	public long getMontantMise(Joueur joueur){//chaque joueur
		
		return joueur.getJetons();
	}
	
	public long getSommeEnJetonsMisesDeCompetiteur(String competiteur){//comme jetons pour le vainqueur
		long sommeEnPari = 0;
		for (int i =0; i<LLpari.size(); i++){
			if(LLpari.get(i).getCompetiteur().equals(competiteur)){
				sommeEnPari += LLpari.get(i).getJetons();
			}
		}
		return sommeEnPari;
	}
	
	public void repartitionJeton(String vainqueur){
  	if(getSommeEnJetonsMisesDeCompetiteur(vainqueur)==0){
  		for(int i =0; i<LLpari.size(); i++){
  			long jetons = LLpari.get(i).getJetons();
  			LLpari.get(i).getJoueur().crediteurJoueur(jetons);
  		}		
    }
    else{
    	for(int i = 0;  i<LLpari.size(); i++){
    		if(LLpari.get(i).getCompetiteur().equals(vainqueur)){
    			long jetons = (LLpari.get(i).getJetons()*getSommeEnPari())/getSommeEnJetonsMisesDeCompetiteur(vainqueur);
    			LLpari.get(i).getJoueur().crediteurJoueur(jetons);
    			
    		}
    	}
    		
    }
		
		
		
	}
	
	public void removePari(){
		for(int i= 0; i<LLpari.size(); i++ ){
			Pari p = LLpari.get(i);
			p.getJoueur().supprimerPari(p);
			
		}
		
	}
	
	
	public static void main (String [] args){
		LinkedList<Competiteur> x  = new LinkedList<Competiteur>(); // creer LL de x utiliser le type de attribute
		//LLcompetiteur = new LinkedList<Competiteur>(); // si comme ça, le attribute vas pas changer de non static à non static
		//LinkedList<Pari> y = new LinkedList<Pari>(); // on peut pas utiliser la meme nom pour tester 
		Competition a = new Competition("soccer",x);
		System.out.println(a.getName());
		Competiteur c = new Competiteur("sisil"); // on doit creer objet de competitieur pour utiliser le methode ajouterCompetiteur
		a.ajouterCompetiteur(c); //parce que ajouterCompetiteur est le methode de competition et utliser le param. nom de competiteur
		//Pari z = new Pari(500,"c1","t1");// creer objet de pari z pour verifier le methode ajouterPari
		//a.ajouterPari(z);
	}
}