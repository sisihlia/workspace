package siteParis;

//import java.util.Collection;


public class Competiteur {
	
	
	private String competiteur;
	
	
	public Competiteur (String competiteur){
		this.competiteur = competiteur;
		
	}
	public String getCompetiteur() {
		return competiteur;
	}
	
	public String toString(){
		return getCompetiteur();
	}
	
	public static void main (String [] args){
		Competiteur a = new Competiteur("cesar");
		//System.out.println(a);
		System.out.println(a.toString());
	}
}

