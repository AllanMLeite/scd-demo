package br.com.scd.demo.associated;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssociatedServiceImplTest {

	@InjectMocks
	private AssociatedServiceImpl service;

	@Mock
	private AssociatedRepository associatedRepository;

	@Test
	public void shouldFindById() {
		AssociatedEntity entity = new AssociatedEntity();
		when(associatedRepository.findById(1l)).thenReturn(Optional.of(entity));
		assertThat(service.findById(1l).get()).isEqualTo(entity);
	}
}
