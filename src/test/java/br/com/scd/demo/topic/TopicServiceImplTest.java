package br.com.scd.demo.topic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.topic.TopicEntityFactory;
import br.com.scd.demo.topic.TopicRepository;
import br.com.scd.demo.topic.TopicServiceImpl;
import br.com.scd.demo.topic.Topic;
import br.com.scd.demo.topic.TopicForInsert;

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

}
