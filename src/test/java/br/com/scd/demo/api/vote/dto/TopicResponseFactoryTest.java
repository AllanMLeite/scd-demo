package br.com.scd.demo.api.vote.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.vote.Vote;

public class TopicResponseFactoryTest {

	@Test
	public void shouldCreateInstance() {
		Vote vote = new Vote(1l, 2l, 3l, VoteEnum.NAO);
		VoteResponse voteResponse = VoteResponseFactory.getInstance(vote);

		assertEquals("1", voteResponse.getId().toString());
		assertEquals("2", voteResponse.getSessionId().toString());
		assertEquals("3", voteResponse.getAssociatedId().toString());
		assertEquals(VoteEnum.NAO, voteResponse.getVote());
	}
}
