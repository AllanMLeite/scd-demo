package br.com.scd.demo.api.associated.dto;

import org.springframework.util.Assert;

import lombok.Getter;

public class AssociatedResponse {

	@Getter
	private Long id;
	
	@Getter
	private String cpf;

	public AssociatedResponse(Long id, String cpf) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(cpf, "cpf cannot be null");

		this.id = id;
		this.cpf = cpf;
	} 
}
