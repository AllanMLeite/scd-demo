package br.com.scd.demo.sessao;

import java.util.Objects;

import org.springframework.util.Assert;

import lombok.Getter;

public class SessaoForInsert {

	private static final Integer DEFAULT_DURATION_IN_MINUTES = 1;

	@Getter
	private Long pautaId;

	@Getter
	private Integer durationInMinutes;

	public SessaoForInsert(Long pautaId, Integer durationInMinutes) {

		Assert.notNull(pautaId, "pautaId cannot be null");

		if (Objects.isNull(durationInMinutes)) {
			durationInMinutes = DEFAULT_DURATION_IN_MINUTES;
		}

		this.pautaId = pautaId;
		this.durationInMinutes = durationInMinutes;
	}
}
