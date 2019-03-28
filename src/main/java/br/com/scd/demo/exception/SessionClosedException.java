package br.com.scd.demo.exception;

import java.time.LocalDateTime;

public class SessionClosedException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public SessionClosedException(LocalDateTime endDate) {
		super(String.format("Sessão encerrada às %s.", endDate));
	}
}
