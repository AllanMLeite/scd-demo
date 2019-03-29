package br.com.scd.demo.api.vote.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.scd.demo.converter.VoteEnumConverter;
import br.com.scd.demo.enums.VoteEnum;
import lombok.Getter;

@Getter
public class VoteRequest {

	@NotNull(message = "Id da sessao deve ser informado.")
	private Long sessionId;
	
	@NotNull(message = "Id do associado deve ser informado.")
	private Long associatedId;
	
	@NotNull(message = "Voto deve ser informado.")
	@JsonDeserialize(converter=VoteEnumConverter.class)
	private VoteEnum vote;
}
