package br.com.scd.demo.converter;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.exception.VoteNotFoundException;

public class VoteEnumConverter extends StdConverter<String, VoteEnum> {

	@Override
	public VoteEnum convert(String vote) {
		return Arrays.asList(VoteEnum.values()).stream()
			.filter(e -> StringUtils.equalsIgnoreCase(e.name(), vote))
			.findFirst()
			.orElseThrow(() -> new VoteNotFoundException(vote));
	}
}
