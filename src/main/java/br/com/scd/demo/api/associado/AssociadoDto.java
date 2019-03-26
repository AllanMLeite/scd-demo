package br.com.scd.demo.api.associado;

import javax.validation.constraints.NotNull;

import lombok.Getter;

public class AssociadoDto {

	@Getter
	@NotNull(message = "Id do associado deve ser informado.")
	private Long id;
}
