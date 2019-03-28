package br.com.scd.demo.associated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public List<Associated> findAll() {
		List<AssociatedEntity> associatedEntities = (List<AssociatedEntity>) associatedRepository.findAll();
		
		return associatedEntities.stream()
				.map(AssociatedFactory::getInstance)
				.collect(Collectors.toList());
	}
}
