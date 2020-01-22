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

import fr.api.project.app.model.entity.Langage;
import fr.api.project.app.repository.LangageRepository;



/**
 * Gere les paths de User
 * 
 * @author FrancoisMacko
 */
@CrossOrigin("*")
@RestController
public class LangageController {

	@Autowired
	private LangageRepository langageRepository2;
	

	@PostMapping("/langages")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Langage create(@RequestBody Langage langageData) {
		
		return langageRepository2.saveAndFlush(langageData);
	}
	

	@RequestMapping(value = "/langages/", params = {"langage"})
	public Optional<Langage> read(String langage) {
		
		Optional<Langage> output = null;
		Optional<Langage> verify = langageRepository2.findByLanguage(langage);
		
		  if(verify.isPresent()) {
			  
			  output = langageRepository2.findByLanguage(langage);
		  }
		
		return output;
	}
	

	@GetMapping("/langages")
	public List<Langage> readAll() {
		
		return  langageRepository2.findAll();
	}
	

	@PutMapping("/langages")
	@ResponseStatus(code = HttpStatus.OK)
	public String update(@RequestBody Langage langageData) {
		
		String output = "Langage not found";
		Optional<Langage> verify = langageRepository2.findById(langageData.getId());
		
		  if(verify.isPresent()) {
			  
			  Optional<Langage> langageInter = langageRepository2.findById(langageData.getId());
			  Langage LangageInter = langageInter.get();
			  
			  langageData.setQuestion(LangageInter.getQuestion());
			  langageRepository2.saveAndFlush(langageData);			
			  
			  output = "Langage has been update";
		  }
		  
		  return output;
	}
	
	/**
	 * Supprime un utilisateur en base de Bdd
	 * La réponse dépend de son existence en Bdd
	 * 
	 * @param userData : corresponds au Json transformé en objet user
	 */
	@DeleteMapping("/langages")
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody Langage langageData) {
		
		String output = "User not found";
		Optional<Langage> verify = langageRepository2.findById(langageData.getId());
		
		if(verify.isPresent()) {
		
			langageRepository2.delete(langageData);	
			output = "User has been delete";
		}
		return output;
	}
}