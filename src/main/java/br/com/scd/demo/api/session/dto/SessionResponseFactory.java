package br.com.scd.demo.api.session.dto;

import br.com.scd.demo.session.Session;

public class SessionResponseFactory {

	private SessionResponseFactory() {
		// prevents instantiation
	}

	public static SessionResponse getInstance(Session session) {
		return new SessionResponse(session.getId(), session.getTopicId(), session.getDurationInMinutes());
	}

}
