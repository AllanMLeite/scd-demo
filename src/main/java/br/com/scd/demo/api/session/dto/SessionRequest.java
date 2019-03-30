package br.com.scd.demo.api.session.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SessionRequest {

	@NotNull(message = "Id da pauta deve ser informado.")
	private Long topicId;

	private Integer durationInMinutes;
}
