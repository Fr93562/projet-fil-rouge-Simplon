package fr.api.project.app.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/*
 * Correspond au classement des utilisateurs
 */
@Entity
public class Ranking {

	
	private int id;
	private int point;
	private User user;
	
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

	public Ranking(int id, int point, User user) {
		super();
		this.id = id;
		this.point = point;
		this.user = user;
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

	@OneToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
