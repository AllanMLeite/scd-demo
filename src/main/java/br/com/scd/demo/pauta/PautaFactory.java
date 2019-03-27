package br.com.scd.demo.pauta;

public class PautaFactory {

	private PautaFactory() {
		// prevents instantiation
	}

	public static Pauta getInstance(PautaEntity pautaEntity) {
		return new Pauta(pautaEntity.getId(), pautaEntity.getSubject());
	}
}
