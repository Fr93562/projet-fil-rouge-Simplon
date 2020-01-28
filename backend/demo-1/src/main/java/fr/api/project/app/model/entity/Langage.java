package fr.api.project.app.model.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * Correspond a la table des langages
 */
@Entity
public class Langage {

	/**
	 * Attributs
	 */
	
	private int id;
	private String language;
	private Collection <Question> question;
	
	
	/*
	 * Constructeurs
	 */
	
	public Langage() {
		
	}
	
	public Langage(String language) {
		this.language = language;
	}
	
	public Langage(String language, Collection<Question> question) {
		this.language = language;
		this.question = question;
	}

	/*
	 * Methodes getters and setters
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonBackReference //Evite les boucles d'appel
	@OneToMany  //Un Langage peut etre dans plusieurs Questions
	public Collection<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Collection<Question> question) {
		this.question = question;
	}
}
