package br.com.scd.demo.topic;

public class TopicFactory {

	private TopicFactory() {
		// prevents instantiation
	}

	public static Topic getInstance(TopicEntity topicEntity) {
		return new Topic(topicEntity.getId(), topicEntity.getSubject());
	}
}
