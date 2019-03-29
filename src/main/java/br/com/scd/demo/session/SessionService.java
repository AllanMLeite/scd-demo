package br.com.scd.demo.session;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.exception.SessionAlreadyOpenedException;
import br.com.scd.demo.exception.TopicDoesntExistsException;
import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.topic.TopicRepository;

@Service
public class SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TopicRepository topicRepository;

	public Session save(SessionForInsert sessionForInsert) {

		Optional<TopicEntity> topicEntity = topicRepository.findById(sessionForInsert.getTopicId());

		validateTopic(sessionForInsert, topicEntity);

		SessionEntity sessionEntity = SessionEntityFactory.getInstance(sessionForInsert, topicEntity.get());
		
		sessionEntity = sessionRepository.save(sessionEntity);
		
		return SessionFactory.getInstance(sessionEntity);
	}

	private void validateTopic(SessionForInsert sessionForInsert, Optional<TopicEntity> topicEntity) {
		checkTopicExists(topicEntity);
		checkSessionAlreadyOpened(sessionForInsert.getTopicId());
	}

	private void checkSessionAlreadyOpened(Long topicId) {
		Optional<SessionEntity> sessionEntity = sessionRepository.findByTopicId(topicId);
		
		if(sessionEntity.isPresent()) {
			throw new SessionAlreadyOpenedException();
		}
	}

	private void checkTopicExists(Optional<TopicEntity> topicEntity) {
		if (!topicEntity.isPresent()) {
			throw new TopicDoesntExistsException();
		}
	}

	public Optional<SessionEntity> findById(Long sessionId) {
		return sessionRepository.findById(sessionId);
	}
}
