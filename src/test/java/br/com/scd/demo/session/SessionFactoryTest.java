package br.com.scd.demo.session;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.session.Session;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionFactory;
import br.com.scd.demo.topic.TopicEntity;

public class SessionFactoryTest {

	@Test
	public void shouldCreateInstance() {
		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "id", 45l);

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "id", 20l);
		sessionEntity.setDurationInMinutes(10);
		sessionEntity.setTopic(topicEntity);

		Session session = SessionFactory.getInstance(sessionEntity);

		assertEquals("10", session.getDurationInMinutes().toString());
		assertEquals("45", session.getTopicId().toString());
		assertEquals("20", session.getId().toString());
	}
}
