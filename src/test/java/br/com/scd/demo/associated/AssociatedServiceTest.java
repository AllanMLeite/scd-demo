package br.com.scd.demo.associated;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class AssociatedServiceTest {

	@InjectMocks
	private AssociatedService service;

	@Mock
	private AssociatedRepository associatedRepository;

	@Test
	public void shouldFindById() {
		AssociatedEntity entity = new AssociatedEntity();
		when(associatedRepository.findById(1l)).thenReturn(Optional.of(entity));
		assertThat(service.findById(1l).get()).isEqualTo(entity);
	}
	
	@Test
	public void shouldFindByAll() {
		AssociatedEntity someEntity = new AssociatedEntity();
		ReflectionTestUtils.setField(someEntity, "id", 1l);
		someEntity.setCpf("01234567890");
		
		AssociatedEntity anotherEntity = new AssociatedEntity();
		ReflectionTestUtils.setField(anotherEntity, "id", 2l);
		anotherEntity.setCpf("45678912345");
		
		when(associatedRepository.findAll()).thenReturn(Arrays.asList(someEntity, anotherEntity));
		
		Associated someAssociated = new Associated(1l, "01234567890");
		Associated anotherAssociated = new Associated(2l, "45678912345");
		
		assertThat(service.findAll())
			.usingRecursiveFieldByFieldElementComparator()
			.containsExactlyInAnyOrder(someAssociated, anotherAssociated);
	}
}
