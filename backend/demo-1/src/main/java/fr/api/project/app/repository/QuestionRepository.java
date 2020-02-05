package fr.api.project.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.api.project.app.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	/**
	 * Fourni la Liste des questions correspondant au langage
	 * 
	 * @param langage langage a trouver
	 * @return Liste de questions correspondant au langage
	 */
	@Query(value = "SELECT * FROM question LEFT JOIN langage_question lq ON question.id = lq.question_id WHERE lq.langage_id = (SELECT id FROM langage WHERE language=?1)", nativeQuery = true)
	Optional<List<Question>> findAllByLangage(String langage); // TODO est-ce que cette méthode est encore utilisée?

	/**
	 * Fourni la Liste des questions correspondant a la ressource
	 * 
	 * @param ressources a trouver
	 * @return Liste de questions correspondant a la ressource
	 */
	@Query(value = "SELECT * FROM question left join ressource src on question.ressource_id = src.id where src.id = ?1", nativeQuery = true)
	Optional<List<Question>> findAllByRessources(int ressources);

	/**
	 * Supprime les FK dans la table de liaison langage_question
	 * 
	 * @param id Id de la question
	 */
	@Transactional //src = https://dzone.com/articles/how-does-spring-transactional
	@Modifying //src = https://www.baeldung.com/spring-data-jpa-modifying-annotation
	@Query(value = "DELETE FROM langage_question WHERE question_id = ?1", nativeQuery = true)
	void deleteByIdLink(int id);

}
