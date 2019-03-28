package br.com.scd.demo.api.associated;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.scd.demo.associated.Associated;
import br.com.scd.demo.associated.AssociatedService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AssociatedApiTest {

	private static final String URL = "/associated/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private AssociatedService service;

	@Test
	public void shouldFindAll() throws Exception {

		Associated someAssociated = new Associated(1l,  "01234567890");
		Associated anotherAssociated = new Associated(2l,  "90234567890");
		List<Associated> associateds = Arrays.asList(someAssociated, anotherAssociated);
		
		when(service.findAll()).thenReturn(associateds);

		String expectedResponse = mapper.writeValueAsString(associateds);
		mockMvc.perform(get(new URI(URL)))
			.andExpect(status().isOk())
			.andExpect(content().json(expectedResponse));
	}
}
