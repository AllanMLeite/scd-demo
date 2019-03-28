package br.com.scd.demo.exception;

public class VoteNotFoundException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public VoteNotFoundException(String vote) {
		super(String.format("Voto invalido: %s", vote));
	}
}
