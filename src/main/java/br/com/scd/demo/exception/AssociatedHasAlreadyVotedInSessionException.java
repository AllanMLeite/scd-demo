package br.com.scd.demo.exception;

public class AssociatedHasAlreadyVotedInSessionException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public AssociatedHasAlreadyVotedInSessionException() {
		super("Cada associado pode votar somente uma vez por pauta.");
	}
}
