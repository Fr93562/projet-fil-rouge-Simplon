package fr.api.project.app.model.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * Correspond à la table des langages
 */
@Entity
public class Langage {

	private int id;
	private String langage;
	private Collection <Question> question;
	
	
	/*
	 * Constructeurs
	 */
	public Langage() {
		
	}
	
	public Langage(String langage) {
		this.langage = langage;
	}
	
	public Langage(String langage, Collection<Question> question) {
		this.langage = langage;
		this.question = question;
	}

	/*
	 * Méthodes getters and setters
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLangage() {
		return langage;
	}
	public void setLangage(String langage) {
		this.langage = langage;
	}

	@OneToMany
	public Collection<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Collection<Question> question) {
		this.question = question;
	}
	
	
	
	
}
