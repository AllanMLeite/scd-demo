package br.com.scd.demo.pauta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.scd.demo.associado.Associado;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {
	
	@InjectMocks
	private PautaService service;
	
	@Mock
	private PautaRepository pautaRepository;

	@Test
	public void shouldSavePauta() {

		List<Associado> associateds =  Collections.singletonList(new Associado(1l));
		Pauta pauta = new Pauta(1l, "subject", associateds);
		PautaForInsert pautaForInsert = new PautaForInsert("subject", associateds);
		when(pautaRepository.save(pautaForInsert)).thenReturn(pauta);
		
		Pauta saved = service.save(pautaForInsert);
		
		assertThat(saved).isEqualTo(pauta);
	}

}
