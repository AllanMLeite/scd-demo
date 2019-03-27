package br.com.scd.demo.pauta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PautaFactoryTest {

	@Test
	public void shouldCreateInstance() {
		PautaForInsert pautaForInsert = new PautaForInsert("xyz");

		PautaEntity pautaEntity = PautaEntityFactory.getInstance(pautaForInsert);

		assertEquals("xyz", pautaEntity.getSubject());
	}
}
