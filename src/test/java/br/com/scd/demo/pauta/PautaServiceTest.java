package br.com.scd.demo.pauta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

	@InjectMocks
	private PautaService service;

	@Mock
	private PautaRepository pautaRepository;

	@Test
	public void shouldSavePauta() {

		PautaForInsert pautaForInsert = new PautaForInsert("subject");
		PautaEntity pautaEntityForInsert = PautaEntityFactory.getInstance(pautaForInsert);
		PautaEntity pautaEntity = PautaEntityFactory.getInstance(pautaForInsert);
		ReflectionTestUtils.setField(pautaEntity, "id", 12l);
		when(pautaRepository.save(refEq(pautaEntityForInsert))).thenReturn(pautaEntity);

		Pauta saved = service.save(pautaForInsert);

		Pauta expected = new Pauta(12l, "subject");
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}

}
