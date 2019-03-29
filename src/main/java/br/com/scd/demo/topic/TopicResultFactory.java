package br.com.scd.demo.topic;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;

public class TopicResultFactory {

	private TopicResultFactory() {
		// prevents instantiation
	}

	public static TopicResult getInstance(TopicEntity topicEntity) {
		return new TopicResult.Builder().addId(topicEntity.getId())
				.addSubject(topicEntity.getSubject())
				.addStatus(topicEntity.getStatus())
				.addTotalVotesMap(getTotalVotesMap(topicEntity))
				.addVoteSessionResult(getVoteSessionResult(topicEntity))
				.addStartDate(getStartDate(topicEntity))
				.addEndDate(getEndDate(topicEntity))
				.build();
	}

	private static LocalDateTime getStartDate(TopicEntity topicEntity) {
		return topicEntity.sessionExists() ? topicEntity.getSession().getDateAdded() : null;
	}
	
	private static LocalDateTime getEndDate(TopicEntity topicEntity) {
		return topicEntity.sessionExists() ? topicEntity.getSession().getEndDate() : null;
	}

	private static TopicResultEnum getVoteSessionResult(TopicEntity topicEntity) {
		return topicEntity.sessionExists() ? topicEntity.getSession().getSessionResult() : TopicResultEnum.NENHUM_VOTO;
	}

	private static Map<VoteEnum, Long> getTotalVotesMap(TopicEntity topicEntity) {
		return topicEntity.sessionExists() ? topicEntity.getSession().getTotalVotes() : new HashMap<>();
	}
}
