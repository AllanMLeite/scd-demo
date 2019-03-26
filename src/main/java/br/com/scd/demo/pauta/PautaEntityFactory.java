package br.com.scd.demo.pauta;

public class PautaEntityFactory {

	private PautaEntityFactory() {
		// prevents instantiation
	}

	public static PautaEntity getInstance(PautaForInsert pautaForInsert) {
		PautaEntity pautaEntity = new PautaEntity();
		pautaEntity.setSubject(pautaForInsert.getSubject());
		return pautaEntity;
	}

}
