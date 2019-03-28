package br.com.scd.demo.api.associated.dto;

import br.com.scd.demo.associated.Associated;

public class AssociatedResposeFactory {

	private AssociatedResposeFactory() {
		// prevents instantiation
	}
	
	public static AssociatedResponse getInstance(Associated associated) {
		return new AssociatedResponse(associated.getId(), associated.getCpf());
	}
}
