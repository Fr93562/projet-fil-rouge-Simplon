package fr.api.project.app.model.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Declaration de l'objet model Ressource
 * Model de table pour hibernate
 * Id avec avec auto-incrementation
 * @author trivial code devs
 *
 */
@Entity
public class Ressource 
{
	/**
	 * Attributs
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	private String link;
	
	@OneToMany(mappedBy="ressource") //Une ressource peut avoir plusieurs Questions
	@JsonIgnoreProperties("ressource")
	private Collection<Question> questions;

	/**
	 * Constructeurs
	 */
	
	public Ressource() {}

	public Ressource(String text, String link, Collection <Question> questions) 
	{
		this.text = text;
		this.link = link;
		this.questions = questions;
	}
	
	/**
	 * Getter/Setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> question) {
		this.questions = question;
	}
}
