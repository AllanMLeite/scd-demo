package br.com.scd.demo.topic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.api.topic.dto.TopicRequest;
import br.com.scd.demo.topic.TopicForInsert;
import br.com.scd.demo.topic.TopicForInsertFactory;

public class TopicForInsertFactoryTest {

	@Test
	public void shouldCreateInstance() {
		TopicRequest topicRequest = new TopicRequest();
		ReflectionTestUtils.setField(topicRequest, "subject", "xyz");

		TopicForInsert topicForInsert = TopicForInsertFactory.getInstance(topicRequest);

		assertEquals("xyz", topicForInsert.getSubject());
	}
}
