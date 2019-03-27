package br.com.scd.demo.api.topic.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import br.com.scd.demo.api.topic.dto.TopicResponse;

@RunWith(Theories.class)
public class TopicResponseTest {

	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY};
	
	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new TopicResponse(null, "subject"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("id cannot be null");
	}
	
	@Theory
	public void subjectCannotBeBlank(@FromDataPoints("invalidValues") String subject) {
		assertThatThrownBy(() -> new TopicResponse(12l, subject))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("subject cannot be blank");
	}
	
	@Test
	public void shouldCreateTopicResponse() {
		TopicResponse topic = new TopicResponse(12l, "subject");
		
		assertEquals("subject", topic.getSubject());
		assertEquals("12", topic.getId().toString());
	}
}
