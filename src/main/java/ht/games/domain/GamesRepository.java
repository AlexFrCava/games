package ht.games.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GamesRepository extends CrudRepository<Games, Long> {
	List<Games> findByTitle(String title);

}
