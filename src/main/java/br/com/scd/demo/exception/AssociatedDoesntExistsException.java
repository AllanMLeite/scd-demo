package br.com.scd.demo.exception;

public class AssociatedDoesntExistsException extends InvalidArgumentException {

	private static final long serialVersionUID = 1L;

	public AssociatedDoesntExistsException() {
		super("Id do associado inexistente.");
	}

}
