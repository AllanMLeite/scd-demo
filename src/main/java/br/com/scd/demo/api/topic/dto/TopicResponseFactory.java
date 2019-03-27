package br.com.scd.demo.api.topic.dto;

import br.com.scd.demo.topic.Topic;

public class TopicResponseFactory {

	private TopicResponseFactory() {
		// prevents instantiation
	}

	public static TopicResponse getInstance(Topic topic) {
		return new TopicResponse(topic.getId(), topic.getSubject());
	}
}
