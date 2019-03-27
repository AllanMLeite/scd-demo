package br.com.scd.demo.vote;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.scd.demo.enums.VoteEnum;

public class VoteTest {

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new Vote(null, 1l, 2l, VoteEnum.SIM))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("id cannot be null");
	}

	@Test
	public void sessionIdCannotBeNull() {
		assertThatThrownBy(() -> new Vote(1l, null, 2l, VoteEnum.SIM))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("sessionId cannot be null");
	}

	@Test
	public void associatedIdCannotBeNull() {
		assertThatThrownBy(() -> new Vote(1l, 1l, null, VoteEnum.SIM))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("associatedId cannot be null");
	}

	@Test
	public void voteCannotBeNull() {
		assertThatThrownBy(() -> new Vote(1l, 1l, 2l, null)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("vote cannot be null");
	}

	@Test
	public void shouldCreate() {
		Vote vote = new Vote(1l, 2l, 3l, VoteEnum.SIM);

		assertEquals("1", vote.getId().toString());
		assertEquals("2", vote.getSessionId().toString());
		assertEquals("3", vote.getAssociatedId().toString());
		assertEquals(VoteEnum.SIM, vote.getVote());
	}
}
