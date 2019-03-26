package br.com.scd.demo.pauta;

import java.util.List;

import org.springframework.util.Assert;

import br.com.scd.demo.associado.Associado;
import lombok.Getter;

public class PautaForInsert {

	@Getter
	private final String subject;
	
	@Getter
	private final List<Associado> associateds;

	public PautaForInsert(String subject, List<Associado> associateds) {
		
		Assert.hasLength(subject, "subject cannot be blank");
		Assert.notEmpty(associateds, "associateds cannot be empty");
		
		this.subject = subject;
		this.associateds = associateds;
	}
}
