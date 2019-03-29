package br.com.scd.demo.topic;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.util.Assert;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import lombok.Getter;

@Getter
public class TopicResult {

	private Long id;

	private String subject;

	private StatusEnum status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Map<VoteEnum, Long> totalVotes;

	private TopicResultEnum voteSessionResult;

	private TopicResult() {
		// prevents instantiation
	}

	public static final class Builder {

		TopicResult topicResult;

		public Builder() {
			topicResult = new TopicResult();
		}

		public Builder addId(Long id) {
			topicResult.id = id;
			return this;
		}

		public Builder addSubject(String subject) {
			topicResult.subject = subject;
			return this;
		}

		public Builder addStatus(StatusEnum status) {
			topicResult.status = status;
			return this;
		}

		public Builder addStartDate(LocalDateTime startDate) {
			topicResult.startDate = startDate;
			return this;
		}

		public Builder addEndDate(LocalDateTime endDate) {
			topicResult.endDate = endDate;
			return this;
		}

		public Builder addTotalVotesMap(Map<VoteEnum, Long> totalVotes) {
			topicResult.totalVotes = totalVotes;
			return this;
		}

		public Builder addVoteSessionResult(TopicResultEnum voteSessionResult) {
			topicResult.voteSessionResult = voteSessionResult;
			return this;
		}

		public TopicResult build() {

			Assert.notNull(topicResult.id, "id cannot be null");
			Assert.hasLength(topicResult.subject, "subject cannot be blank");

			return topicResult;
		}
	}
}
