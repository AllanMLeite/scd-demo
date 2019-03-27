package br.com.scd.demo.sessao;

import br.com.scd.demo.pauta.PautaEntity;

public class SessaoEntityFactory {

	private SessaoEntityFactory() {
		// prevents instantiation
	}
	
	public static SessaoEntity getInstance(SessaoForInsert sessaoForInsert, PautaEntity pautaEntity) {
		SessaoEntity sessaoEntity = new SessaoEntity();
		sessaoEntity.setPauta(pautaEntity);
		sessaoEntity.setDurationInMinutes(sessaoForInsert.getDurationInMinutes());
		return sessaoEntity;
	}

}
