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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import fr.api.project.app.model.entity.Faq;
import fr.api.project.app.repository.FaqRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private FaqRepository faqRepository;
	
	/**
	 * Methode qui permet de rajouter une question a la Faq
	 * @param faq la nouvelle question
	 * @return la question ajout�e
	 */
	@PostMapping
	public Faq ajoutFaq(@RequestBody Faq faq) {
		return faqRepository.saveAndFlush(faq);
	}
	
	/**
	 * Methode qui permet d'afficher la liste des questions
	 * @return la liste des questions
	 */
	@GetMapping
	public List<Faq> afficherFaq() {
		return faqRepository.findAll();
	}
	
	/**
	 * Methode qui permet de modifier une question
	 * @param newFaq question modifie
	 */
	@PutMapping
	public void modifierFaq(@RequestBody Faq newFaq) {
		Optional<Faq> oldFaq = faqRepository.findById(newFaq.getId());
		  if(oldFaq.isPresent()) {
			  faqRepository.saveAndFlush(newFaq);			  
		  }
	}
	
	/**
	 * Methode qui permet de supprimer une question
	 * @param removeFaq question a supprimer
	 */
	@DeleteMapping("/supprimer")
	public void supprimerFaq(@RequestBody Faq removeFaq) {
		Optional<Faq> oldFaq = faqRepository.findById(removeFaq.getId());
		  if(oldFaq.isPresent()) {
			  faqRepository.delete(removeFaq);			  
		  }
	}
	
}
