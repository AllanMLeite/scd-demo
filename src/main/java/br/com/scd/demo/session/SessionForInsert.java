package br.com.scd.demo.session;

import java.util.Objects;

import org.springframework.util.Assert;

import lombok.Getter;

public class SessionForInsert {

	private static final Integer DEFAULT_DURATION_IN_MINUTES = 1;

	@Getter
	private Long topicId;

	@Getter
	private Integer durationInMinutes;

	public SessionForInsert(Long topicId, Integer durationInMinutes) {

		Assert.notNull(topicId, "topicId cannot be null");

		if (Objects.isNull(durationInMinutes)) {
			durationInMinutes = DEFAULT_DURATION_IN_MINUTES;
		}

		this.topicId = topicId;
		this.durationInMinutes = durationInMinutes;
	}
}
