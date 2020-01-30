package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Declaration de l'objet model Question
 * Model de table pour hibernate
 * Id avec avec auto-incrementation
 * @author trivial code devs
 *
 */
@Entity
public class Question 
{
	/**
	 * Attributs
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private int level;
	private String answer;
	private String choice1, choice2, choice3;
	
	@ManyToOne //Plusieurs Questions ont une Categorie
	private Categorie categorie;
	
	@ManyToOne //Plusieurs Questions ont une Ressource
	@JsonIgnoreProperties("questions")
	private Ressource ressource;
	
	/**
	 * Constructeurs
	 */
	
	public Question() {}

	public Question(String question, int level, String answer, String choice1, String choice2, String choice3,
			Categorie categorie, Ressource ressource) 
	{
		this.question = question;
		this.level = level;
		this.answer = answer;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.categorie = categorie;
		this.ressource = ressource;
	}
	
	/**
	 * Getters/Setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	
	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}
