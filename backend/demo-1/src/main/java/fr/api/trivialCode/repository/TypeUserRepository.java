package fr.api.trivialCode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.api.trivialCode.model.TypeUser;


/*
 * Correspond au repository des types d'utilisateur
 */
@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Integer> {

	TypeUser findByType(String string);

}
