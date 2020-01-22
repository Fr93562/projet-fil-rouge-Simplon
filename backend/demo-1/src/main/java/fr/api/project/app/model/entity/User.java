package fr.api.project.app.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Correspond à la table des utilisateurs
 */
@Entity
public class User {

	
	private int id;
	private String username;
	private String email;
	private String password;
	private Date dateInscription;
	
	/*
	 * Constructeurs
	 */
	public User() {
		
	}
	
	public User(String username, String email, String password, Date dateInscription) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
	}
	
	public User(int id, String username, String email, String password, Date dateInscription) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
	}

	/*
	 * méthodes getters et setters
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	
	
	
}
