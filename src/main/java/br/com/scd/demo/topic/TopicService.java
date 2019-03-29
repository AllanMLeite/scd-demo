package br.com.scd.demo.topic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.exception.TopicDoesntExistsException;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	public Topic save(TopicForInsert topicForInsert) {
		
		TopicEntity topicEntity = TopicEntityFactory.getInstance(topicForInsert);
		
		topicEntity = topicRepository.save(topicEntity);
		
		return TopicFactory.getInstance(topicEntity);
	}

	public TopicResult findByIdWithSessionResult(Long topicId) {
		
		Optional<TopicEntity> topicEntity = topicRepository.findByIdWithSessionResult(topicId);
		
		checkTopicExists(topicEntity);
		
		return TopicResultFactory.getInstance(topicEntity.get());
	}

	private void checkTopicExists(Optional<TopicEntity> topicEntity) {
		if(!topicEntity.isPresent()) {
			throw new TopicDoesntExistsException();
		}
	}
}
