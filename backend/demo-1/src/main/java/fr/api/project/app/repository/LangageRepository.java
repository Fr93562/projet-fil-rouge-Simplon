package fr.api.project.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.Langage;

/*
 * Correspond au repository des langages web
 */
@Repository
public interface LangageRepository extends JpaRepository<Langage, Integer> {


	Optional<Langage> findByLanguage(String langage);

	@Query(value = "INSERT INTO langage_question VALUES (?2, ?1)", nativeQuery = true)
	String insertLangageQuestion(int idQuestion, int idLangage);

}
