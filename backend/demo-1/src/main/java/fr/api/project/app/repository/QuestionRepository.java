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

	@Query(value = "SELECT question_id FROM langage_question WHERE langage_id = ?1", nativeQuery = true)
	Optional<List<Integer>> findAllByLangageId(int langageid);
	
}
