package br.com.scd.demo.pauta;

import org.springframework.util.Assert;

import lombok.Getter;

public class Pauta {

	@Getter
	private final Long id;
	
	@Getter
	private final String subject;
	
	public Pauta(Long id, String subject) {
		
		Assert.notNull(id, "id cannot be null");
		Assert.hasLength(subject, "subject cannot be blank");
		
		this.id = id;
		this.subject = subject;
	}
	
}
