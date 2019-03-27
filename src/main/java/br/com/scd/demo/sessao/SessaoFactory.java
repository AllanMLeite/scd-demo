package br.com.scd.demo.sessao;

public class SessaoFactory {

	private SessaoFactory() {
		// prevents instantiation
	}

	public static Sessao getInstance(SessaoEntity sessaoEntity) {
		return new Sessao(sessaoEntity.getId(), sessaoEntity.getPauta().getId(), sessaoEntity.getDurationInMinutes());
	}

}
