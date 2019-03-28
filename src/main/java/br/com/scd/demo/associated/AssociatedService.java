package br.com.scd.demo.associated;

import java.util.List;
import java.util.Optional;

public interface AssociatedService {

	Optional<AssociatedEntity> findById(Long associatedId);

	List<Associated> findAll();
}
