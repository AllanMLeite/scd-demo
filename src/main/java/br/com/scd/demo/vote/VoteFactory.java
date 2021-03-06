package br.com.scd.demo.vote;

public final class VoteFactory {

	private VoteFactory() {

	}

	public static Vote getInstance(VoteEntity voteEntity) {
		return new Vote(voteEntity.getId(), voteEntity.getSession().getId(), voteEntity.getAssociated().getId(), voteEntity.getVote());
	}
}
