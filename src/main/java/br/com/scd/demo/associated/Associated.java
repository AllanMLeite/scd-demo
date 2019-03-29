package br.com.scd.demo.associated;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
public class Associated {

	private Long id;
	
	private String cpf;

	public Associated(Long id, String cpf) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(cpf, "cpf cannot be null");

		this.id = id;
		this.cpf = cpf;
	} 
}
