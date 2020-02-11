package fr.api.trivialCode.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.api.trivialCode.model.User;
import fr.api.trivialCode.service.Authentification;

/**
 * Gere les path de securite
 * 
 * @author: Francois Macko
 *
 */
@CrossOrigin("*")
@RestController
public class SecurityController {

	@Resource(name = "connexion")
	private Authentification auth;

	/**
	 * Gère l'authentification
	 * 
	 * @param userData: Corresponds aux données users passées dans le Json
	 * @return
	 */
	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	public String[] login(@RequestBody User userData) {

		auth.setUsername(userData.getUsername());
		auth.setPassword(userData.getPassword());

		return auth.sign();
	}
}
