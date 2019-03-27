package br.com.scd.demo.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.topic.TopicEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "session")
public class SessionEntity extends BaseEntity {

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private TopicEntity topic;

	@Getter
	@Setter
	@Column(name = "duration_in_minutes")
	private Integer durationInMinutes;
}
