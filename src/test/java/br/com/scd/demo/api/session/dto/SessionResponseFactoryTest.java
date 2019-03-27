package br.com.scd.demo.api.session.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.api.session.dto.SessionResponse;
import br.com.scd.demo.api.session.dto.SessionResponseFactory;
import br.com.scd.demo.session.Session;

public class SessionResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		Session session = new Session(12l, 15l, 10);
		SessionResponse sessionResponse = SessionResponseFactory.getInstance(session);

		assertEquals("12", sessionResponse.getId().toString());
		assertEquals("15", sessionResponse.getTopicId().toString());
		assertEquals("10", sessionResponse.getDurationInMinutes().toString());
	}
}
