package br.com.scd.demo.pauta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.pauta.PautaEntity;
import br.com.scd.demo.pauta.PautaEntityFactory;
import br.com.scd.demo.pauta.PautaForInsert;

public class PautaEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		PautaForInsert pautaForInsert = new PautaForInsert("xyz");

		PautaEntity pautaEntity = PautaEntityFactory.getInstance(pautaForInsert);

		assertEquals("xyz", pautaEntity.getSubject());
	}
}
