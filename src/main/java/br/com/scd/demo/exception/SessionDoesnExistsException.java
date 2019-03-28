package br.com.scd.demo.exception;

public class SessionDoesnExistsException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public SessionDoesnExistsException() {
		super("Id da sessão inexistente.");
	}
}
