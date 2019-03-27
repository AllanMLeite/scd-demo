package br.com.scd.demo.api.pauta.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.api.pauta.dto.PautaResponse;
import br.com.scd.demo.api.pauta.dto.PautaResponseFactory;
import br.com.scd.demo.pauta.Pauta;

public class PautaResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		Pauta pauta = new Pauta(12l, "subject");
		PautaResponse pautaResponse = PautaResponseFactory.getInstance(pauta);

		assertEquals("subject", pautaResponse.getSubject());
		assertEquals("12", pautaResponse.getId().toString());
	}
}
