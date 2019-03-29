package br.com.scd.demo.topic;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.enums.StatusEnum;
import br.com.scd.demo.session.SessionEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "topic")
public class TopicEntity extends BaseEntity {

	@Setter
	private String subject;
	
	@OneToOne(mappedBy="topic")
	private SessionEntity session;
	
	public StatusEnum getStatus() {
		if (sessionDoesntExists()) {
			return StatusEnum.NAO_INICIADA;
		}

		return session.isClosed() ? StatusEnum.FINALIZADA : StatusEnum.ABERTA;
	}
	
	public boolean sessionExists() {
		return !sessionDoesntExists();
	}
	
	public boolean sessionDoesntExists() {
		return Objects.isNull(session);
	}
}
