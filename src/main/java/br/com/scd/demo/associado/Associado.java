package br.com.scd.demo.associado;

import org.springframework.util.Assert;

import lombok.Getter;

public class Associado {

	@Getter
	private Long id;

	public Associado(Long id) {
		Assert.notNull(id, "id cannot be null");
		
		this.id = id;
	} 
}
