package br.com.scd.demo.vote;

import br.com.scd.demo.api.vote.dto.VoteRequest;

public class VoteForInsertFactory {

	private VoteForInsertFactory() {
		// TODO prevents instantiation
	}

	public static VoteForInsert getInstance(VoteRequest request) {
		return new VoteForInsert(request.getSessionId(), request.getAssociatedId(), request.getVote());
	}

}
