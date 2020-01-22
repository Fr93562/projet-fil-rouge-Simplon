package fr.api.project.app.model.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * Déclaration de l'objet model Ressource
 * modèle de table pour hibernate
 * id avec avec auto incrémentation
 * @author trivial code devs
 *
 */
@Entity
public class Ressource 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	private String link;
	
	
	@OneToMany(mappedBy="ressource")
	private Collection<Question> questions;

	public Ressource() {}

	public Ressource(int id, String text, String link, Collection <Question> questions) 
	{
		this.id = id;
		this.text = text;
		this.link = link;
		this.questions = questions;
	}
	
	

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

	public Collection<Question> getQuestion() {
		return questions;
	}

	public void setQuestion(Collection<Question> question) {
		this.questions = question;
	}
	
	
	
	

}
