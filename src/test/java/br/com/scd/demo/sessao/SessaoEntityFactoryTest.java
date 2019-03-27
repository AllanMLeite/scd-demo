package br.com.scd.demo.sessao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.pauta.PautaEntity;

public class SessaoEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		SessaoForInsert sessaoForInsert = new SessaoForInsert(45l, 1);
		PautaEntity pautaEntity = new PautaEntity();
		ReflectionTestUtils.setField(pautaEntity, "id", 45l);

		SessaoEntity sessaoEntity = SessaoEntityFactory.getInstance(sessaoForInsert, pautaEntity);

		assertEquals("45", sessaoEntity.getPauta().getId().toString());
		assertEquals("1", sessaoEntity.getDurationInMinutes().toString());

	}
}
