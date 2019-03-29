package br.com.scd.demo.vote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.associated.AssociatedEntity;
import br.com.scd.demo.associated.AssociatedService;
import br.com.scd.demo.exception.AssociatedDoesntExistsException;
import br.com.scd.demo.exception.AssociatedHasAlreadyVotedInSessionException;
import br.com.scd.demo.exception.SessionClosedException;
import br.com.scd.demo.exception.SessionDoesnExistsException;
import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionService;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private AssociatedService associatedService;

	@Override
	public Vote vote(VoteForInsert voteForInsert) {

		Optional<SessionEntity> sessionEntity = sessionService.findById(voteForInsert.getSessionId());

		checkSession(sessionEntity);

		Optional<AssociatedEntity> associatedEntity = associatedService.findById(voteForInsert.getAssociatedId());

		checkAssociated(voteForInsert, associatedEntity);

		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity.get(), associatedEntity.get(), voteForInsert.getVote());
		
		voteEntity = voteRepository.save(voteEntity);
		
		return VoteFactory.getInstance(voteEntity);
	}

	private void checkAssociated(VoteForInsert voteForInsert, Optional<AssociatedEntity> associatedEntity) {
		checkAssociatedExists(associatedEntity);
		checkAssociatedHasAlreadyVotedInSession(voteForInsert);
	}

	private void checkAssociatedHasAlreadyVotedInSession(VoteForInsert voteForInsert) {
		Optional<VoteEntity> existingVote = voteRepository.findBySessionIdAndAssociatedId(voteForInsert.getSessionId(),
				voteForInsert.getAssociatedId());

		checkAssociatedHasAlreadyVotedInSession(existingVote);
	}

	private void checkSession(Optional<SessionEntity> sessionEntity) {
		checkSessionExists(sessionEntity);
		checkSessionIsOpen(sessionEntity.get());
	}

	private void checkAssociatedExists(Optional<AssociatedEntity> associatedEntity) {
		if (!associatedEntity.isPresent()) {
			throw new AssociatedDoesntExistsException();
		}
	}

	private void checkSessionIsOpen(SessionEntity sessionEntity) {
		if(sessionEntity.isClosed()) {
			throw new SessionClosedException(sessionEntity.getEndDate());
		}
	}

	private void checkAssociatedHasAlreadyVotedInSession(Optional<VoteEntity> existingVote) {
		if (existingVote.isPresent()) {
			throw new AssociatedHasAlreadyVotedInSessionException();
		}
	}

	private void checkSessionExists(Optional<SessionEntity> sessionEntity) {
		if (!sessionEntity.isPresent()) {
			throw new SessionDoesnExistsException();
		}
	}
}
