package fr.api.project.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.User;


/*
 * repository de l'entity utilisateur
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
