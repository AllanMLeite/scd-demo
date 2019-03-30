package br.com.scd.demo.api.session;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.session.dto.SessionRequest;
import br.com.scd.demo.api.session.dto.SessionResponse;
import br.com.scd.demo.api.session.dto.SessionResponseFactory;
import br.com.scd.demo.session.Session;
import br.com.scd.demo.session.SessionForInsert;
import br.com.scd.demo.session.SessionForInsertFactory;
import br.com.scd.demo.session.SessionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sessions")
public class SessionApi {

	@Autowired
	private SessionService sessionService;

	@PostMapping
	@ApiOperation("Abrir sessão de votação.")
	public ResponseEntity<SessionResponse> openSession(@RequestBody @Valid SessionRequest request) {

		log.info("Abrindo sessão de votação: {}", request);

		SessionForInsert sessionForInsert = SessionForInsertFactory.getInstance(request);

		Session session = sessionService.save(sessionForInsert);

		SessionResponse response = SessionResponseFactory.getInstance(session);

		log.info("Sessão de votação aberta: {}", response);

		return ResponseEntity.ok(response);
	}
}
