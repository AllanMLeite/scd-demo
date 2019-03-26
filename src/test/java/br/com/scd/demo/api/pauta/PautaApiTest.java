package br.com.scd.demo.api.pauta;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
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

import br.com.scd.demo.pauta.Pauta;
import br.com.scd.demo.pauta.PautaForInsert;
import br.com.scd.demo.pauta.PautaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PautaApiTest {

	private static final String PAUTA_URL = "/pauta/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PautaService service;

	@Test
	public void shoudSavePauta() throws Exception {

		PautaForInsert pautaForInsert = new PautaForInsert("subject");
		Pauta pauta = new Pauta(1l, "subject");
		when(service.save(any(PautaForInsert.class))).thenReturn(pauta);

		final String expectedResponse = mapper.writeValueAsString(pauta);
		mockMvc.perform(
					post(PAUTA_URL)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(pautaForInsert)))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	@Test
	public void shouldThrowErrorWhenSubjectHaveInvalidLength() throws Exception {

		String subject = generateStringWithMoreThan100Chars();
		PautaForInsert pautaForInsert = new PautaForInsert(subject);

		mockMvc.perform(
				post(PAUTA_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(pautaForInsert)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto nao deve ultrapassar 100 caracteres.")));
	}
	
	@Test
	public void shouldThrowErrorWhenSubjectIsNull() throws Exception {
		String payload = "{\"associateds\": [{\"id\":1}]}";
		mockMvc.perform(
				post(PAUTA_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto deve ser informado.")));
	}
	
	@Test
	public void shouldThrowErrorWhenSubjectIsEmpty() throws Exception {
		String payload = "{\"subject\":\"\", \"associateds\": [{\"id\":1}]}";

		mockMvc.perform(
				post(PAUTA_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto deve ser informado.")));
	}

	private String generateStringWithMoreThan100Chars() {
		return RandomStringUtils.randomAlphabetic(101);
	}
}
