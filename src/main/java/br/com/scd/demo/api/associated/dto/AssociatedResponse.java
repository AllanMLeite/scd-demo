package br.com.scd.demo.api.associated.dto;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class AssociatedResponse {

	private final Long id;
	
	private final String cpf;

	public AssociatedResponse(Long id, String cpf) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(cpf, "cpf cannot be null");

		this.id = id;
		this.cpf = cpf;
	} 
}
