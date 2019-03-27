package br.com.scd.demo.sessao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends CrudRepository<SessaoEntity, Long> {

	Optional<SessaoEntity> findByPautaId(Long pautaId);

}
