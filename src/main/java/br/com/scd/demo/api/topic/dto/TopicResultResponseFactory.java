package br.com.scd.demo.api.topic.dto;

import br.com.scd.demo.topic.TopicResult;

public class TopicResultResponseFactory {

	private TopicResultResponseFactory() {
		// prevents instantiation
	}
	
	public static TopicResultResponse getInstance(TopicResult topicResult) {
		return new TopicResultResponse.Builder().addId(topicResult.getId())
				.addSubject(topicResult.getSubject())
				.addStatus(topicResult.getStatus())
				.addTotalVotesMap(topicResult.getTotalVotes())
				.addVoteSessionResult(topicResult.getVoteSessionResult())
				.addStartDate(topicResult.getStartDate())
				.addEndDate(topicResult.getEndDate())
				.build();
	}
}
