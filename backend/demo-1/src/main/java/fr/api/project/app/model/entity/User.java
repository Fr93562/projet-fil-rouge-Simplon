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


/**
 * Correspond a la table des utilisateurs
 * @author 
 *
 */
@Entity
public class User {
	
	/**
	 * Attributs
	 */
	
	private int id;
	private String username;
	private String email;
	private String password;
	private Date dateInscription;
	private TypeUser typeUser;
	private Collection <Langage> langage;
	private long ranking;
	
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

	public User(String username, String email, String password, Date dateInscription, TypeUser typeUser,
			Collection<Langage> langage, long ranking) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.dateInscription = dateInscription;
		this.typeUser = typeUser;
		this.langage = langage;
		this.ranking = ranking;
	}

	/*
	 * Methodes getters et setters
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

	@ManyToOne //Plusieurs utilisateurs ont un Type
	public TypeUser getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}

	@ManyToMany //Plusieurs utilisateurs ont plusieurs Langages
	//Creation d'une table intermediaire
	@JoinTable(name="user_langage", joinColumns={@JoinColumn(name="id_user", unique=false)}, inverseJoinColumns={@JoinColumn(name="id_langage", unique=false)})
	public Collection <Langage> getLangage() {
		return langage;
	}

	public void setLangage(Collection <Langage> langage) {
		this.langage = langage;
	}

	public long getRanking() {
		return ranking;
	}

	public void setRanking(long ranking) {
		this.ranking = ranking;
	}
}
