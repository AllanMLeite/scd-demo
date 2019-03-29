package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.exception.TopicDoesntExistsException;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.vote.VoteEntity;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTest {

	@InjectMocks
	private TopicServiceImpl service;

	@Mock
	private TopicRepository topicRepository;

	@Test
	public void shouldSave() {

		TopicForInsert topicForInsert = new TopicForInsert("subject");
		TopicEntity topicEntityForInsert = TopicEntityFactory.getInstance(topicForInsert);
		TopicEntity topicEntity = TopicEntityFactory.getInstance(topicForInsert);
		ReflectionTestUtils.setField(topicEntity, "id", 12l);
		when(topicRepository.save(refEq(topicEntityForInsert))).thenReturn(topicEntity);

		Topic saved = service.save(topicForInsert);

		Topic expected = new Topic(12l, "subject");
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}
	
	@Test
	public void shouldThrowErrorWhenTopicDoesntExists() {

		when(topicRepository.findByIdWithSessionResult(1l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.findByIdWithSessionResult(1l))
			.isInstanceOf(TopicDoesntExistsException.class)
			.hasMessage("Id da pauta inexistente.");
	}
	
	
	@Test
	public void shouldFindByIdWithSessionResult() {
		
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
		
		when(topicRepository.findByIdWithSessionResult(1l)).thenReturn(Optional.of(topicEntity));

		TopicResult topicResult = service.findByIdWithSessionResult(1l);
		
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

}
