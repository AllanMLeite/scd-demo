package br.com.scd.demo.api.sessao;

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

import br.com.scd.demo.sessao.Sessao;
import br.com.scd.demo.sessao.SessaoForInsert;
import br.com.scd.demo.sessao.SessaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SessaoApiTest {

	private static final String SESSAO_URL = "/sessao/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private SessaoService service;

	@Test
	public void shoudSave() throws Exception {

		SessaoForInsert sessaoForInsert = new SessaoForInsert(2l, 3);
		Sessao sessao = new Sessao(1l, 2l, 3);
		when(service.save(any(SessaoForInsert.class))).thenReturn(sessao);

		final String expectedResponse = mapper.writeValueAsString(sessao);
		mockMvc.perform(
					post(SESSAO_URL)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(sessaoForInsert)))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	@Test
	public void shouldThrowErrorWhenPautaIdIsNull() throws Exception {
		String payload = "{\"pautaId\": null}";
		mockMvc.perform(
				post(SESSAO_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Id da pauta deve ser informado.")));
	}
	
	@Test
	public void shouldntThrowErrorWhenDurationIsNull() throws Exception {
		Sessao sessao = new Sessao(1l, 2l, 3);
		when(service.save(any(SessaoForInsert.class))).thenReturn(sessao);
		
		String payload = "{\"pautaId\": 12}";
		mockMvc.perform(
				post(SESSAO_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isOk());
	}
}
