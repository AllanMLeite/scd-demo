package br.com.scd.demo.vote;

public class VoteFactory {

	private VoteFactory() {
		// prevents instantiation
	}

	public static Vote getInstance(VoteEntity voteEntity) {
		return new Vote(voteEntity.getId(), voteEntity.getSession().getId(), voteEntity.getAssociated().getId(), voteEntity.getVote());
	}
}
