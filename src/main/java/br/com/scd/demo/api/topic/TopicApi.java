package br.com.scd.demo.api.topic;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.topic.dto.TopicRequest;
import br.com.scd.demo.api.topic.dto.TopicResponse;
import br.com.scd.demo.api.topic.dto.TopicResponseFactory;
import br.com.scd.demo.topic.Topic;
import br.com.scd.demo.topic.TopicForInsert;
import br.com.scd.demo.topic.TopicForInsertFactory;
import br.com.scd.demo.topic.TopicService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/topic/")
public class TopicApi {

	@Autowired
	private TopicService topicService;
	
	@PostMapping
	@ApiOperation("Cadastrar pauta de votação.")
	public ResponseEntity<TopicResponse> save(@RequestBody @Valid TopicRequest request) {
		
		TopicForInsert topicForInsert = TopicForInsertFactory.getInstance(request);
		
		Topic topic = topicService.save(topicForInsert);
		
		TopicResponse response = TopicResponseFactory.getInstance(topic);
		
		return ResponseEntity.ok(response);
	}
}
