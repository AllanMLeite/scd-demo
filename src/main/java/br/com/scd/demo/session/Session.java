package br.com.scd.demo.session;

import org.springframework.util.Assert;

import lombok.Getter;

public class Session {

	@Getter
	private Long id;

	@Getter
	private Long topicId;

	@Getter
	private Integer durationInMinutes;

	public Session(Long id, Long topicId, Integer durationInMinutes) {

		Assert.notNull(id, "id cannot be null");
		Assert.notNull(topicId, "topicId cannot be null");
		Assert.notNull(durationInMinutes, "durationInMinutes cannot be null");

		this.id = id;
		this.topicId = topicId;
		this.durationInMinutes = durationInMinutes;
	}
}
