package br.com.scd.demo.topic;

import br.com.scd.demo.api.topic.dto.TopicRequest;

public final class TopicForInsertFactory {

	private TopicForInsertFactory() {

	}

	public static TopicForInsert getInstance(TopicRequest request) {
		return new TopicForInsert(request.getSubject());
	}
}
