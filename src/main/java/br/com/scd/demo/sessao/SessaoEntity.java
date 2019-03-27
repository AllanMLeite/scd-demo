package br.com.scd.demo.sessao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.pauta.PautaEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sessao")
public class SessaoEntity extends BaseEntity {

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private PautaEntity pauta;

	@Getter
	@Setter
	@Column(name = "duration_in_minutes")
	private Integer durationInMinutes;
}
