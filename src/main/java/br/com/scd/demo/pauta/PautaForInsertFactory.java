package br.com.scd.demo.pauta;

import javax.validation.Valid;

import br.com.scd.demo.api.pauta.dto.PautaRequest;

public class PautaForInsertFactory {

	private PautaForInsertFactory() {
		// prevents instantiation
	}

	public static PautaForInsert getInstance(@Valid PautaRequest request) {
		return new PautaForInsert(request.getSubject());
	}
}
