package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.session.SessionEntity;

public class TopicEntityTest {

	@Test
	public void shouldGetStatusNAO_INICIADA() {
		TopicEntity topicEntity = new TopicEntity();
		assertThat(topicEntity.getStatus()).isEqualTo(StatusEnum.NAO_INICIADA);
	}

	@Test
	public void shouldGetStatusFINALIZADA() {
		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", LocalDateTime.now().minusMinutes(5));
		sessionEntity.setDurationInMinutes(1);

		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "session", sessionEntity);

		assertThat(topicEntity.getStatus()).isEqualTo(StatusEnum.FINALIZADA);
	}
	
	@Test
	public void shouldGetStatusABERTA() {
		SessionEntity sessionEntity = new SessionEntity();
		sessionEntity.setCreatedNow();
		sessionEntity.setDurationInMinutes(1);

		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "session", sessionEntity);

		assertThat(topicEntity.getStatus()).isEqualTo(StatusEnum.ABERTA);
	}
	
	@Test
	public void shouldReturnSessionExistsTrue() {
		TopicEntity topicEntity = new TopicEntity();
		ReflectionTestUtils.setField(topicEntity, "session", new SessionEntity());
		assertThat(topicEntity.sessionExists()).isEqualTo(Boolean.TRUE);
	}
	
	@Test
	public void shouldReturnSessionExistsFalse() {
		TopicEntity topicEntity = new TopicEntity();
		assertThat(topicEntity.sessionExists()).isEqualTo(Boolean.FALSE);
	}
}
