package ht.games.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
	List<Status> findByStatusQuo(String statusQuo);

}
