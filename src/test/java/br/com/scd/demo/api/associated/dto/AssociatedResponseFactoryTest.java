package br.com.scd.demo.api.associated.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.associated.Associated;

public class AssociatedResponseFactoryTest {

	@Test
	public void shouldCreate() {
		Associated associated = new Associated(12l, "01234567890");
		AssociatedResponse associatedResponse = AssociatedResponseFactory.getInstance(associated);
		
		assertEquals("12", associatedResponse.getId().toString());
		assertEquals("01234567890", associatedResponse.getCpf().toString());
	}
}
