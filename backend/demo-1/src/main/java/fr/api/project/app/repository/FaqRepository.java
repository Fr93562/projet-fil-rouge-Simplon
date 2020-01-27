package fr.api.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.Faq;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> 
{

}
