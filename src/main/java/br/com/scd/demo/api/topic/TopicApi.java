package br.com.scd.demo.api.topic;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.topic.dto.TopicRequest;
import br.com.scd.demo.api.topic.dto.TopicResponse;
import br.com.scd.demo.api.topic.dto.TopicResponseFactory;
import br.com.scd.demo.api.topic.dto.TopicResultResponse;
import br.com.scd.demo.api.topic.dto.TopicResultResponseFactory;
import br.com.scd.demo.topic.Topic;
import br.com.scd.demo.topic.TopicForInsert;
import br.com.scd.demo.topic.TopicForInsertFactory;
import br.com.scd.demo.topic.TopicResult;
import br.com.scd.demo.topic.TopicService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/topics")
public class TopicApi {

	@Autowired
	private TopicService topicService;
	
	@PostMapping
	@ApiOperation("Cadastrar pauta de votação.")
	public ResponseEntity<TopicResponse> save(@RequestBody @Valid TopicRequest request) {
		
        log.info("Cadastrando pauta de votação: {}", request);
        
		TopicForInsert topicForInsert = TopicForInsertFactory.getInstance(request);
		
		Topic topic = topicService.save(topicForInsert);
		
		TopicResponse response = TopicResponseFactory.getInstance(topic);
		
		log.info("Cadastrada pauta de votação: {}", response);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/result/{topicId}")
	@ApiOperation("Resultado de votos de uma pauta.")
	public ResponseEntity<TopicResultResponse> getTopicResult(@PathVariable Long topicId) {
		
        log.info("Verificando o resultado da pauta de votação id: {}", topicId);
        
		TopicResult topicResult = topicService.findByIdWithSessionResult(topicId);
		
		TopicResultResponse topicResultResponse = TopicResultResponseFactory.getInstance(topicResult);
		
        log.info("Resultado da pauta de votação: {}", topicResultResponse);
		
		return ResponseEntity.ok(topicResultResponse);
	}
}
