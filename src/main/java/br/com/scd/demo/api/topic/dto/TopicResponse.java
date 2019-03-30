package br.com.scd.demo.api.topic.dto;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TopicResponse {

	private final Long id;

	private final String subject;

	public TopicResponse(Long id, String subject) {

		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(subject, "subject cannot be blank");

		this.id = id;
		this.subject = subject;
	}
}
