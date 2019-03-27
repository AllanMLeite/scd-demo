package br.com.scd.demo.api.session.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;

public class SessionRequest {

	@Getter
	@NotNull(message = "Id da pauta deve ser informado.")
	private Long topicId;

	@Getter
	private Integer durationInMinutes;
}
