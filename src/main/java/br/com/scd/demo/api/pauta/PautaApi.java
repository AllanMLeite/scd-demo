package br.com.scd.demo.api.pauta;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.pauta.dto.PautaRequest;
import br.com.scd.demo.api.pauta.dto.PautaResponse;
import br.com.scd.demo.api.pauta.dto.PautaResponseFactory;
import br.com.scd.demo.pauta.Pauta;
import br.com.scd.demo.pauta.PautaForInsert;
import br.com.scd.demo.pauta.PautaForInsertFactory;
import br.com.scd.demo.pauta.PautaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pauta/")
public class PautaApi {

	@Autowired
	private PautaService pautaService;
	
	@PostMapping
	@ApiOperation("Cadastrar pauta")
	public ResponseEntity<PautaResponse> save(@RequestBody @Valid PautaRequest request) {
		
		PautaForInsert pautaForInsert = PautaForInsertFactory.getInstance(request);
		
		Pauta pauta = pautaService.save(pautaForInsert);
		
		PautaResponse response = PautaResponseFactory.getInstance(pauta);
		
		return ResponseEntity.ok(response);
	}
}
