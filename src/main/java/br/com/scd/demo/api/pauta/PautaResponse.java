package br.com.scd.demo.api.pauta;

import java.util.List;

import org.springframework.util.Assert;

import br.com.scd.demo.associado.Associado;
import lombok.Getter;

public class PautaResponse {

	@Getter
	private final Long id;

	@Getter
	private final String subject;

	@Getter
	private final List<Associado> associateds;

	public PautaResponse(Long id, String subject, List<Associado> associateds) {

		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(subject, "subject cannot be blank");
		Assert.notEmpty(associateds, "associateds cannot be empty");

		this.id = id;
		this.subject = subject;
		this.associateds = associateds;
	}
}
