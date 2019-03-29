package br.com.scd.demo.topic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.exception.TopicDoesntExistsException;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Override
	public Topic save(TopicForInsert topicForInsert) {
		TopicEntity topicEntity = TopicEntityFactory.getInstance(topicForInsert);
		topicEntity = topicRepository.save(topicEntity);
		return TopicFactory.getInstance(topicEntity);
	}

	@Override
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
