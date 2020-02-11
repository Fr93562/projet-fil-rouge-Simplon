package fr.api.trivialCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.api.trivialCode.model.Faq;


@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> 
{

}
