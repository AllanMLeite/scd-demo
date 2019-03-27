package br.com.scd.demo.topic;

import br.com.scd.demo.api.topic.dto.TopicRequest;

public class TopicForInsertFactory {

	private TopicForInsertFactory() {
		// prevents instantiation
	}

	public static TopicForInsert getInstance(TopicRequest request) {
		return new TopicForInsert(request.getSubject());
	}
}
