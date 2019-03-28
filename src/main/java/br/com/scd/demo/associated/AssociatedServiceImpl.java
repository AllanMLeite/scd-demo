package br.com.scd.demo.associated;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociatedServiceImpl implements AssociatedService {

	@Autowired
	private AssociatedRepository associatedRepository;
	
	@Override
	public Optional<AssociatedEntity> findById(Long associatedId) {
		return associatedRepository.findById(associatedId);
	}
}
