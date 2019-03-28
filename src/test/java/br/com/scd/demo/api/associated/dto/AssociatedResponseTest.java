package br.com.scd.demo.api.associated.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class AssociatedResponseTest {

	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY };

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new AssociatedResponse(null, "01234567890"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("id cannot be null");
	}
	
	@Theory
	public void cpfCannotBeBlank(@FromDataPoints("invalidValues") String cpf) {
		assertThatThrownBy(() -> new AssociatedResponse(12l, cpf))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("cpf cannot be null");
	}

	@Test
	public void shouldCreate() {
		AssociatedResponse associatedResponse = new AssociatedResponse(12l, "01234567890");
		assertEquals("12", associatedResponse.getId().toString());
		assertEquals("01234567890", associatedResponse.getCpf().toString());
	}
}
