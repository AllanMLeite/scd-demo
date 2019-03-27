package br.com.scd.demo.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
