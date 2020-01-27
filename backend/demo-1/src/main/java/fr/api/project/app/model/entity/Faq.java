package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Declaration de l'objet model FAQ
 * Model de table pour hibernate
 * Id avec avec auto-incrementation
 * @author trivial code devs
 *
 */
@Entity
public class Faq 
{
	/**
	 * Attributs
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String question;
	private String response;
	private String priority;
	
	/**
	 * Constructeurs
	 */
	
	public Faq() {}

	public Faq(String question, String response, String priority) 
	{
		this.question = question;
		this.response = response;
		this.priority = priority;
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
