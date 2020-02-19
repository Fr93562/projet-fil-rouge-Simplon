package fr.api.trivialCode.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.api.trivialCode.model.User;
import fr.api.trivialCode.repository.UserRepository;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

/**
 * Gère l'authentification des users à l'application
 * 
 * @author Francois Macko
 */
@Service("connexion")
public class Authentification {

	@Autowired
	private UserRepository userRepository;

	final private String secretWord = "superToken";
	private String username = "";
	private String password = "";
	private String token = "";
	private User user = null;

	/**
	 * Constructeurs
	 */
	public Authentification() {

	}

	public Authentification(String token) {

		setToken(token);
	}

	public Authentification(String username, String password) {

		setUsername(username);
		setPassword(password);
	}

	// Getters et setters
	private String getSecretWord() {

		return secretWord;
	}

	private String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	private String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	private String getToken() {

		return token;
	}

	public void setToken(String token) {

		this.token = token;
	}

	private User getUser() {

		return user;
	}

	private void setUser(User user) {

		this.user = user;
	}

	/**
	 * Cherche un user en base de données Stocke l'user dans l'objet s'il existe
	 * 
	 * @return : true si l'user existe, false sinon
	 */
	private boolean search() {

		boolean output = false;

		Optional<User> result = userRepository.findByUsername(getUsername());

		if (result.isPresent()) {

			setUser(result.get());
			output = true;
		}
		return output;
	}

	/**
	 * Récupère un user en base de données
	 * 
	 * @return : renvoie un objet user
	 */
	private boolean compare() {

		boolean output = false;

		if ((getUser().getUsername()).equals(getUsername())) {

			if ((getUser().getPassword()).equals(getPassword())) {

				output = true;
			}
		}
		return output;
	}

	/**
	 * Prépare les données qui seront renvoyées si l'user est autorisé
	 * 
	 * @return : tableau de string qui contient l'username, le token et un message
	 */
	private String[] authorized() {

		String[] output = new String[] { getUsername(), 
										(getUser().getTypeUser()).getType(), 
										getToken(),
				"To use the token, add the token to the HTTP header" };

		return output;
	}

	/**
	 * Prépare les données qui seront renvoyées si l'user n'est pas autorisé
	 * 
	 * @return: tableau de string indiquant que l'user n'est pas reconnu
	 */
	private String[] unauthorized() {

		return new String[] { "unauthorized" };
	}

	/**
	 * Génère un token Utilise l'username et le password pour générer le payload
	 */
	private void encode() {

		Signer signer = HMACSigner.newSHA256Signer(getSecretWord());
		JWT jwt = new JWT().setSubject(getUsername() + "," + getPassword());
		String encodedJWT = JWT.getEncoder().encode(jwt, signer);

		setToken(encodedJWT);
	}

	/**
	 * Décode le token Utilise secretWord pour récupérer le payload
	 */
	private void decode() {
		
	    try {

			Verifier verifier = HMACVerifier.newVerifier(getSecretWord());
			JWT jwt = JWT.getDecoder().decode(getToken(), verifier);

			String payload = jwt.subject;
			String[] content = payload.split(",");

			System.out.println(content);
			if (content.length != 1) {

				setUsername(content[0]);
				setPassword(content[1]);
			}
	    	
	      } catch (Exception e) {
	    	  
				setUsername("fake");
				setPassword("fake");
	      }
	}

	/**
	 * Génère le token si l'utilisateur existe
	 * 
	 * @return : renvoie un tableau de string avec le nom de l'user, son rôle et
	 *         token
	 */
	public String[] sign() {

		String[] output = unauthorized();

		if (search()) {
			if (compare()) {

				encode();
				output = authorized();
			}
		}
		return output;
	}

	/**
	 * Vérifie si le token corresponds à un user en base de données
	 * 
	 * @return :
	 */
	public String[] verify() {

		String[] output = unauthorized();

		decode();
		
		if (search()) {
			if (compare()) {

				output = authorized();
			}
		}
		return output;
	}
}
