package br.com.scd.demo.api.vote.dto;

import br.com.scd.demo.vote.Vote;

public class VoteResponseFactory {

	private VoteResponseFactory() {
		// TODO prevents instantiation
	}

	public static VoteResponse getInstance(Vote vote) {
		return new VoteResponse(vote.getId(), vote.getSessionId(), vote.getAssociatedId(), vote.getVote());

	}
}
