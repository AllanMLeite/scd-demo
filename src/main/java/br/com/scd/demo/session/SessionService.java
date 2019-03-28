package br.com.scd.demo.session;

import java.util.Optional;

public interface SessionService {

	public Session save(SessionForInsert sessionForInsert);

	public Optional<SessionEntity> findById(Long sessionId);
}
