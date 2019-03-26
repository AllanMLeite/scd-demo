package br.com.scd.demo.api.pauta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

public class PautaRequest {

	@Getter
	@Size(max = 100, message = "Assunto nao deve ultrapassar 100 caracteres.")
	@NotBlank(message = "Assunto deve ser informado.")
	private String subject;
}
