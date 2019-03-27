package br.com.scd.demo.associado;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AssociadoTest {

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new Associado(null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("id cannot be null");
	}

	@Test
	public void shouldCreate() {
		Associado associado = new Associado(12l);
		assertEquals("12", associado.getId().toString());
	}
}
