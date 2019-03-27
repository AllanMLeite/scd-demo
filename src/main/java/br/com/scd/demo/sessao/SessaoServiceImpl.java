package br.com.scd.demo.sessao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.pauta.PautaEntity;
import br.com.scd.demo.pauta.PautaRepository;

@Service
public class SessaoServiceImpl implements SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Override
	public Sessao save(SessaoForInsert sessaoForInsert) {

		Optional<PautaEntity> pautaEntity = pautaRepository.findById(sessaoForInsert.getPautaId());

		checkPautaExists(pautaEntity);
		
		checkSessionAlreadyOpened(sessaoForInsert.getPautaId());

		SessaoEntity sessaoEntity = SessaoEntityFactory.getInstance(sessaoForInsert, pautaEntity.get());
		
		sessaoEntity = sessaoRepository.save(sessaoEntity);
		
		return SessaoFactory.getInstance(sessaoEntity);
	}

	private void checkSessionAlreadyOpened(Long pautaId) {
		Optional<SessaoEntity> sessaoEntity = sessaoRepository.findByPautaId(pautaId);
		
		if(sessaoEntity.isPresent()) {
			throw new IllegalArgumentException("Sessão já iniciada para a pauta informada.");
		}
	}

	private void checkPautaExists(Optional<PautaEntity> pautaEntity) {
		if (!pautaEntity.isPresent()) {
			throw new IllegalArgumentException("Id da pauta inexistente.");
		}
	}
}
