package br.com.scd.demo.exception;

public class SessionAlreadyOpenedException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public SessionAlreadyOpenedException() {
		super("Sessão já iniciada para a pauta informada.");
	}
}
