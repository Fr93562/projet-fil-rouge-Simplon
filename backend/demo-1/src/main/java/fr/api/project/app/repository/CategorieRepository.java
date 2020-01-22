package fr.api.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> 
{

}
