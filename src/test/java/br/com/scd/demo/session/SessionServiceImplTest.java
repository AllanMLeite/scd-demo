package br.com.scd.demo.session;

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

import br.com.scd.demo.exception.SessionAlreadyOpenedException;
import br.com.scd.demo.exception.TopicDoesntExistsException;
import br.com.scd.demo.session.Session;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionEntityFactory;
import br.com.scd.demo.session.SessionForInsert;
import br.com.scd.demo.session.SessionRepository;
import br.com.scd.demo.session.SessionServiceImpl;
import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.topic.TopicRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

	@InjectMocks
	private SessionServiceImpl service;

	@Mock
	private TopicRepository topicRepository;

	@Mock
	private SessionRepository sessionRepository;

	@Test
	public void shouldThrowErrorWhenTopicDoesntExists() {

		when(topicRepository.findById(1l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.save(new SessionForInsert(1l, 2)))
			.isInstanceOf(TopicDoesntExistsException.class)
			.hasMessage("Id da pauta inexistente.");
	}
	
	@Test
	public void shouldThrowErrorWhenSessionAlreadyOpened() {

		SessionForInsert sessionForInsert = new SessionForInsert(1l, 2);
		TopicEntity topicEntity = new TopicEntity();
		SessionEntity sessionEntityForInsert = SessionEntityFactory.getInstance(sessionForInsert, topicEntity);
		when(topicRepository.findById(1l)).thenReturn(Optional.of(topicEntity));
		when(sessionRepository.findByTopicId(1l)).thenReturn(Optional.of(sessionEntityForInsert));

		assertThatThrownBy(() -> service.save(sessionForInsert))
			.isInstanceOf(SessionAlreadyOpenedException.class)
			.hasMessage("Sessão já iniciada para a pauta informada.");
	}

	@Test
	public void shouldSaveTopic() {

		SessionForInsert sessionForInsert = new SessionForInsert(1l, 2);
		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "id", 1l);
		
		SessionEntity sessionEntityForInsert = SessionEntityFactory.getInstance(sessionForInsert, topicEntity);
		
		SessionEntity sessionEntity = SessionEntityFactory.getInstance(sessionForInsert, topicEntity);
		ReflectionTestUtils.setField(sessionEntity, "id", 12l);
		
		when(topicRepository.findById(1l)).thenReturn(Optional.of(topicEntity));
		when(sessionRepository.findByTopicId(1l)).thenReturn(Optional.empty());
		when(sessionRepository.save(refEq(sessionEntityForInsert))).thenReturn(sessionEntity);

		Session saved = service.save(sessionForInsert);

		Session expected = new Session(12l, 1l, 2);
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}

	@Test
	public void shouldFindById() {
		SessionEntity sessionEntity = new SessionEntity();
		when(sessionRepository.findById(1l)).thenReturn(Optional.of(sessionEntity));
		assertThat(service.findById(1l).get()).isEqualTo(sessionEntity);
	}
}
