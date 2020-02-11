package fr.api.trivialCode.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.api.trivialCode.model.Faq;
import fr.api.trivialCode.repository.FaqRepository;
import fr.api.trivialCode.service.Authentification;

/**
 * Gere les path de la FAQ
 * 
 * @author Camille
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/faq")
public class FaqController {

	@Resource(name = "connexion")
	private Authentification auth;

	@Autowired
	private FaqRepository faqRepository;

	/**
	 * Rajoute une question a la Faq
	 * 
	 * @param faq la nouvelle question
	 * @return la question ajoutee
	 */
	@PostMapping
	public String ajoutFaq(@RequestBody Faq faq, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				faqRepository.saveAndFlush(faq);
				output = "success";
			}
		}
		return output;
	}

	/**
	 * Affiche la liste des questions
	 * 
	 * @return la liste des questions
	 */
	@GetMapping
	public List<Faq> afficherFaq() {
		return faqRepository.findAll();
	}

	/**
	 * Modifie une question
	 * 
	 * @param newFaq question modifie
	 */
	@PutMapping
	public String modifierFaq(@RequestBody Faq newFaq, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Faq> oldFaq = faqRepository.findById(newFaq.getId());
				if (oldFaq.isPresent()) {
					faqRepository.saveAndFlush(newFaq);
				}
				output = "success";
			}
		}
		return output;
	}

	/**
	 * Supprime une question
	 * 
	 * @param removeFaq question a supprimer
	 */
	@DeleteMapping
	public String supprimerFaq(@RequestBody Faq removeFaq, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Faq> oldFaq = faqRepository.findById(removeFaq.getId());
				if (oldFaq.isPresent()) {
					faqRepository.delete(removeFaq);
				}
				output = "success";
			}
		}
		return output;
	}
}
