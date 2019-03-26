package br.com.scd.demo.pauta;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pauta")
@NoArgsConstructor
public class PautaEntity extends BaseEntity {

	@Getter
	@Setter
	private String subject;
}
