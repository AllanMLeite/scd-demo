package br.com.scd.demo.session;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.api.session.dto.SessionRequest;
import br.com.scd.demo.session.SessionForInsert;
import br.com.scd.demo.session.SessionForInsertFactory;

public class SessionForInsertFactoryTest {

	@Test
	public void shouldCreateInstance() {

		SessionRequest sessionRequest = new SessionRequest();
		ReflectionTestUtils.setField(sessionRequest, "topicId", 20l);
		ReflectionTestUtils.setField(sessionRequest, "durationInMinutes", 40);

		SessionForInsert sessionForInsert = SessionForInsertFactory.getInstance(sessionRequest);

		assertEquals("40", sessionForInsert.getDurationInMinutes().toString());
		assertEquals("20", sessionForInsert.getTopicId().toString());
	}
}
