package br.com.scd.demo.api.sessao.dto;

import br.com.scd.demo.sessao.Sessao;

public class SessaoResponseFactory {

	private SessaoResponseFactory() {
		// prevents instantiation
	}
	
	public static SessaoResponse getInstance(Sessao sessao) {
		return new SessaoResponse(sessao.getId(), sessao.getPautaId(), sessao.getDurationInMinutes());
	}

}
