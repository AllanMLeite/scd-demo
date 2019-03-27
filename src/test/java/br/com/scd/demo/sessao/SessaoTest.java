package br.com.scd.demo.sessao;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SessaoTest {

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new Sessao(null, 1l, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id cannot be null");
	}

	@Test
	public void pautaIdCannotBeNull() {
		assertThatThrownBy(() -> new Sessao(1l, null, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("pautaId cannot be null");
	}

	@Test
	public void durationInMinutesCannotBeNull() {
		assertThatThrownBy(() -> new Sessao(1l, 1l, null)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("durationInMinutes cannot be null");
	}

	@Test
	public void shoudCreateResponse() {
		Sessao sessao = new Sessao(12l, 15l, 10);

		assertEquals("15", sessao.getPautaId().toString());
		assertEquals("12", sessao.getId().toString());
		assertEquals("10", sessao.getDurationInMinutes().toString());
	}
}
