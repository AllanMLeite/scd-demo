package br.com.scd.demo.topic;

public class TopicEntityFactory {

	private TopicEntityFactory() {
		// prevents instantiation
	}

	public static TopicEntity getInstance(TopicForInsert topicForInsert) {
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setSubject(topicForInsert.getSubject());
		return topicEntity;
	}

}
