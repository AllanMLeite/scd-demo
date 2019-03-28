package br.com.scd.demo.session;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionEntity, Long> {

	Optional<SessionEntity> findByTopicId(Long topicId);
}
