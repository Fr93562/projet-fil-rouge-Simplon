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
import fr.api.project.app.model.entity.Ressource;
import fr.api.project.app.repository.RessourceRepository;


/**
 * gère les routes des ressources
 * @author Elodie
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/ressources")
public class RessourceController {
	
	@Autowired
	private RessourceRepository ressourceRepository;
	
	/**
	 * Méthode qui permet de rajouter une ressource à la liste
	 * @param ressource la nouvelle ressource
	 * @return la ressource ajoutée
	 */
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ressource ajoutRessource(@RequestBody Ressource ressource) {
		return ressourceRepository.saveAndFlush(ressource);
	}
	
	/**
	 * Méthode qui permet d'afficher la liste des ressources
	 * @return la liste des ressources
	 */
	@GetMapping("")
	public List<Ressource> afficherRessource() {
		return ressourceRepository.findAll();
	}
	
	/**
	 * Méthode qui permet de modifier une ressource
	 * @param newRessource ressource modifiée
	 */
	@PutMapping("")
	public String modifierRessource(@RequestBody Ressource newRessource) {
		String retour = "Ressource non trouvé";
		Optional<Ressource> oldRessource = ressourceRepository.findById(newRessource.getId());
		  if(oldRessource.isPresent()) {
			  ressourceRepository.saveAndFlush(newRessource);
			  return "Ressource modifié";
		  }
		  return retour;
	}
	
	/**
	 * méthode qui permet de supprimer une ressource
	 * @param removeRessource ressource à supprimer
	 */
	@DeleteMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public String supprimerRessource(@RequestBody Ressource removeRessource) {
		String retour = "Ressource non trouvé";
		Optional<Ressource> oldRessource = ressourceRepository.findById(removeRessource.getId());
		  if(oldRessource.isPresent()) {
			  ressourceRepository.delete(removeRessource);
			  return "Ressource Supprimé";
		  }
		  return retour;
	}
	
}