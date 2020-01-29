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
@RequestMapping("/users")
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
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public User create(@RequestBody User userData) {
		
		TypeUser defaultRole = typeUserRepository.findByType("Joueur");
		userData.setTypeUser(defaultRole);
		userData.setRanking(0);
		
		return userRepository.saveAndFlush(userData);
	}
	
	/**
	 * Cherche un utilisateur en base de données
	 * 
	 * 
	 * @param username : pseudo de l'utilisateur
	 * @return : renvoie un objet utilisateur ou null
	 */
	@RequestMapping(params = {"username"})
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
	@GetMapping
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
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String update(@RequestBody User userData) {
		
		String output = "User not found";
		Optional<User> verify = userRepository.findById(userData.getId());
		
		  if(verify.isPresent()) {
			  
			TypeUser defaultRole = typeUserRepository.findByType("Joueur");
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
	@PutMapping("/admin")
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
	 * Mets a jour le ranking
	 * @param username Utilisateur a mettre a jour
	 * @param point Points a ajouter
	 * @return Un message en fonction de la reussite ou non
	 */
	@PutMapping(value = "/ranking", params = {"id", "point"})
	@ResponseStatus(code = HttpStatus.OK)
	public String updateRanking(int id, long point) {
		
		String retour = "User not found";
		Optional<User> userExiste = userRepository.findById(id);
		
		  if(userExiste.isPresent()) {
			User userModif = userExiste.get();
			userModif.setRanking(userModif.getRanking() + point);
			userRepository.saveAndFlush(userModif);			  
			retour = "Ranking has been update";
		  }
		  
		  return retour;
	}
	
	/**
	 * Supprime un utilisateur en base de Bdd
	 * La réponse dépend de son existence en Bdd
	 * 
	 * @param userData : corresponds au Json transformé en objet user
	 */ 
	@DeleteMapping
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
	
	/**
	 * Liste tous les types
	 * @return La liste des types
	 */
	@GetMapping("/type")
	public List<TypeUser> getTypeUser(){
		return typeUserRepository.findAll();
	}
	
	/**
	 * Ajoute un type
	 * @return Le type cree
	 */
	@PostMapping("/type")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TypeUser addTypeUser(@RequestBody TypeUser newTypeUser){
		return typeUserRepository.saveAndFlush(newTypeUser);
	}
	
	/**
	 * Modifie un type
	 * @param modifTypeUser
	 * @return le type modifie
	 */
	@PutMapping("/type")
	@ResponseStatus(code = HttpStatus.OK)
	public String updateTypeUser(@RequestBody TypeUser modifTypeUser) {

		String output = "Type not found";
		Optional<TypeUser> verify = typeUserRepository.findById(modifTypeUser.getId());

		  if(verify.isPresent()) {
			  typeUserRepository.saveAndFlush(modifTypeUser);			  
			  output = "Type has been update";
		  }
		  return output;
	}
	
	/**
	 * Supprime le type 
	 * @param delTypeUser Type a supprimé
	 * @return retourne un message en fonction du resultat de la requete
	 */
	@DeleteMapping("/type")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteTypeUser(@RequestBody TypeUser delTypeUser) {

		String output = "Type not found";
		Optional<TypeUser> verify = typeUserRepository.findById(delTypeUser.getId());

		if(verify.isPresent()) {
			typeUserRepository.delete(delTypeUser);	
			output = "Type has been delete";
		}
		return output;
	}
}