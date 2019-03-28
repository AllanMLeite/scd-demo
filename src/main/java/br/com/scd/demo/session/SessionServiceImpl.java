package br.com.scd.demo.session;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.topic.TopicRepository;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public Session save(SessionForInsert sessionForInsert) {

		Optional<TopicEntity> topicEntity = topicRepository.findById(sessionForInsert.getTopicId());

		checkTopicExists(topicEntity);
		
		checkSessionAlreadyOpened(sessionForInsert.getTopicId());

		SessionEntity sessionEntity = SessionEntityFactory.getInstance(sessionForInsert, topicEntity.get());
		
		sessionEntity = sessionRepository.save(sessionEntity);
		
		return SessionFactory.getInstance(sessionEntity);
	}

	private void checkSessionAlreadyOpened(Long topicId) {
		Optional<SessionEntity> sessionEntity = sessionRepository.findByTopicId(topicId);
		
		if(sessionEntity.isPresent()) {
			throw new IllegalArgumentException("Sessão já iniciada para a pauta informada.");
		}
	}

	private void checkTopicExists(Optional<TopicEntity> topicEntity) {
		if (!topicEntity.isPresent()) {
			throw new IllegalArgumentException("Id da pauta inexistente.");
		}
	}

	@Override
	public Optional<SessionEntity> findById(Long sessionId) {
		return sessionRepository.findById(sessionId);
	}
}
