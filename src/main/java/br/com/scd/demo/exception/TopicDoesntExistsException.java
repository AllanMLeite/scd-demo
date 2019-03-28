package br.com.scd.demo.exception;

public class TopicDoesntExistsException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public TopicDoesntExistsException() {
		super("Id da pauta inexistente.");
	}
}
