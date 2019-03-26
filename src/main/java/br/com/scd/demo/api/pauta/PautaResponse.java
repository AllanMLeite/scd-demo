package br.com.scd.demo.api.pauta;

import org.springframework.util.Assert;

import lombok.Getter;

public class PautaResponse {

	@Getter
	private final Long id;

	@Getter
	private final String subject;

	public PautaResponse(Long id, String subject) {

		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(subject, "subject cannot be blank");

		this.id = id;
		this.subject = subject;
	}
}
