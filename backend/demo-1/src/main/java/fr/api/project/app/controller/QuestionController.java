package fr.api.project.app.controller;

import java.util.ArrayList;
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

import fr.api.project.app.model.entity.Categorie;
import fr.api.project.app.model.entity.Langage;
import fr.api.project.app.model.entity.Question;
import fr.api.project.app.repository.CategorieRepository;
import fr.api.project.app.repository.LangageRepository;
import fr.api.project.app.repository.QuestionRepository;

/**
 * Controller gerant les Endpoint Question et Categorie
 * @author Camille
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	/**
	 * Attribut
	 */
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;

	/**
	 * Ajoute une question
	 * @param newQuestion La question à ajouter
	 * @return La question ajouté
	 */
	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Question addQuestion(@RequestBody Question newQuestion) {
		return questionRepository.saveAndFlush(newQuestion);
	}

	/**
	 * Retourne les questions correspondant au type de langage en parametr
	 * @param langage langage recherche
	 * @return Liste de questions en JSON
	 */
	@RequestMapping(value = "", params = {"langage"})
	public List<Question> listQuestion(String langage) {

		Optional<List<Question>> questionList = questionRepository.findAllByLangage(langage);
		if(questionList.isPresent()) {
			return questionList.get();
		}
		return new ArrayList<Question>();
	}

	/**
	 * Renvoie toutes les questions au format Json 
	 * 
	 * @return : renvoie une liste en Json
	 */
	@GetMapping("")
	public List<Question> readAll() {

		return questionRepository.findAll();
	}

	/**
	 * Mets a jour une question
	 * 
	 * @param modifQuestion la question modifie
	 * @return Un String contenant le resultat de la requete
	 */
	@PutMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public String updateQuestion(@RequestBody Question modifQuestion) {

		String output = "Question not found";
		Optional<Question> verify = questionRepository.findById(modifQuestion.getId());

		  if(verify.isPresent()) {

			  questionRepository.saveAndFlush(modifQuestion);			  
			  output = "Question has been update";
		  }
		  return output;
	}

	/**
	 * Supprime une question
	 * 
	 * @param delQuestion : corresponds au Json transformé en objet question
	 */
	@DeleteMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody Question delQuestion) {

		String output = "Question not found";
		Optional<Question> verify = questionRepository.findById(delQuestion.getId());

		if(verify.isPresent()) {

			questionRepository.delete(delQuestion);	
			output = "Question has been delete";
		}
		return output;
	}
	
	/**
	 * Liste toutes les categories
	 * @return La liste des categories
	 */
	@GetMapping("/cat")
	public List<Categorie> getCategorie(){
		return categorieRepository.findAll();
	}
	
	/**
	 * Ajoute une categorie
	 * @return La categorie cree
	 */
	@PostMapping("/cat")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Categorie addCategorie(@RequestBody Categorie newCategorie){
		return categorieRepository.saveAndFlush(newCategorie);
	}
	
	/**
	 * Modifie une categorie
	 * @param modifCategorie
	 * @return la categorie modifier
	 */
	@PutMapping("/cat")
	@ResponseStatus(code = HttpStatus.OK)
	public String updateCategorie(@RequestBody Categorie modifCategorie) {

		String output = "Categorie not found";
		Optional<Categorie> verify = categorieRepository.findById(modifCategorie.getId());

		  if(verify.isPresent()) {

			  categorieRepository.saveAndFlush(modifCategorie);			  
			  output = "Categorie has been update";
		  }
		  return output;
	}
	
	/**
	 * Supprime la categorie 
	 * @param delCategorie categorie a supprimé
	 * @return retourne un message en fonction du resultat de la requete
	 */
	@DeleteMapping("/cat")
	@ResponseStatus(code = HttpStatus.OK)
	public String deleteCategorie(@RequestBody Categorie delCategorie) {

		String output = "Categorie not found";
		Optional<Categorie> verify = categorieRepository.findById(delCategorie.getId());

		if(verify.isPresent()) {

			categorieRepository.delete(delCategorie);	
			output = "Categorie has been delete";
		}
		return output;
	}
}