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

import fr.api.trivialCode.model.Faq;
import fr.api.trivialCode.model.Langage;
import fr.api.trivialCode.model.Question;
import fr.api.trivialCode.repository.LangageRepository;
import fr.api.trivialCode.service.Authentification;

/**
 * Gere les paths de langage
 * 
 * @author FrancoisMacko
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/langages")
public class LangageController {

	@Resource(name = "connexion")
	private Authentification auth;

	@Autowired
	private LangageRepository langageRepository2;

	/**
	 * Ajoute un langage
	 * 
	 * @param langageData langage a ajoute
	 * @return le langage ajoute
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public String create(@RequestBody Langage langageData, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				langageRepository2.saveAndFlush(langageData);
				output = "success";
			}
		}
		return output;
	}

	/**
	 * Cherche un langage par nom
	 * 
	 * @param langage nom du langage
	 * @return langage trouver ou pas
	 */
	@RequestMapping(params = { "langage" })
	public Optional<Langage> read(String langage) {

		Optional<Langage> output = null;
		Optional<Langage> verify = langageRepository2.findByLanguage(langage);

		if (verify.isPresent()) {

			output = verify;
		}

		return output;
	}

	/**
	 * Recupere la liste des langages
	 * 
	 * @return Liste des Langages
	 */
	@GetMapping
	public List<Langage> readAll() {

		return langageRepository2.findAll();
	}

	/**
	 * Modifie un langage
	 * 
	 * @param langageData langage a modifie
	 * @return Message en fonction de la reussite de la requete
	 */
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String update(@RequestBody Langage langageData, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Langage> verify = langageRepository2.findById(langageData.getId());

				if (verify.isPresent()) {
					Langage LangageInter = verify.get();

					langageData.setQuestion(LangageInter.getQuestion());
					langageRepository2.saveAndFlush(langageData);

					output = "success";
				}
			}
		}
		return output;
	}

	/**
	 * Supprime un utilisateur en base de Bdd La réponse dépend de son existence en
	 * Bdd
	 * 
	 * @param userData : corresponds au Json transformé en objet user
	 */
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String delete(@RequestBody Langage langageData, HttpServletRequest request) {

		String output = "unauthorized";

		if (request.getHeader("token") != null) {

			auth.setToken(request.getHeader("token"));

			if (auth.verify()[1].equalsIgnoreCase("Administrateur")) {

				Optional<Langage> verify = langageRepository2.findById(langageData.getId());

				if (verify.isPresent()) {

					langageRepository2.delete(langageData);
					output = "success";
				}
			}
		}
		return output;
	}
}