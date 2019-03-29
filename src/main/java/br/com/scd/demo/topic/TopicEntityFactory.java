package br.com.scd.demo.topic;

public final class TopicEntityFactory {

	private TopicEntityFactory() {

	}

	public static TopicEntity getInstance(TopicForInsert topicForInsert) {
		
		TopicEntity topicEntity = new TopicEntity();
		
		topicEntity.setSubject(topicForInsert.getSubject());
		
		return topicEntity;
	}

}
