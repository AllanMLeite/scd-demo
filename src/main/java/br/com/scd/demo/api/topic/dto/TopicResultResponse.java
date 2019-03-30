package br.com.scd.demo.api.topic.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.util.Assert;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TopicResultResponse {

	private Long id;

	private String subject;

	private StatusEnum status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Map<VoteEnum, Long> totalVotes;

	private TopicResultEnum voteSessionResult;

	private TopicResultResponse() {

	}

	public static final class Builder {

		TopicResultResponse topicResultResponse;

		public Builder() {
			topicResultResponse = new TopicResultResponse();
		}

		public Builder addId(Long id) {
			topicResultResponse.id = id;
			return this;
		}

		public Builder addSubject(String subject) {
			topicResultResponse.subject = subject;
			return this;
		}

		public Builder addStatus(StatusEnum status) {
			topicResultResponse.status = status;
			return this;
		}

		public Builder addStartDate(LocalDateTime startDate) {
			topicResultResponse.startDate = startDate;
			return this;
		}

		public Builder addEndDate(LocalDateTime endDate) {
			topicResultResponse.endDate = endDate;
			return this;
		}

		public Builder addTotalVotesMap(Map<VoteEnum, Long> totalVotes) {
			topicResultResponse.totalVotes = totalVotes;
			return this;
		}

		public Builder addVoteSessionResult(TopicResultEnum voteSessionResult) {
			topicResultResponse.voteSessionResult = voteSessionResult;
			return this;
		}

		public TopicResultResponse build() {

			Assert.notNull(topicResultResponse.id, "id cannot be null");
			Assert.hasLength(topicResultResponse.subject, "subject cannot be blank");

			return topicResultResponse;
		}
	}
}
