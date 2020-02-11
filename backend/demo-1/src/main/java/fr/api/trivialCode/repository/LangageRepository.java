package fr.api.trivialCode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.api.trivialCode.model.Langage;

/*
 * Correspond au repository des langages web
 */
@Repository
public interface LangageRepository extends JpaRepository<Langage, Integer> {


	Optional<Langage> findByLanguage(String langage);
}
