package br.com.scd.demo.api.pauta;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import br.com.scd.demo.associado.Associado;
import br.com.scd.demo.pauta.Pauta;

public class PautaResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		List<Associado> associateds = Collections.singletonList(new Associado(1l));
		Pauta pauta = new Pauta(12l, "subject", associateds);
		PautaResponse pautaResponse = PautaResponseFactory.getInstance(pauta);

		assertEquals("subject", pautaResponse.getSubject());
		assertEquals("12", pautaResponse.getId().toString());
		assertEquals(associateds, pautaResponse.getAssociateds());
	}
}
