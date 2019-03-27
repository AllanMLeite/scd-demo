package br.com.scd.demo.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.api.vote.dto.VoteRequest;
import br.com.scd.demo.enums.VoteEnum;

public class VoteForInsertFactoryTest {

	@Test
	public void shouldCreateInstance() {
		VoteRequest voteRequest = new VoteRequest();
		ReflectionTestUtils.setField(voteRequest, "sessionId", 1l);
		ReflectionTestUtils.setField(voteRequest, "associatedId", 2l);
		ReflectionTestUtils.setField(voteRequest, "vote", VoteEnum.SIM);

		VoteForInsert voteForInsert = VoteForInsertFactory.getInstance(voteRequest);

		assertEquals("1", voteForInsert.getSessionId().toString());
		assertEquals("2", voteForInsert.getAssociatedId().toString());
		assertEquals(VoteEnum.SIM, voteForInsert.getVote());
	}
}
