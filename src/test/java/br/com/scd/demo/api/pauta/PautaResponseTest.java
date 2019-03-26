package br.com.scd.demo.api.pauta;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import br.com.scd.demo.associado.Associado;

@RunWith(Theories.class)
public class PautaResponseTest {

	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY};
	
	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new PautaResponse(null, "subject", Collections.singletonList(new Associado(1l))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("id cannot be null");
	}
	
	@Theory
	public void subjectCannotBeBlank(@FromDataPoints("invalidValues") String subject) {
		assertThatThrownBy(() -> new PautaResponse(12l, subject, Collections.singletonList(new Associado(1l))))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("subject cannot be blank");
	}
	
	@Test
	public void associatedsCannotBeNull() {
		assertThatThrownBy(() -> new PautaResponse(12l, "subject", null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("associateds cannot be empty");
	}
	
	@Test
	public void associatedsCannotBeEmpty() {
		assertThatThrownBy(() -> new PautaResponse(12l, "subject", new ArrayList<>()))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("associateds cannot be empty");
	}
	
	@Test
	public void shoudCreatePautaResponse() {
		List<Associado> associateds = Collections.singletonList(new Associado(1l));
		PautaResponse pauta = new PautaResponse(12l, "subject", associateds);
		
		assertEquals("subject", pauta.getSubject());
		assertEquals("12", pauta.getId().toString());
		assertEquals(associateds, pauta.getAssociateds());
	}
}
