package br.com.scd.demo.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Test;

import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.exception.VoteNotFoundException;

public class VoteEnumConverterTest {

	final VoteEnumConverter converter = new VoteEnumConverter();
	
	@Test
	public void shouldThrowExceptionWhenVoteUnknown() {
		assertThatThrownBy(() -> converter.convert("unknown"))
			.isInstanceOf(VoteNotFoundException.class)
			.hasMessage("Voto invalido: unknown");		
	}
	
	@Test
	public void shouldConvert() {
		assertThat(converter.convert("sim"))
			.isEqualTo(VoteEnum.SIM);
	}
}
