package br.com.scd.demo.vote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.scd.demo.session.SessionEntity;
import br.com.scd.demo.session.SessionRepository;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public Vote vote(VoteForInsert voteForInsert) {

		Optional<SessionEntity> sessionEntity = sessionRepository.findById(voteForInsert.getSessionId());

		checkSessionExists(sessionEntity);

		// TODO validar se o associado existe

		Optional<VoteEntity> existingVote = voteRepository.findBySessionIdAndAssociatedId(voteForInsert.getSessionId(),
				voteForInsert.getAssociatedId());

		checkAssociatedHasAlreadyVotedInSession(existingVote);

		VoteEntity voteEntity = VoteEntityFactory.getInstance(sessionEntity.get(), voteForInsert.getAssociatedId(), voteForInsert.getVote());
		
		voteEntity = voteRepository.save(voteEntity);
		
		return VoteFactory.getInstance(voteEntity);
	}

	private void checkAssociatedHasAlreadyVotedInSession(Optional<VoteEntity> existingVote) {
		if (existingVote.isPresent()) {
			throw new IllegalArgumentException("Cada associado pode votar somente uma vez por pauta.");
		}
	}

	private void checkSessionExists(Optional<SessionEntity> sessionEntity) {
		if (!sessionEntity.isPresent()) {
			throw new IllegalArgumentException("Id da sessão inexistente.");
		}
	}

}
