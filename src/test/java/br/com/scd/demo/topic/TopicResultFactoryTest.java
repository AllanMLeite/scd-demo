package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.vote.VoteEntity;

public class TopicResultFactoryTest {

	@Test
	public void shouldCreateInstanceWhenSessionExists() {
		
		HashMap<VoteEnum, Long> totalVotes = new HashMap<>();
		totalVotes.put(VoteEnum.SIM, 1l);
		
		LocalDateTime now = LocalDateTime.now();
		
		VoteEntity voteEntity = new VoteEntity();
		voteEntity.setVote(VoteEnum.SIM);
		
		SessionEntity sessionEntity = new SessionEntity();		
		ReflectionTestUtils.setField(sessionEntity, "id", 1l);
		ReflectionTestUtils.setField(sessionEntity, "votes", Arrays.asList(voteEntity));
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", now);
		sessionEntity.setDurationInMinutes(10);
		
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setSubject("subject");
		ReflectionTestUtils.setField(topicEntity, "id", 1l);
		ReflectionTestUtils.setField(topicEntity, "session", sessionEntity);

		TopicResult topicResult = TopicResultFactory.getInstance(topicEntity);
		
		TopicResult expectedTopic = new TopicResult.Builder()
				.addId(1l)
				.addSubject("subject")
				.addStatus(StatusEnum.ABERTA)
				.addTotalVotesMap(totalVotes)
				.addVoteSessionResult(TopicResultEnum.APROVADA)
				.addStartDate(now)
				.addEndDate(now.plusMinutes(10l))
				.build();
		
		assertThat(topicResult).isEqualToComparingFieldByFieldRecursively(expectedTopic);
	}
	
	@Test
	public void shouldCreateInstanceWhenSessionDoesntExists() {
		
		TopicEntity topicEntity = new TopicEntity();
		topicEntity.setSubject("subject");
		ReflectionTestUtils.setField(topicEntity, "id", 1l);

		TopicResult topicResult = TopicResultFactory.getInstance(topicEntity);
		
		TopicResult expectedTopic = new TopicResult.Builder()
				.addId(1l)
				.addSubject("subject")
				.addStatus(StatusEnum.NAO_INICIADA)
				.addTotalVotesMap(new HashMap<>())
				.addVoteSessionResult(TopicResultEnum.NENHUM_VOTO)
				.build();
		
		assertThat(topicResult).isEqualToComparingFieldByFieldRecursively(expectedTopic);
	}
}
