package br.com.scd.demo.api.vote;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.vote.Vote;
import br.com.scd.demo.vote.VoteForInsert;
import br.com.scd.demo.vote.VoteService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VoteApiTest {

	private static final String VOTE_URL = "/vote/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private VoteService service;

	@Test
	public void shouldSave() throws Exception {

		VoteForInsert voteForInsert = new VoteForInsert(1l, 2l, VoteEnum.SIM);
		Vote vote = new Vote(3l, 1l, 2l, VoteEnum.SIM);
		when(service.vote(any(VoteForInsert.class))).thenReturn(vote);

		final String expectedResponse = mapper.writeValueAsString(vote);
		mockMvc.perform(post(mapper.writeValueAsString(voteForInsert)))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	@Test
	public void shouldThrowErrorWhenSessionIdIsNull() throws Exception {
		String payload = "{\"sessionId\": null, \"associatedId\": 12, \"vote\": \"SIM\"}";
		mockMvc.perform(post(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Id da sessao deve ser informado.")));
	}
	
	@Test
	public void shouldThrowErrorWhenAssociatedIdIsNull() throws Exception {
		String payload = "{\"sessionId\": 12, \"associatedId\": null, \"vote\": \"SIM\"}";
		mockMvc.perform(post(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Id do associado deve ser informado.")));
	}
	
	@Test
	public void shouldThrowErrorWhenVoteIsNull() throws Exception {
		String payload = "{\"sessionId\": 12, \"associatedId\": 12, \"vote\": null}";
		mockMvc.perform(post(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Voto deve ser informado.")));
	}
	
	private MockHttpServletRequestBuilder post(String payload) {
		return MockMvcRequestBuilders.post(VOTE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payload);
	}
}
