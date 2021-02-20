package org.comassky.Kafka.dto;

import java.time.LocalDate;

public class Personne {

	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private Integer age;
	private Float taille;

	public Personne() {
	}

	public Personne(String nom, String prenom, LocalDate dateNaissance, Integer age, Float taille) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.age = age;
		this.taille = taille;
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

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getTaille() {
		return taille;
	}

	public void setTaille(Float taille) {
		this.taille = taille;
	}

	@Override
	public String toString() {
		return "Personne{" +
				"nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", dateNaissance=" + dateNaissance +
				", age=" + age +
				", taille=" + taille +
				'}';
	}
}
