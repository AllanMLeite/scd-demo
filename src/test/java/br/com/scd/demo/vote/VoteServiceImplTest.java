package br.com.scd.demo.vote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionRepository;
import br.com.scd.demo.topic.TopicEntity;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

	@InjectMocks
	private VoteServiceImpl service;

	@Mock
	private VoteRepository voteRepository;

	@Mock
	private SessionRepository sessionRepository;

	@Test
	public void shouldThrowErrorWhenSessionDoesntExists() {

		when(sessionRepository.findById(1l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("Id da sessão inexistente.");
	}

	@Test
	public void shouldThrowErrorWhenAssociatedHasAlreadyVotedInSession() {

		when(sessionRepository.findById(1l)).thenReturn(Optional.of(new SessionEntity()));
		when(voteRepository.findBySessionIdAndAssociatedId(1l, 2l)).thenReturn(Optional.of(new VoteEntity()));

		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Cada associado pode votar somente uma vez por pauta.");
	}

	@Test
	public void shouldSaveVote() {

		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "id", 1l);

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "id", 12l);

		VoteEntity voteEntityForInsert = VoteEntityFactory.getInstance(sessionEntity, 13l, VoteEnum.SIM);

		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity, 13l, VoteEnum.SIM);
		ReflectionTestUtils.setField(voteEntity, "id", 1l);

		when(sessionRepository.findById(12l)).thenReturn(Optional.of(sessionEntity));
		when(voteRepository.findBySessionIdAndAssociatedId(12l, 13l)).thenReturn(Optional.empty());
		when(voteRepository.save(refEq(voteEntityForInsert))).thenReturn(voteEntity);

		Vote saved = service.vote(new VoteForInsert(12l, 13l, VoteEnum.SIM));

		Vote expected = new Vote(1l, 12l, 13l, VoteEnum.SIM);
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}
}
