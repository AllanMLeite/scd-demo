package br.com.scd.demo.session;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionEntityFactory;
import br.com.scd.demo.session.SessionForInsert;
import br.com.scd.demo.topic.TopicEntity;

public class SessionEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		SessionForInsert sessionForInsert = new SessionForInsert(45l, 1);
		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "id", 45l);

		SessionEntity sessionEntity = SessionEntityFactory.getInstance(sessionForInsert, topicEntity);

		assertEquals("45", sessionEntity.getTopic().getId().toString());
		assertEquals("1", sessionEntity.getDurationInMinutes().toString());

	}
}
