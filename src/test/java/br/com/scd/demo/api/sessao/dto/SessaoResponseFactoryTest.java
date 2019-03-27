package br.com.scd.demo.api.sessao.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.sessao.Sessao;

public class SessaoResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		Sessao sessao = new Sessao(12l, 15l, 10);
		SessaoResponse sessaoResponse = SessaoResponseFactory.getInstance(sessao);

		assertEquals("12", sessaoResponse.getId().toString());
		assertEquals("15", sessaoResponse.getPautaId().toString());
		assertEquals("10", sessaoResponse.getDurationInMinutes().toString());
	}
}
