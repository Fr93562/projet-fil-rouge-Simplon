package fr.api.project.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import fr.api.project.app.model.entity.Faq;
import fr.api.project.app.repository.FaqRepository;


@CrossOrigin("*")
@RestController
public class FaqController {

	/**
	 * Commentaires: CRUD complet + (readAll) 1iere modif d'une branche
	 */
	@Autowired
	private FaqRepository faqRepository;
	
	
	
	
	/**
	 * méthode qui permet de rajouter une question à la Faq
	 * @param faq la nouvelle question
	 * @return la question ajoutée
	 */
	@PostMapping("/faq/ajout")
	public Faq ajoutFaq(@RequestBody Faq faq) {
		return faqRepository.saveAndFlush(faq);
	}
	
	/**
	 * méthode qui permet d'afficher la liste des questions
	 * @return la liste des questions
	 */
	@GetMapping("/faq/afficher")
	public List<Faq> afficherFaq() {
		return faqRepository.findAll();
	}
	
	/**
	 * méthode qui permet de modifier une question
	 * @param newFaq question modifiée
	 */
	@PutMapping("/faq/modifier")
	public void modifierFaq(@RequestBody Faq newFaq) {
		Optional<Faq> oldFaq = faqRepository.findById(newFaq.getId());
		  if(oldFaq.isPresent()) {
			  faqRepository.saveAndFlush(newFaq);			  
		  }
	}
	
	/**
	 * méthode qui permet de supprimer une question
	 * @param removeFaq question à supprimer
	 */
	@DeleteMapping("/faq/supprimer")
	public void supprimerFaq(@RequestBody Faq removeFaq) {
		Optional<Faq> oldFaq = faqRepository.findById(removeFaq.getId());
		  if(oldFaq.isPresent()) {
			  faqRepository.delete(removeFaq);			  
		  }
	}
	
}
