package br.com.scd.demo.topic;

import org.springframework.util.Assert;

import lombok.Getter;

public class TopicForInsert {

	@Getter
	private final String subject;
	
	public TopicForInsert(String subject) {
		
		Assert.hasLength(subject, "subject cannot be blank");
		
		this.subject = subject;
	}
}
