package br.com.scd.demo.pauta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.api.pauta.dto.PautaRequest;

public class PautaForInsertFactoryTest {

	@Test
	public void shouldCreateInstance() {
		PautaRequest pautaRequest = new PautaRequest();
		ReflectionTestUtils.setField(pautaRequest, "subject", "xyz");

		PautaForInsert pautaForInsert = PautaForInsertFactory.getInstance(pautaRequest);

		assertEquals("xyz", pautaForInsert.getSubject());
	}
}
