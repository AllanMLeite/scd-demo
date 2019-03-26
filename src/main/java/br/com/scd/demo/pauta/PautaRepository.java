package br.com.scd.demo.pauta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends CrudRepository<PautaEntity, Long>{

}
