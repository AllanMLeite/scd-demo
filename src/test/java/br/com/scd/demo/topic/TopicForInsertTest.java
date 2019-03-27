package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import br.com.scd.demo.topic.TopicForInsert;

@RunWith(Theories.class)
public class TopicForInsertTest {

	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY};
	
	@Theory
	public void subjectCannotBeBlank(@FromDataPoints("invalidValues") String subject) {
		assertThatThrownBy(() -> new TopicForInsert(subject))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("subject cannot be blank");
	}
	
	@Test
	public void shouldCreateInstance() {
		TopicForInsert topic = new TopicForInsert("subject");
		assertEquals("subject", topic.getSubject());
	}
}
