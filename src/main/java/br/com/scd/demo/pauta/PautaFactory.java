package br.com.scd.demo.pauta;

import javax.validation.Valid;

import br.com.scd.demo.api.pauta.PautaRequest;

public class PautaFactory {

	private PautaFactory() {
		// prevents instantiation
	}

	public static PautaForInsert getInstance(@Valid PautaRequest request) {
		return new PautaForInsert(request.getSubject());
	}

	public static Pauta getInstance(PautaEntity pautaEntity) {
		return new Pauta(pautaEntity.getId(), pautaEntity.getSubject());
	}
}
