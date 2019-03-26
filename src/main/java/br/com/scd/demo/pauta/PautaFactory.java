package br.com.scd.demo.pauta;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import br.com.scd.demo.api.pauta.PautaRequest;
import br.com.scd.demo.associado.Associado;
import br.com.scd.demo.associado.AssociadoFactory;

public class PautaFactory {

	private PautaFactory() {
		// prevents instantiation
	}

	public static PautaForInsert getInstance(@Valid PautaRequest request) {
		List<Associado> associateds = request.getAssociateds()
				.stream()
				.map(AssociadoFactory::getInstance)
				.collect(Collectors.toList());
		
		return new PautaForInsert(request.getSubject(), associateds);
	}
}
