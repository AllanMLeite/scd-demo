package br.com.scd.demo.api.pauta;

import br.com.scd.demo.pauta.Pauta;

public class PautaResponseFactory {

	private PautaResponseFactory() {
		// prevents instantiation
	}

	public static PautaResponse getInstance(Pauta pauta) {
		return new PautaResponse(pauta.getId(), pauta.getSubject(), pauta.getAssociateds());
	}
}
