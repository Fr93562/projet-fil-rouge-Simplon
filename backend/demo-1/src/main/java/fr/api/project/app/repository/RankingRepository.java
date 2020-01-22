package fr.api.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.api.project.app.model.entity.Ranking;

/*
 * Correspond au repository du classement
 */
@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {

}
