package br.com.scd.demo.api.pauta;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.scd.demo.api.associado.AssociadoDto;
import lombok.Getter;

public class PautaRequest {

	@Getter
	@Size(max = 100, message = "Assunto nao deve ultrapassar 100 caracteres.")
	@NotBlank(message = "Assunto deve ser informado.")
	private String subject;

	@Getter
	@Valid
	@NotEmpty(message = "Lista de associados deve ser informada.")
	private List<AssociadoDto> associateds;
}
