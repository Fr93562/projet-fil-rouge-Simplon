package fr.api.project.app.model.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


/*
 * Correspond � la table des utilisateurs
 */
@Entity
public class User {
	
	private int id;
	private String username;
	private String email;
	private String password;
	private Date dateInscription;
	private TypeUser typeUser;
	private Collection <Langage> langage;
	
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

	public User(String username, String email, String password, Date dateInscription,
			TypeUser typeUser) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
		this.typeUser = typeUser;
	}
	
	

	public User(int id, String username, String email, String password, Date dateInscription, 
			TypeUser typeUser, Collection <Langage> langage) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
		this.typeUser = typeUser;
		this.langage = langage;
	}

	/*
	 * m�thodes getters et setters
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@ManyToOne
	public TypeUser getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="user_langage", joinColumns={@JoinColumn(name="id_user", unique=false)}, inverseJoinColumns={@JoinColumn(name="id_langage", unique=false)})
	public Collection <Langage> getLangage() {
		return langage;
	}

	public void setLangage(Collection <Langage> langage) {
		this.langage = langage;
	}
	
	
	
	
	
	
}
