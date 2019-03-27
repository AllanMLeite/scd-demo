package br.com.scd.demo.sessao;

import br.com.scd.demo.api.sessao.dto.SessaoRequest;

public class SessaoForInsertFactory {

	private SessaoForInsertFactory() {
		// prevents instantiation
	}

	public static SessaoForInsert getInstance(SessaoRequest request) {
		return new SessaoForInsert(request.getPautaId(), request.getDurationInMinutes());
	}
}
