package br.com.scd.demo.sessao;

import org.springframework.util.Assert;

import lombok.Getter;

public class Sessao {

	@Getter
	private Long id;

	@Getter
	private Long pautaId;

	@Getter
	private Integer durationInMinutes;

	public Sessao(Long id, Long pautaId, Integer durationInMinutes) {

		Assert.notNull(id, "id cannot be null");
		Assert.notNull(pautaId, "pautaId cannot be null");
		Assert.notNull(durationInMinutes, "durationInMinutes cannot be null");

		this.id = id;
		this.pautaId = pautaId;
		this.durationInMinutes = durationInMinutes;
	}
}