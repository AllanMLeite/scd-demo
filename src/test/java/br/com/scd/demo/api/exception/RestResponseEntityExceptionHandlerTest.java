package br.com.scd.demo.api.exception;

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

import br.com.scd.demo.exception.InvalidArgumentException;
import br.com.scd.demo.topic.TopicForInsert;
import br.com.scd.demo.topic.TopicService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestResponseEntityExceptionHandlerTest {

	private static final String TOPIC_URL = "/topic/";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TopicService topicService;

	@Test
	public void shouldHandleMethodArgumentNotValidException() throws Exception {

		String expectedMsg = "{\"message\":\"Assunto deve ser informado.\",\"status\":400,\"error\":\"Bad Request\"}";

		mockMvc.perform(post(TOPIC_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content("{}")).andExpect(status().isBadRequest()).andExpect(content().string(expectedMsg));
	}

	@Test
	public void shouldHandleInvalidArgumentException() throws Exception {

		String expectedMsg = "Exception xyz";
		when(topicService.save(any(TopicForInsert.class))).thenThrow(new InvalidArgumentException(expectedMsg));

		mockMvc.perform(post(TOPIC_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content("{\"subject\":\"123\"}")).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString(expectedMsg)));
	}
}
