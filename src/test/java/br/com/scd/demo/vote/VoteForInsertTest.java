package br.com.scd.demo.vote;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.enums.VoteEnum;

public class VoteForInsertTest {

	@Test
	public void sessionIdCannotBeNull() {
		assertThatThrownBy(() -> new VoteForInsert(null, 2l, VoteEnum.SIM)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("sessionId cannot be null");
	}

	@Test
	public void associatedIdCannotBeNull() {
		assertThatThrownBy(() -> new VoteForInsert(1l, null, VoteEnum.SIM)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("associatedId cannot be null");
	}

	@Test
	public void voteCannotBeNull() {
		assertThatThrownBy(() -> new VoteForInsert(1l, 2l, null)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("vote cannot be null");
	}

	@Test
	public void shouldCreate() {
		VoteForInsert vote = new VoteForInsert(2l, 3l, VoteEnum.SIM);

		assertEquals("2", vote.getSessionId().toString());
		assertEquals("3", vote.getAssociatedId().toString());
		assertEquals(VoteEnum.SIM, vote.getVote());
	}
}
