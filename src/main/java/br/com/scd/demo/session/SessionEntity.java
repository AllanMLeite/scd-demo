package br.com.scd.demo.session;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.topic.TopicEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "session")
public class SessionEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "topic_id")
	private TopicEntity topic;

	@Column(name = "duration_in_minutes")
	private Integer durationInMinutes;

	@Column(name = "date_added")
	private LocalDateTime dateAdded;

	@PrePersist
	public void setCreatedNow() {
		dateAdded = LocalDateTime.now();
	}

	public boolean isClosed() {
		return getEndDate().isBefore(LocalDateTime.now());
	}

	public LocalDateTime getEndDate() {
		return dateAdded.plusMinutes(durationInMinutes);
	}
}
