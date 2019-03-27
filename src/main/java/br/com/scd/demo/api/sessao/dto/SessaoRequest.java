package br.com.scd.demo.api.sessao.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

public class SessaoRequest {

	@Getter
	@NotNull(message = "Id da pauta deve ser informado.")
	private Long pautaId;

	@Getter
	private Integer durationInMinutes;
}
