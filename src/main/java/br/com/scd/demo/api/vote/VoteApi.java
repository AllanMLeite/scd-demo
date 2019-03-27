package br.com.scd.demo.api.vote;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.vote.dto.VoteRequest;
import br.com.scd.demo.api.vote.dto.VoteResponse;
import br.com.scd.demo.api.vote.dto.VoteResponseFactory;
import br.com.scd.demo.vote.Vote;
import br.com.scd.demo.vote.VoteForInsert;
import br.com.scd.demo.vote.VoteForInsertFactory;
import br.com.scd.demo.vote.VoteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vote/")
public class VoteApi {

	@Autowired
	private VoteService service;

	@PostMapping
	@ApiOperation("Cadastrar voto")
	public ResponseEntity<VoteResponse> save(@RequestBody @Valid VoteRequest request) {

		VoteForInsert voteForInsert = VoteForInsertFactory.getInstance(request);

		Vote vote = service.vote(voteForInsert);

		VoteResponse response = VoteResponseFactory.getInstance(vote);

		return ResponseEntity.ok(response);
	}
}
