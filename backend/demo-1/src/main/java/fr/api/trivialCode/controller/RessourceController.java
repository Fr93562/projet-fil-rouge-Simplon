package fr.api.trivialCode.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajoutRessource(@RequestBody Ressource ressource, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				ressourceRepository.saveAndFlush(ressource);
				output = "success";
			}
		}
		return output;
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
	public String modifierRessource(@RequestBody Ressource newRessource, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Ressource> oldRessource = ressourceRepository.findById(newRessource.getId());
				if (oldRessource.isPresent()) {
					ressourceRepository.saveAndFlush(newRessource);
					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * méthode qui permet de supprimer une ressource
	 * 
	 * @param removeRessource ressource à supprimer
	 */
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String supprimerRessource(@RequestBody Ressource removeRessource, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Ressource> oldRessource = ressourceRepository.findById(removeRessource.getId());
				if (oldRessource.isPresent()) {
					ressourceRepository.delete(removeRessource);
					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * Retourne la Liste des ressources correspondant a la liste des IDs des
	 * questions
	 * 
	 * @param ids Liste des IDs a recuperer
	 * @return Liste des ressources correspondant au IDs des questions
	 */
	@GetMapping("/end")
	public List<Ressource> returnListRessources(@RequestBody List<Integer> ids) {
		return ressourceRepository.findAllById(ids);
	}
}