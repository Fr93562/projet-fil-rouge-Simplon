package fr.api.trivialCode.controller;

import java.util.Collection;
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

import fr.api.trivialCode.model.Categorie;
import fr.api.trivialCode.model.Langage;
import fr.api.trivialCode.model.Question;
import fr.api.trivialCode.repository.CategorieRepository;
import fr.api.trivialCode.repository.LangageRepository;
import fr.api.trivialCode.repository.QuestionRepository;
import fr.api.trivialCode.service.Authentification;

/**
 * Controller gerant les Endpoint Question et Categorie
 * 
 * @author Camille
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionController {

	/**
	 * Attributs
	 */
	@Resource(name = "connexion")
	private Authentification auth;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private LangageRepository langageRepository;

	/**
	 * Ajoute une question
	 * 
	 * @param newQuestion La question à ajouter
	 * @return La question ajouté
	 */
	@PostMapping(params = { "langage" })
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addQuestion(@RequestBody Question newQuestion, String langage, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Question saveQuestion = questionRepository.saveAndFlush(newQuestion);
				Optional<Langage> langageVerif = langageRepository.findByLanguage(langage);

				if (langageVerif.isPresent()) {

					Langage langageComplet = langageVerif.get();
					langageComplet.getQuestion().add(saveQuestion);
					langageRepository.saveAndFlush(langageComplet);
					output = "authorized";
				}
			}
		}
		return output;
	}

	/**
	 * Retourne les questions correspondant au type de langage en parametre
	 * 
	 * @param langage langage recherche
	 * @return Liste de questions en JSON
	 */
	@RequestMapping(params = { "langage" })
	public Collection<Question> listQuestionParLangage(String langage) {

		Collection<Question> output = null;
		Optional<Langage> langageFind = langageRepository.findByLanguage(langage);

		if (langageFind.isPresent()) {

			output = (langageFind.get()).getQuestion();
		}

		return output;
	}

	/**
	 * Renvoie toutes les questions au format Json
	 * 
	 * @return : renvoie une liste en Json
	 */
	@GetMapping
	public List<Question> readAll() {

		return questionRepository.findAll();
	}

	/**
	 * Mets a jour une question
	 * 
	 * @param modifQuestion la question modifie
	 * @return Un String contenant le resultat de la requete
	 */
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String updateQuestion(@RequestBody Question modifQuestion, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Question> verify = questionRepository.findById(modifQuestion.getId());

				if (verify.isPresent()) {

					questionRepository.saveAndFlush(modifQuestion);
					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * Liste toutes les categories
	 * 
	 * @return La liste des categories
	 */
	@GetMapping("/cat")
	public List<Categorie> getCategorie() {
		return categorieRepository.findAll();
	}

	/**
	 * Ajoute une categorie
	 * 
	 * @return La categorie cree
	 */
	@PostMapping("/cat")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addCategorie(@RequestBody Categorie newCategorie, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				categorieRepository.saveAndFlush(newCategorie);
				output = "success";
			}
		}
		return output;
	}

	/**
	 * Modifie une categorie
	 * 
	 * @param modifCategorie
	 * @return la categorie modifier
	 */
	@PutMapping("/cat")
	@ResponseStatus(code = HttpStatus.OK)
	public String updateCategorie(@RequestBody Categorie modifCategorie, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Categorie> verify = categorieRepository.findById(modifCategorie.getId());

				if (verify.isPresent()) {

					categorieRepository.saveAndFlush(modifCategorie);
					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * Supprime la categorie
	 * 
	 * @param delCategorie categorie a supprimé
	 * @return retourne un message en fonction du resultat de la requete
	 */
	@DeleteMapping("/cat")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteCategorie(@RequestBody Categorie delCategorie, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Categorie> verify = categorieRepository.findById(delCategorie.getId());

				if (verify.isPresent()) {

					categorieRepository.delete(delCategorie);
					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * Supprime une question
	 * 
	 * @param delQuestion : corresponds au Json transformé en objet question
	 */
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody Question delQuestion, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Question> verify = questionRepository.findById(delQuestion.getId());

				if (verify.isPresent()) {
					questionRepository.deleteByIdLink(delQuestion.getId());
					questionRepository.delete(delQuestion);
					output = "success";
				}
			}
		}
		return output;
	}
}