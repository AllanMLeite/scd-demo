package br.com.scd.demo.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.session.SessionForInsert;

public class SessionForInsertTest {

	@Test
	public void topicIdCannotBeNull() {
		assertThatThrownBy(() -> new SessionForInsert(null, 1)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("topicId cannot be null");
	}

	@Test
	public void shouldSetDefaultDurationMinutes() {
		SessionForInsert sessionForInsert = new SessionForInsert(1l, null);
		assertEquals("1", sessionForInsert.getDurationInMinutes().toString());
	}

	@Test
	public void shouldCreate() {
		SessionForInsert sessionForInsert = new SessionForInsert(1l, 5);
		assertEquals("5", sessionForInsert.getDurationInMinutes().toString());
		assertEquals("1", sessionForInsert.getTopicId().toString());
	}
}
