package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Correspond a la table des types d'utilisateurs
 */
@Entity
public class TypeUser {
	
	/**
	 * Attributs
	 */
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}	
}
