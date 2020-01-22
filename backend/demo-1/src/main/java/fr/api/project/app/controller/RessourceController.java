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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.api.project.app.model.entity.Ressource;
import fr.api.project.app.repository.RessourceRepository;

@CrossOrigin("*")
@RestController

/**
 * gère les routes des ressources
 * @author 
 *
 */
public class RessourceController {
	

	@Autowired
	private RessourceRepository ressourceRepository;
	
	/**
	 * méthode qui permet de rajouter une ressource à la liste
	 * @param ressource la nouvelle ressource
	 * @return la ressource ajoutée
	 */
	@PostMapping("/ressources")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ressource ajoutRessource(@RequestBody Ressource ressource) {
		return ressourceRepository.saveAndFlush(ressource);
	}
	
	
	/**
	 * méthode qui permet d'afficher la liste des ressources
	 * @return la liste des ressources
	 */
	@GetMapping("/ressources")
	public List<Ressource> afficherRessource() {
		return ressourceRepository.findAll();
	}
	
	/**
	 * méthode qui permet de modifier une ressource
	 * @param newRessource ressource modifiée
	 */
	@PutMapping("/ressources")
	public void modifierRessource(@RequestBody Ressource newRessource) {
		Optional<Ressource> oldRessource = ressourceRepository.findById(newRessource.getId());
		  if(oldRessource.isPresent()) {
			  ressourceRepository.saveAndFlush(newRessource);			  
		  }
	}
	
	/**
	 * méthode qui permet de supprimer une ressource
	 * @param removeRessource ressource à supprimer
	 */
	@DeleteMapping("/ressources")
	@ResponseStatus(code = HttpStatus.OK)
	public void supprimerRessource(@RequestBody Ressource removeRessource) {
		Optional<Ressource> oldRessource = ressourceRepository.findById(removeRessource.getId());
		  if(oldRessource.isPresent()) {
			  ressourceRepository.delete(removeRessource);			  
		  }
	}
	
}