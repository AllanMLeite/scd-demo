package br.com.scd.demo.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.associated.AssociatedEntity;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;

public class VoteFactoryTest {

	@Test
	public void shouldCreateInstance() {
		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "id", 45l);

		VoteEntity voteEntity = new VoteEntity();
		ReflectionTestUtils.setField(voteEntity, "id", 10l);
		
		AssociatedEntity associatedEntity = new AssociatedEntity();
		ReflectionTestUtils.setField(associatedEntity, "id", 1l);
		
		voteEntity.setAssociated(associatedEntity);
		voteEntity.setSession(sessionEntity);
		voteEntity.setVote(VoteEnum.SIM);

		Vote vote = VoteFactory.getInstance(voteEntity);

		assertEquals("10", vote.getId().toString());
		assertEquals("45", vote.getSessionId().toString());
		assertEquals("1", vote.getAssociatedId().toString());
		assertEquals(VoteEnum.SIM, vote.getVote());
	}
}
