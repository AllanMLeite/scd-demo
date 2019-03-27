package br.com.scd.demo.api.session.dto;

import org.springframework.util.Assert;

import lombok.Getter;

public class SessionResponse {

	@Getter
	private Long id;

	@Getter
	private Long topicId;

	@Getter
	private Integer durationInMinutes;

	public SessionResponse(Long id, Long topicId, Integer durationInMinutes) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.notNull(topicId, "topicId cannot be null");
		Assert.notNull(durationInMinutes, "durationInMinutes cannot be null");
		
		this.id = id;
		this.topicId = topicId;
		this.durationInMinutes = durationInMinutes;
	}
}
