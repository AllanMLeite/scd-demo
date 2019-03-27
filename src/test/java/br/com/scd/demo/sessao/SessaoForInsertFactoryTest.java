package br.com.scd.demo.sessao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.api.sessao.dto.SessaoRequest;

public class SessaoForInsertFactoryTest {

	@Test
	public void shouldCreateInstance() {

		SessaoRequest sessaoRequest = new SessaoRequest();
		ReflectionTestUtils.setField(sessaoRequest, "pautaId", 20l);
		ReflectionTestUtils.setField(sessaoRequest, "durationInMinutes", 40);

		SessaoForInsert sessaoForInsert = SessaoForInsertFactory.getInstance(sessaoRequest);

		assertEquals("40", sessaoForInsert.getDurationInMinutes().toString());
		assertEquals("20", sessaoForInsert.getPautaId().toString());
	}
}
