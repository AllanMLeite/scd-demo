package br.com.scd.demo.vote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.session.SessionEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vote")
public class VoteEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "session_id")
	private SessionEntity session;

	@Column(name = "associated_id")
	private Long associatedId;

	@Enumerated(EnumType.STRING)
	@Column(name = "vote")
	private VoteEnum vote;
}
