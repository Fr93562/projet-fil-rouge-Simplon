package fr.api.trivialCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.api.trivialCode.model.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> 
{

}
