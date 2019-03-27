package br.com.scd.demo.api.topic.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.api.topic.dto.TopicResponse;
import br.com.scd.demo.api.topic.dto.TopicResponseFactory;
import br.com.scd.demo.topic.Topic;

public class TopicResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		Topic topic = new Topic(12l, "subject");
		TopicResponse topicResponse = TopicResponseFactory.getInstance(topic);

		assertEquals("subject", topicResponse.getSubject());
		assertEquals("12", topicResponse.getId().toString());
	}
}
