package br.com.scd.demo.api.topic;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.topic.Topic;
import br.com.scd.demo.topic.TopicForInsert;
import br.com.scd.demo.topic.TopicResult;
import br.com.scd.demo.topic.TopicService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TopicApiTest {

	private static final String TOPIC_URL = "/topics";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private TopicService service;

	@Test
	public void shouldSave() throws Exception {

		TopicForInsert topicForInsert = new TopicForInsert("subject");
		Topic topic = new Topic(1l, "subject");
		when(service.save(any(TopicForInsert.class))).thenReturn(topic);

		final String expectedResponse = mapper.writeValueAsString(topic);
		String requestBody = mapper.writeValueAsString(topicForInsert);
		mockMvc.perform(post(requestBody))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	@Test
	public void shouldThrowErrorWhenSubjectHaveInvalidLength() throws Exception {

		String subject = generateStringWithMoreThan100Chars();
		TopicForInsert topicForInsert = new TopicForInsert(subject);

		mockMvc.perform(post(TOPIC_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(topicForInsert)))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto nao deve ultrapassar 100 caracteres.")));
	}
	
	@Test
	public void shouldThrowErrorWhenSubjectIsNull() throws Exception {
		String payload = "{\"associateds\": [{\"id\":1}]}";
		mockMvc.perform(post(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto deve ser informado.")));
	}
	
	@Test
	public void shouldThrowErrorWhenSubjectIsEmpty() throws Exception {
		String payload = "{\"subject\":\"\", \"associateds\": [{\"id\":1}]}";

		mockMvc.perform(post(payload))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Assunto deve ser informado.")));
	}
	
	@Test
	public void shouldGetTopicResult() throws Exception {

		HashMap<VoteEnum, Long> totalVotes = new HashMap<>();
		totalVotes.put(VoteEnum.SIM, 1l);
		
		LocalDateTime now = LocalDateTime.now();
		
		TopicResult topic = new TopicResult.Builder()
				.addId(1l)
				.addSubject("subject")
				.addStatus(StatusEnum.ABERTA)
				.addTotalVotesMap(totalVotes)
				.addVoteSessionResult(TopicResultEnum.APROVADA)
				.addStartDate(now)
				.addEndDate(now.plusMinutes(10l))
				.build();
		
		when(service.findByIdWithSessionResult(1l)).thenReturn(topic);

		final String expectedResponse = mapper.writeValueAsString(topic);
		mockMvc.perform(MockMvcRequestBuilders.get(new URI(TOPIC_URL+"/result/1")))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}

	private String generateStringWithMoreThan100Chars() {
		return RandomStringUtils.randomAlphabetic(101);
	}
	
	private MockHttpServletRequestBuilder post(String requestBody) {
		return MockMvcRequestBuilders.post(TOPIC_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody);
	}
}
