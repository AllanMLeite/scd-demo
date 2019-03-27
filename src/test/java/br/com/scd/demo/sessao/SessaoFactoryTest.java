package br.com.scd.demo.sessao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.pauta.PautaEntity;

public class SessaoFactoryTest {

	@Test
	public void shouldCreateInstance() {
		PautaEntity pautaEntity = new PautaEntity();
		ReflectionTestUtils.setField(pautaEntity, "id", 45l);

		SessaoEntity sessaoEntity = new SessaoEntity();
		ReflectionTestUtils.setField(sessaoEntity, "id", 20l);
		sessaoEntity.setDurationInMinutes(10);
		sessaoEntity.setPauta(pautaEntity);

		Sessao sessao = SessaoFactory.getInstance(sessaoEntity);

		assertEquals("10", sessao.getDurationInMinutes().toString());
		assertEquals("45", sessao.getPautaId().toString());
		assertEquals("20", sessao.getId().toString());
	}
}
