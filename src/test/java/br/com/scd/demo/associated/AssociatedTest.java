package br.com.scd.demo.associated;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import br.com.scd.demo.associated.Associated;

@RunWith(Theories.class)
public class AssociatedTest {

	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY };

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new Associated(null, "01234567890"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("id cannot be null");
	}
	
	@Theory
	public void cpfCannotBeBlank(@FromDataPoints("invalidValues") String cpf) {
		assertThatThrownBy(() -> new Associated(12l, cpf))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("cpf cannot be null");
	}

	@Test
	public void shouldCreate() {
		Associated associated = new Associated(12l, "01234567890");
		assertEquals("12", associated.getId().toString());
		assertEquals("01234567890", associated.getCpf().toString());

	}
}
