package br.com.scd.demo.vote;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<VoteEntity, Long> {

	Optional<VoteEntity> findBySessionIdAndAssociatedId(Long sessionId, Long associatedId);
}
