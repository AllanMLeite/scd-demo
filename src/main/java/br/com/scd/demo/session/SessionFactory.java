package br.com.scd.demo.session;

public class SessionFactory {

	private SessionFactory() {

	}

	public static Session getInstance(SessionEntity sessionEntity) {
		return new Session(sessionEntity.getId(), sessionEntity.getTopic().getId(), sessionEntity.getDurationInMinutes());
	}

}
