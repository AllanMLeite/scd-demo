package br.com.scd.demo.converter;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.util.StdConverter;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.exception.VoteNotFoundException;

public class VoteEnumConverter extends StdConverter<String, VoteEnum> {

	@Override
	public VoteEnum convert(String vote) {
		for (VoteEnum e : VoteEnum.values()) {
			if (StringUtils.equalsIgnoreCase(e.name(), vote))
				return e;
		}
		throw new VoteNotFoundException(vote);
	}
}
