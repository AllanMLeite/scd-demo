package br.com.scd.demo.sessao;

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

import br.com.scd.demo.pauta.PautaEntity;
import br.com.scd.demo.pauta.PautaRepository;

@RunWith(MockitoJUnitRunner.class)
public class SessaoServiceImplTest {

	@InjectMocks
	private SessaoServiceImpl service;

	@Mock
	private PautaRepository pautaRepository;

	@Mock
	private SessaoRepository sessaoRepository;

	@Test
	public void shouldThrowErrorWhenPautaDoesntExists() {

		when(pautaRepository.findById(1l)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.save(new SessaoForInsert(1l, 2)))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Id da pauta inexistente.");
	}
	
	@Test
	public void shouldThrowErrorWhenSessionAlreadyOpened() {

		SessaoForInsert sessaoForInsert = new SessaoForInsert(1l, 2);
		PautaEntity pautaEntity = new PautaEntity();
		SessaoEntity sessaoEntityForInsert = SessaoEntityFactory.getInstance(sessaoForInsert, pautaEntity);
		when(pautaRepository.findById(1l)).thenReturn(Optional.of(pautaEntity));
		when(sessaoRepository.findByPautaId(1l)).thenReturn(Optional.of(sessaoEntityForInsert));

		assertThatThrownBy(() -> service.save(sessaoForInsert))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Sessão já iniciada para a pauta informada.");
	}

	@Test
	public void shouldSavePauta() {

		SessaoForInsert sessaoForInsert = new SessaoForInsert(1l, 2);
		PautaEntity pautaEntity = new PautaEntity();
		
		ReflectionTestUtils.setField(pautaEntity, "id", 1l);
		SessaoEntity sessaoEntityForInsert = SessaoEntityFactory.getInstance(sessaoForInsert, pautaEntity);
		
		SessaoEntity sessaoEntity = SessaoEntityFactory.getInstance(sessaoForInsert, pautaEntity);
		ReflectionTestUtils.setField(sessaoEntity, "id", 12l);
		
		when(pautaRepository.findById(1l)).thenReturn(Optional.of(pautaEntity));
		when(sessaoRepository.findByPautaId(1l)).thenReturn(Optional.empty());
		when(sessaoRepository.save(refEq(sessaoEntityForInsert))).thenReturn(sessaoEntity);

		Sessao saved = service.save(sessaoForInsert);

		Sessao expected = new Sessao(12l, 1l, 2);
		assertThat(saved).isEqualToComparingFieldByFieldRecursively(expected);
	}
}
