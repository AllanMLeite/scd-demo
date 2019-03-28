package br.com.scd.demo.exception;

public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = -9042865677332029985L;

	public InvalidArgumentException(String message) {
		super(message);
	}
}
