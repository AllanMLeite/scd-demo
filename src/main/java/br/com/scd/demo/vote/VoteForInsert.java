package br.com.scd.demo.vote;

import org.springframework.util.Assert;

import br.com.scd.demo.enums.VoteEnum;
import lombok.Getter;

@Getter
public class VoteForInsert {

	private Long sessionId;

	private Long associatedId;
	
	private VoteEnum vote;

	public VoteForInsert(Long sessionId, Long associatedId, VoteEnum vote) {
		
		Assert.notNull(sessionId, "sessionId cannot be null");
		Assert.notNull(associatedId, "associatedId cannot be null");
		Assert.notNull(vote, "vote cannot be null");
		
		this.sessionId = sessionId;
		this.associatedId = associatedId;
		this.vote = vote;
	}
}
