package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;

@RunWith(Theories.class)
public class TopicResultTest {
	
	@DataPoints("invalidValues")
	public static String[] invalidValues = { null, StringUtils.EMPTY };

	@Test
	public void idCannotBeNull() {
		assertThatThrownBy(() -> new TopicResult.Builder().addId(null)
				.addSubject("subject")
				.addStatus(StatusEnum.ABERTA)
				.addTotalVotesMap(new HashMap<>())
				.addVoteSessionResult(TopicResultEnum.APROVADA)
				.addStartDate(LocalDateTime.now())
				.addEndDate(LocalDateTime.now())
				.build())
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessage("id cannot be null");
	}

	@Theory
	public void subjectCannotBeBlank(@FromDataPoints("invalidValues") String subject) {
		assertThatThrownBy(() -> new TopicResult.Builder().addId(1l)
				.addSubject(subject)
				.addStatus(StatusEnum.ABERTA)
				.addTotalVotesMap(new HashMap<>())
				.addVoteSessionResult(TopicResultEnum.APROVADA)
				.addStartDate(LocalDateTime.now())
				.addEndDate(LocalDateTime.now())
				.build())
		.isInstanceOf(IllegalArgumentException.class)
		.hasMessage("subject cannot be blank");
	}
	
	@Test
	public void shouldCreateInstance() {
		HashMap<VoteEnum, Long> totalVotes = new HashMap<>();
		totalVotes.put(VoteEnum.NAO, 10l);
		
		LocalDateTime endDate = LocalDateTime.now();
		
		TopicResult topic = new TopicResult.Builder().addId(1l)
				.addSubject("subject")
				.addStatus(StatusEnum.ABERTA)
				.addTotalVotesMap(totalVotes)
				.addVoteSessionResult(TopicResultEnum.APROVADA)
				.addStartDate(endDate.minusMinutes(2l))
				.addEndDate(endDate)
				.build();

		assertThat(topic.getSubject()).isEqualTo("subject");
		assertThat(topic.getStatus()).isEqualTo(StatusEnum.ABERTA);
		assertThat(topic.getTotalVotes()).isEqualTo(totalVotes);
		assertThat(topic.getVoteSessionResult()).isEqualTo(TopicResultEnum.APROVADA);
		assertThat(topic.getStartDate()).isEqualTo(endDate.minusMinutes(2l));
		assertThat(topic.getEndDate()).isEqualTo(endDate);
	}
}
