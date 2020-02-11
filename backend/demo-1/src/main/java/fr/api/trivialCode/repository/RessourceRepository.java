package fr.api.trivialCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.api.trivialCode.model.Ressource;


@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Integer> 
{

}
