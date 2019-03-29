package br.com.scd.demo.api.vote.dto;

import br.com.scd.demo.vote.Vote;

public final class VoteResponseFactory {

	private VoteResponseFactory() {

	}

	public static VoteResponse getInstance(Vote vote) {
		return new VoteResponse(vote.getId(), vote.getSessionId(), vote.getAssociatedId(), vote.getVote());
	}
}
