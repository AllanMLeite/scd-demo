package br.com.scd.demo.topic;

public interface TopicService {

	public Topic save(TopicForInsert topicForInsert);
	
	public TopicResult findByIdWithSessionResult(Long topicId);
}
