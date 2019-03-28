package br.com.scd.demo.associated;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class AssociatedFactoryTest {

	@Test
	public void shouldCreate() {
		AssociatedEntity associatedEntity = new AssociatedEntity();
		associatedEntity.setCpf("01234567890");
		ReflectionTestUtils.setField(associatedEntity, "id", 12l);

		Associated associated = AssociatedFactory.getInstance(associatedEntity);

		assertEquals("12", associated.getId().toString());
		assertEquals("01234567890", associated.getCpf().toString());
	}
}
