package br.com.scd.demo.api.associated.dto;

import br.com.scd.demo.associated.Associated;

public final class AssociatedResponseFactory {

	private AssociatedResponseFactory() {

	}
	
	public static AssociatedResponse getInstance(Associated associated) {
		return new AssociatedResponse(associated.getId(), associated.getCpf());
	}
}
