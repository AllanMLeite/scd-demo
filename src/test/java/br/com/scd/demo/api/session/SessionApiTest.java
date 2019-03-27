package br.com.scd.demo.api.session;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.scd.demo.session.Session;
import br.com.scd.demo.session.SessionForInsert;
import br.com.scd.demo.session.SessionService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SessionApiTest {

	private static final String SESSION_URL = "/session/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private SessionService service;

	@Test
	public void shouldSave() throws Exception {

		SessionForInsert sessionForInsert = new SessionForInsert(2l, 3);
		Session session = new Session(1l, 2l, 3);
		when(service.save(any(SessionForInsert.class))).thenReturn(session);

		final String expectedResponse = mapper.writeValueAsString(session);
		mockMvc.perform(
					post(SESSION_URL)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(sessionForInsert)))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	@Test
	public void shouldThrowErrorWhenTopicIdIsNull() throws Exception {
		String payload = "{\"topicId\": null}";
		mockMvc.perform(
				post(SESSION_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Id da pauta deve ser informado.")));
	}
	
	@Test
	public void shouldntThrowErrorWhenDurationIsNull() throws Exception {
		Session session = new Session(1l, 2l, 3);
		when(service.save(any(SessionForInsert.class))).thenReturn(session);
		
		String payload = "{\"topicId\": 12}";
		mockMvc.perform(
				post(SESSION_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isOk());
	}
}
