package br.com.scd.demo.session;

import br.com.scd.demo.api.session.dto.SessionRequest;

public final class SessionForInsertFactory {

	private SessionForInsertFactory() {

	}

	public static SessionForInsert getInstance(SessionRequest request) {
		return new SessionForInsert(request.getTopicId(), request.getDurationInMinutes());
	}
}
