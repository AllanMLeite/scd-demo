package br.com.scd.demo.api.vote.dto;

import org.springframework.util.Assert;

import br.com.scd.demo.enums.VoteEnum;
import lombok.Getter;

@Getter
public class VoteResponse {

	private Long id;

	private Long sessionId;

	private Long associatedId;

	private VoteEnum vote;

	public VoteResponse(Long id, Long sessionId, Long associatedId, VoteEnum vote) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.notNull(sessionId, "sessionId cannot be null");
		Assert.notNull(associatedId, "associatedId cannot be null");
		Assert.notNull(vote, "vote cannot be null");
		
		this.id = id;
		this.sessionId = sessionId;
		this.associatedId = associatedId;
		this.vote = vote;
	}
}
