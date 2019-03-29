package br.com.scd.demo.vote;

import br.com.scd.demo.api.vote.dto.VoteRequest;

public final class VoteForInsertFactory {

	private VoteForInsertFactory() {

	}

	public static VoteForInsert getInstance(VoteRequest request) {
		return new VoteForInsert(request.getSessionId(), request.getAssociatedId(), request.getVote());
	}
}
