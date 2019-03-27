package br.com.scd.demo.topic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.topic.TopicEntityFactory;
import br.com.scd.demo.topic.TopicForInsert;

public class TopicEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		TopicForInsert topicForInsert = new TopicForInsert("xyz");

		TopicEntity topicEntity = TopicEntityFactory.getInstance(topicForInsert);

		assertEquals("xyz", topicEntity.getSubject());
	}
}
