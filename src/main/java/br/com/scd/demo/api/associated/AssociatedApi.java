package br.com.scd.demo.api.associated;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.scd.demo.api.associated.dto.AssociatedResponse;
import br.com.scd.demo.api.associated.dto.AssociatedResposeFactory;
import br.com.scd.demo.associated.Associated;
import br.com.scd.demo.associated.AssociatedService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/associated/")
public class AssociatedApi {

	@Autowired
	private AssociatedService associatedService;

	@GetMapping
	@ApiOperation("Listagem dos associados disponíveis para votação.")
	public ResponseEntity<List<AssociatedResponse>> findAll() {

		List<Associated> associateds = associatedService.findAll();

		List<AssociatedResponse> response = associateds.stream()
				.map(AssociatedResposeFactory::getInstance)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(response);
	}
}
