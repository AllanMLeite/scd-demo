package br.com.scd.demo.associado;

import br.com.scd.demo.api.associado.AssociadoDto;

public class AssociadoFactory {

	private AssociadoFactory() {
		// prevents instantiation
	}

	public static Associado getInstance(AssociadoDto associadoDto) {
		return new Associado(associadoDto.getId());
	}
}
