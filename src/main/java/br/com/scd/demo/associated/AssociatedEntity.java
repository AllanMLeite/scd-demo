package br.com.scd.demo.associated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "associated")
public class AssociatedEntity extends BaseEntity {

	@Column(name = "cpf")
	private String cpf;
}
