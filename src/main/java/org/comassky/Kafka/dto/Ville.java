package org.comassky.Kafka.dto;

public class Ville {

	private String nom;
	private Integer departement;
	private String codePostal;
	private Long nombreHabitant;

	public Ville() {
	}

	public Ville(String nom, Integer departement, String codePostal, Long nombreHabitant) {
		this.nom = nom;
		this.departement = departement;
		this.codePostal = codePostal;
		this.nombreHabitant = nombreHabitant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getDepartement() {
		return departement;
	}

	public void setDepartement(Integer departement) {
		this.departement = departement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Long getNombreHabitant() {
		return nombreHabitant;
	}

	public void setNombreHabitant(Long nombreHabitant) {
		this.nombreHabitant = nombreHabitant;
	}

	@Override
	public String toString() {
		return "Ville{" +
				"nom='" + nom + '\'' +
				", departement=" + departement +
				", codePostal='" + codePostal + '\'' +
				", nombreHabitant=" + nombreHabitant +
				'}';
	}
}

