package br.com.scd.demo.session;

import br.com.scd.demo.topic.TopicEntity;

public final class SessionEntityFactory {

	private SessionEntityFactory() {
		
	}
	
	public static SessionEntity getInstance(SessionForInsert sessionForInsert, TopicEntity topicEntity) {
		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setTopic(topicEntity);
		sessionEntity.setDurationInMinutes(sessionForInsert.getDurationInMinutes());
		return sessionEntity;
	}
}
