package br.com.scd.demo.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.session.Session;

public class SessionTest {

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new Session(null, 1l, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id cannot be null");
	}

	@Test
	public void topicIdCannotBeNull() {
		assertThatThrownBy(() -> new Session(1l, null, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("topicId cannot be null");
	}

	@Test
	public void durationInMinutesCannotBeNull() {
		assertThatThrownBy(() -> new Session(1l, 1l, null)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("durationInMinutes cannot be null");
	}

	@Test
	public void shouldCreateResponse() {
		Session session = new Session(12l, 15l, 10);

		assertEquals("15", session.getTopicId().toString());
		assertEquals("12", session.getId().toString());
		assertEquals("10", session.getDurationInMinutes().toString());
	}
}
