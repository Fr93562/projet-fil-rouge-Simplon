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

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.api.project.app.model.entity.Langage;
import fr.api.project.app.model.entity.Question;
import fr.api.project.app.repository.LangageRepository;
import fr.api.project.app.repository.QuestionRepository;

@CrossOrigin("*")
@RestController
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private LangageRepository langageRepository;



	/**
	 * Ajoute une question
	 * @param newQuestion La question à ajouter
	 * @return La question ajouté
	 */
	@PostMapping("/question")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Question addQuestion(@RequestBody Question newQuestion) {

		return questionRepository.saveAndFlush(newQuestion);
	}

	@RequestMapping(value = "/question/", params = {"langage"})
	public List<Question> listQuestion(String langage) {
		
		List<Question> output = null;
		Optional<Langage> language = langageRepository.findByLanguage(langage);
		
		if (language.isPresent()) {
			Optional<List<Integer>> questionList = questionRepository.findAllByLangageId(language.get().getId());
			
			  if(questionList.isPresent()) { 
				  List<Integer> list = questionList.get();
				  output = questionRepository.findAllById(list);
			  }
		}
		
		return output;
	}

	/**
	 * Renvoie toutes les questions au format Json 
	 * 
	 * @return : renvoie une liste en Json
	 */
	@GetMapping("/question")
	public List<Question> readAll() {

		return questionRepository.findAll();
	}

	/**
	 * Mets a jour une question
	 * 
	 * @param modifQuestion
	 * @return : une reponse en fonction de l'existence
	 */
	@PutMapping("/question")
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
	@DeleteMapping("/question")
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
}