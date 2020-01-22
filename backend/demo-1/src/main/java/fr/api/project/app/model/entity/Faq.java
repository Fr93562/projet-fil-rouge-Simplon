package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * déclaration de l'objet model FAQ
 * Modèle de table pour hibernate
 * id avec avec auto incrémentation
 * @author trivial code devs
 *
 */
@Entity
public class Faq 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private String response;
	private String priority;
	
	public Faq() {}

	public Faq(int id, String question, String response, String priority) 
	{
		this.id = id;
		this.question = question;
		this.response = response;
		this.priority = priority;
	}

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

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	
	
	
}
