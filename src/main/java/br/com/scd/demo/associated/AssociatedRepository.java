package br.com.scd.demo.associated;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedRepository extends CrudRepository<AssociatedEntity, Long> {

}
