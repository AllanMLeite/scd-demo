package br.com.scd.demo.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.associated.AssociatedEntity;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;

public class VoteEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "id", 45l);

		AssociatedEntity associatedEntity = new AssociatedEntity();
		ReflectionTestUtils.setField(associatedEntity, "id", 12l);
		
		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity, associatedEntity, VoteEnum.NAO);

		assertEquals(sessionEntity, voteEntity.getSession());
		assertEquals("12", voteEntity.getAssociated().getId().toString());
		assertEquals(VoteEnum.NAO, voteEntity.getVote());
	}
}
