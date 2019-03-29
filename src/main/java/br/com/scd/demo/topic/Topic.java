package br.com.scd.demo.topic;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class Topic {

	private final Long id;
	
	private final String subject;
	
	public Topic(Long id, String subject) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(subject, "subject cannot be blank");
		
		this.id = id;
		this.subject = subject;
	}
}
