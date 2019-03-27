package br.com.scd.demo.sessao;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SessaoForInsertTest {

	@Test
	public void pautaIdCannotBeNull() {
		assertThatThrownBy(() -> new SessaoForInsert(null, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("pautaId cannot be null");
	}

	@Test
	public void shouldSetDefaultDurationMinutes() {
		SessaoForInsert sessaoForInsert = new SessaoForInsert(1l, null);
		assertEquals("1", sessaoForInsert.getDurationInMinutes().toString());
	}

	@Test
	public void shoudCreate() {
		SessaoForInsert sessaoForInsert = new SessaoForInsert(1l, 5);
		assertEquals("5", sessaoForInsert.getDurationInMinutes().toString());
		assertEquals("1", sessaoForInsert.getPautaId().toString());
	}
}
