package br.com.scd.demo.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;

public class VoteEntityFactoryTest {

	@Test
	public void shouldCreateInstance() {
		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "id", 45l);

		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity, 12l, VoteEnum.NAO);

		assertEquals(sessionEntity, voteEntity.getSession());
		assertEquals("12", voteEntity.getAssociatedId().toString());
		assertEquals(VoteEnum.NAO, voteEntity.getVote());
	}
}
