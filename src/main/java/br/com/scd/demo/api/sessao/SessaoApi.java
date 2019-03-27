package br.com.scd.demo.api.sessao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.sessao.dto.SessaoRequest;
import br.com.scd.demo.api.sessao.dto.SessaoResponse;
import br.com.scd.demo.api.sessao.dto.SessaoResponseFactory;
import br.com.scd.demo.sessao.Sessao;
import br.com.scd.demo.sessao.SessaoForInsert;
import br.com.scd.demo.sessao.SessaoForInsertFactory;
import br.com.scd.demo.sessao.SessaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sessao/")
public class SessaoApi {

	@Autowired
	private SessaoService sessaoService;

	@PostMapping
	@ApiOperation("Abrir sessão de votação")
	public ResponseEntity<SessaoResponse> save(@RequestBody @Valid SessaoRequest request) {

		SessaoForInsert sessaoForInsert = SessaoForInsertFactory.getInstance(request);

		Sessao sessao = sessaoService.save(sessaoForInsert);

		SessaoResponse response = SessaoResponseFactory.getInstance(sessao);

		return ResponseEntity.ok(response);
	}
}
