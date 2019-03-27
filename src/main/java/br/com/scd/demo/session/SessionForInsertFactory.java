package br.com.scd.demo.session;

import br.com.scd.demo.api.session.dto.SessionRequest;

public class SessionForInsertFactory {

	private SessionForInsertFactory() {
		// prevents instantiation
	}

	public static SessionForInsert getInstance(SessionRequest request) {
		return new SessionForInsert(request.getTopicId(), request.getDurationInMinutes());
	}
}
