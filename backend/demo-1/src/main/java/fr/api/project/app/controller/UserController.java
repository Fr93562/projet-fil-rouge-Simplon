package fr.api.project.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.api.project.app.model.entity.TypeUser;
import fr.api.project.app.model.entity.User;
import fr.api.project.app.repository.TypeUserRepository;
import fr.api.project.app.repository.UserRepository;



/**
 * Gere les paths de User
 * 
 * @author FrancoisMacko
 */
@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TypeUserRepository typeUserRepository;
	
	/**
	 * Crée un nouvel utilisateur
	 * 
	 * @param userData: Corresponds aux données users passées dans le Json
	 * @return
	 */
	@PostMapping("/users")
	@ResponseStatus(code = HttpStatus.CREATED)
	public User create(@RequestBody User userData) {
		
		TypeUser defaultRole = typeUserRepository.findByType("user");
		userData.setTypeUser(defaultRole);
		
		return userRepository.saveAndFlush(userData);
	}
	
	/**
	 * Cherche un utilisateur en base de données
	 * 
	 * 
	 * @param username : pseudo de l'utilisateur
	 * @return : renvoie un objet utilisateur ou null
	 */
	@RequestMapping(value = "/users/", params = {"username"})
	public Optional<User> read(String username) {
		
		Optional<User> output = null;
		Optional<User> verify = userRepository.findByUsername(username);
		
		  if(verify.isPresent()) {
			  
			  output = userRepository.findByUsername(username);
		  }
		
		return output;
	}
	
	/**
	 * Renvoie les utilisateurs au format Json 
	 * Les mots de passes sont retirés avant le renvoi
	 * 
	 * @return : renvoie une liste en Json
	 */
	@GetMapping("/users")
	public List<User> readAll() {
		
		List<User> output = userRepository.findAll();
		
		for (User user : output) {
			user.setPassword("null");
		}
		
		return output;
	}
	
	/**
	 * Mets a jour l'utilisateur
	 * 
	 * @param userData
	 * @return : une reponse en fonction de l'existence
	 */
	@PutMapping("/users")
	@ResponseStatus(code = HttpStatus.OK)
	public String update(@RequestBody User userData) {
		
		String output = "User not found";
		Optional<User> verify = userRepository.findById(userData.getId());
		
		  if(verify.isPresent()) {
			  
			TypeUser defaultRole = typeUserRepository.findByType("user");
			userData.setTypeUser(defaultRole);
			
			userRepository.saveAndFlush(userData);			  
			output = "User has been update";
		  }
		  
		  return output;
	}
	
	/**
	 * Mets a jour l'utilisateur
	 * Utilisable uniquement sous accès administrateur
	 * 
	 * @param userData
	 * @return : une reponse en fonction de l'existence
	 */
	@PutMapping("/users/admin")
	@ResponseStatus(code = HttpStatus.OK)
	public String updateAll(@RequestBody User userData) {
		
		String output = "User not found";
		Optional<User> verify = userRepository.findById(userData.getId());
		
		  if(verify.isPresent()) {
			
			userRepository.saveAndFlush(userData);			  
			output = "User has been update";
		  }
		  
		  return output;
	}
	
	/**
	 * Supprime un utilisateur en base de Bdd
	 * La réponse dépend de son existence en Bdd
	 * 
	 * @param userData : corresponds au Json transformé en objet user
	 */
	@DeleteMapping("/users")
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody User userData) {
		
		String output = "User not found";
		Optional<User> verify = userRepository.findById(userData.getId());
		
		if(verify.isPresent()) {
		
			userRepository.delete(userData);	
			output = "User has been delete";
		}
		return output;
	}
}