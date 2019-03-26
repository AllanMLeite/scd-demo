package br.com.scd.demo.pauta;

import org.springframework.util.Assert;

import lombok.Getter;

public class PautaForInsert {

	@Getter
	private final String subject;
	
	public PautaForInsert(String subject) {
		
		Assert.hasLength(subject, "subject cannot be blank");
		
		this.subject = subject;
	}
}
