package br.com.scd.demo.api.sessao.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SessaoResponseTest {

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new SessaoResponse(null, 1l, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id cannot be null");
	}

	@Test
	public void pautaIdCannotBeNull() {
		assertThatThrownBy(() -> new SessaoResponse(1l, null, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("pautaId cannot be null");
	}

	@Test
	public void durationInMinutesCannotBeNull() {
		assertThatThrownBy(() -> new SessaoResponse(1l, 1l, null)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("durationInMinutes cannot be null");
	}

	@Test
	public void shoudCreateResponse() {
		SessaoResponse sessao = new SessaoResponse(12l, 15l, 10);

		assertEquals("15", sessao.getPautaId().toString());
		assertEquals("12", sessao.getId().toString());
		assertEquals("10", sessao.getDurationInMinutes().toString());
	}
}
