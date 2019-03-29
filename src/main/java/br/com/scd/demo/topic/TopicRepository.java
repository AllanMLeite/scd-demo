package br.com.scd.demo.topic;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<TopicEntity, Long>{

	@Query("Select topic from TopicEntity topic"
			+ " left join fetch SessionEntity session on session.topic.id = topic.id"
			+ " left join fetch VoteEntity vote on session.id = vote.session.id"
			+ " where topic.id = :topicId")
	Optional<TopicEntity> findByIdWithSessionResult(@Param("topicId") Long topicId);
}
