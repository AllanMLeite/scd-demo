package br.com.scd.demo.session;

import br.com.scd.demo.topic.TopicEntity;

public class SessionEntityFactory {

	private SessionEntityFactory() {
		// prevents instantiation
	}
	
	public static SessionEntity getInstance(SessionForInsert sessionForInsert, TopicEntity topicEntity) {
		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setTopic(topicEntity);
		sessionEntity.setDurationInMinutes(sessionForInsert.getDurationInMinutes());
		return sessionEntity;
	}

}
