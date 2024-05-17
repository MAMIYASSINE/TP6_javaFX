package application;

public class Etudiant {

	private int id;
	private String nom;
	private String prenom;
	private String filere;
	private String sexe;
	public Etudiant(int id, String nom, String prenom, String filere, String sexe) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.filere = filere;
		this.sexe = sexe;
	}
	
	public Etudiant(String nom, String prenom, String filere, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.filere = filere;
		this.sexe = sexe;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getFilere() {
		return filere;
	}
	public void setFilere(String filere) {
		this.filere = filere;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	
	
}
