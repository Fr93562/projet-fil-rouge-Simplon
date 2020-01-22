package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Correspond à la table des types d'utilisateur
 */
@Entity
public class TypeUser {
	

	
	private int id;
	private String type;
	
	/*
	 * Constructeurs
	 */
	public TypeUser() {
		
	}

	public TypeUser(String type) {
		this.type = type;
	}

	public TypeUser(int id, String type) {
		this.id = id;
		this.type = type;
	}

	
	/*
	 * Méthodes getters and setters
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
}
