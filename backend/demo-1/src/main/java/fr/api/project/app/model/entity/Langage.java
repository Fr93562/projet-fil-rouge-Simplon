package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Correspond à la table des langages
 */
@Entity
public class Langage {

	private int id;
	private String langage;
	
	
	/*
	 * Constructeurs
	 */
	public Langage() {
		
	}
	
	public Langage(String langage) {
		this.langage = langage;
	}
	
	public Langage(int id, String langage) {
		super();
		this.id = id;
		this.langage = langage;
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
	public String getLangage() {
		return langage;
	}
	public void setLangage(String langage) {
		this.langage = langage;
	}
	
	
}
