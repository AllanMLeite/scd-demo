package br.com.scd.demo.pauta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	public Pauta save(PautaForInsert pautaForInsert) {
		PautaEntity pautaEntity = PautaEntityFactory.getInstance(pautaForInsert);
		pautaEntity = pautaRepository.save(pautaEntity);
		return PautaFactory.getInstance(pautaEntity);
	}
}