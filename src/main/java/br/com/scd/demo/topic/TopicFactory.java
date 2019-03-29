package br.com.scd.demo.topic;

public final class TopicFactory {

	private TopicFactory() {

	}

	public static Topic getInstance(TopicEntity topicEntity) {
		return new Topic(topicEntity.getId(), topicEntity.getSubject());
	}
}
