package fr.api.trivialCode.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.api.trivialCode.model.ResponseObject;
import fr.api.trivialCode.model.Ressource;
import fr.api.trivialCode.repository.RessourceRepository;
import fr.api.trivialCode.service.Authentification;

/**
 * Gère les routes des ressources
 * 
 * @author Elodie
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/ressources")
public class RessourceController {

	@Resource(name = "connexion")
	private Authentification auth;

	@Autowired
	private RessourceRepository ressourceRepository;

	/**
	 * Méthode qui permet de rajouter une ressource à la liste
	 * 
	 * @param ressource la nouvelle ressource
	 * @return la ressource ajoutée
	 */
	@PostMapping
	public ResponseEntity<ResponseObject> ajoutRessource(@RequestBody Ressource ressource, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				ressourceRepository.saveAndFlush(ressource);
				return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.CREATED);
			}
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * Méthode qui permet d'afficher la liste des ressources
	 * 
	 * @return la liste des ressources
	 */
	@GetMapping
	public List<Ressource> afficherRessource() {

		// request.getHeader("token")

		return ressourceRepository.findAll();
	}

	/**
	 * Méthode qui permet de modifier une ressource
	 * 
	 * @param newRessource ressource modifiée
	 */
	@PutMapping
	public ResponseEntity<ResponseObject> modifierRessource(@RequestBody Ressource newRessource, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Ressource> oldRessource = ressourceRepository.findById(newRessource.getId());
				if (oldRessource.isPresent()) {
					ressourceRepository.saveAndFlush(newRessource);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * méthode qui permet de supprimer une ressource
	 * 
	 * @param removeRessource ressource à supprimer
	 */
	@DeleteMapping
	public ResponseEntity<ResponseObject> supprimerRessource(@RequestBody Ressource removeRessource, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Ressource> oldRessource = ressourceRepository.findById(removeRessource.getId());
				if (oldRessource.isPresent()) {
					ressourceRepository.delete(removeRessource);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}

}