package br.com.scd.demo.vote;

import br.com.scd.demo.associated.AssociatedEntity;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;

public final class VoteEntityFactory {

	private VoteEntityFactory() {

	}

	public static VoteEntity getInstance(SessionEntity sessionEntity, AssociatedEntity associatedEntity, VoteEnum vote) {
		VoteEntity voteEntity = new VoteEntity();
		voteEntity.setSession(sessionEntity);
		voteEntity.setAssociated(associatedEntity);
		voteEntity.setVote(vote);
		return voteEntity;
	}

}
