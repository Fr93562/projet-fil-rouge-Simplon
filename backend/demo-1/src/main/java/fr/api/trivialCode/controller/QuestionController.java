package fr.api.trivialCode.controller;

import java.util.Collection;
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

import fr.api.trivialCode.model.Categorie;
import fr.api.trivialCode.model.Langage;
import fr.api.trivialCode.model.Question;
import fr.api.trivialCode.model.ResponseObject;
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
	public ResponseEntity<ResponseObject> addQuestion(@RequestBody Question newQuestion, String langage, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Question saveQuestion = questionRepository.saveAndFlush(newQuestion);
				Optional<Langage> langageVerif = langageRepository.findByLanguage(langage);

				if (langageVerif.isPresent()) {

					Langage langageComplet = langageVerif.get();
					langageComplet.getQuestion().add(saveQuestion);
					langageRepository.saveAndFlush(langageComplet);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.CREATED);
				}
			}
		}
		return ResponseEntity.badRequest().build();
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
	public ResponseEntity<ResponseObject> updateQuestion(@RequestBody Question modifQuestion, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Question> verify = questionRepository.findById(modifQuestion.getId());

				if (verify.isPresent()) {

					questionRepository.saveAndFlush(modifQuestion);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
				}
			}
		}
		return ResponseEntity.badRequest().build();
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
	public ResponseEntity<ResponseObject> addCategorie(@RequestBody Categorie newCategorie, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				categorieRepository.saveAndFlush(newCategorie);
				return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
			}
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * Modifie une categorie
	 * 
	 * @param modifCategorie
	 * @return la categorie modifier
	 */
	@PutMapping("/cat")
	public ResponseEntity<ResponseObject> updateCategorie(@RequestBody Categorie modifCategorie, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Categorie> verify = categorieRepository.findById(modifCategorie.getId());

				if (verify.isPresent()) {

					categorieRepository.saveAndFlush(modifCategorie);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}

	/**
	 * Supprime une question
	 * 
	 * @param delQuestion : corresponds au Json transformé en objet question
	 */
	@DeleteMapping
	public ResponseEntity<ResponseObject> delete(@RequestBody Question delQuestion, HttpServletRequest request) {

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Question> verify = questionRepository.findById(delQuestion.getId());

				if (verify.isPresent()) {
					questionRepository.deleteByIdLink(delQuestion.getId());
					questionRepository.delete(delQuestion);
					return new ResponseEntity<ResponseObject>(new ResponseObject("Success"),HttpStatus.OK);
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}
}