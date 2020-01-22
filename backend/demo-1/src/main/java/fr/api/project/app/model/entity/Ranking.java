package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Correspond au classement des utilisateurs
 */
@Entity
public class Ranking {

	
	private int id;
	private int point;
	
	/*
	 * Constructeurs
	 */
	public Ranking() {
		
	}

	public Ranking(int point) {
		this.point = point;
	}

	public Ranking(int id, int point) {
		this.id = id;
		this.point = point;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
