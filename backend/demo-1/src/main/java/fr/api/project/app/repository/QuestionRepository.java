package fr.api.project.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> 
{

	/**
	 * Fourni la Liste des questions correspondant au langage
	 * @param langage langage a trouver
	 * @return Liste de question correspondant au langage
	 */
	@Query(value = "SELECT * FROM question LEFT JOIN langage_question lq ON question.id = lq.question_id WHERE lq.langage_id = (SELECT id FROM langage WHERE language=?1)", nativeQuery = true)
	Optional<List<Question>> findAllByLangage(String langage);

}
