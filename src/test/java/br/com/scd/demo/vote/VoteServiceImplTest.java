package br.com.scd.demo.vote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.associated.AssociatedEntity;
import br.com.scd.demo.associated.AssociatedService;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.exception.AssociatedDoesntExistsException;
import br.com.scd.demo.exception.AssociatedHasAlreadyVotedInSessionException;
import br.com.scd.demo.exception.SessionClosedException;
import br.com.scd.demo.exception.SessionDoesnExistsException;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionService;
import br.com.scd.demo.topic.TopicEntity;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

	@InjectMocks
	private VoteServiceImpl service;

	@Mock
	private VoteRepository voteRepository;

	@Mock
	private SessionService sessionService;
	
	@Mock
	private AssociatedService associatedService;

	@Test
	public void shouldThrowErrorWhenSessionDoesntExists() {

		when(sessionService.findById(1l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(SessionDoesnExistsException.class).hasMessage("Id da sessão inexistente.");
	}
	
	@Test
	public void shouldThrowErrorWhenAssociatedDoesntExists() {

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", LocalDateTime.now());
		ReflectionTestUtils.setField(sessionEntity, "durationInMinutes", 5);

		when(sessionService.findById(1l)).thenReturn(Optional.of(sessionEntity));
		when(associatedService.findById(2l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(AssociatedDoesntExistsException.class).hasMessage("Id do associado inexistente.");
	}

	@Test
	public void shouldThrowErrorWhenAssociatedHasAlreadyVotedInSession() {

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", LocalDateTime.now());
		ReflectionTestUtils.setField(sessionEntity, "durationInMinutes", 5);

		when(sessionService.findById(1l)).thenReturn(Optional.of(sessionEntity));
		when(associatedService.findById(2l)).thenReturn(Optional.of(new AssociatedEntity()));
		when(voteRepository.findBySessionIdAndAssociatedId(1l, 2l)).thenReturn(Optional.of(new VoteEntity()));
		
		
		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(AssociatedHasAlreadyVotedInSessionException.class)
				.hasMessage("Cada associado pode votar somente uma vez por pauta.");
	}
	
	@Test
	public void shouldThrowErrorWhenSessionIsClosed() {

		LocalDateTime dateAdded = LocalDateTime.now().minusMinutes(6l);
		
		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", dateAdded);
		ReflectionTestUtils.setField(sessionEntity, "durationInMinutes", 5);

		when(sessionService.findById(1l)).thenReturn(Optional.of(sessionEntity));

		assertThatThrownBy(() -> service.vote(new VoteForInsert(1l, 2l, VoteEnum.NAO)))
				.isInstanceOf(SessionClosedException.class)
				.hasMessage(String.format("Sessão encerrada às %s.", dateAdded.plusMinutes(5)));
	}

	@Test
	public void shouldSaveVote() {

		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "id", 1l);

		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setDurationInMinutes(5);
		ReflectionTestUtils.setField(sessionEntity, "id", 12l);
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", LocalDateTime.now());

		AssociatedEntity associatedEntity = new AssociatedEntity();
		ReflectionTestUtils.setField(associatedEntity, "id", 13l);

		VoteEntity voteEntityForInsert = VoteEntityFactory.getInstance(sessionEntity, associatedEntity, VoteEnum.SIM);

		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity, associatedEntity, VoteEnum.SIM);
		ReflectionTestUtils.setField(voteEntity, "id", 1l);

		when(sessionService.findById(12l)).thenReturn(Optional.of(sessionEntity));
		when(associatedService.findById(13l)).thenReturn(Optional.of(associatedEntity));
		when(voteRepository.findBySessionIdAndAssociatedId(12l, 13l)).thenReturn(Optional.empty());
		when(voteRepository.save(refEq(voteEntityForInsert))).thenReturn(voteEntity);

		Vote saved = service.vote(new VoteForInsert(12l, 13l, VoteEnum.SIM));

		Vote expected = new Vote(1l, 12l, 13l, VoteEnum.SIM);
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}
}
