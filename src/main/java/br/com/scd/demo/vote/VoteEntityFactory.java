package br.com.scd.demo.vote;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;

public class VoteEntityFactory {

	private VoteEntityFactory() {
		// prevents instantiation
	}

	public static VoteEntity getInstance(SessionEntity sessionEntity, Long associatedId, VoteEnum vote) {
		VoteEntity voteEntity = new VoteEntity();
		voteEntity.setSession(sessionEntity);
		voteEntity.setAssociatedId(associatedId);
		voteEntity.setVote(vote);
		return voteEntity;
	}

}
