package br.com.scd.demo.associated;

import java.util.Optional;

public interface AssociatedService {

	Optional<AssociatedEntity> findById(Long associatedId);
}
