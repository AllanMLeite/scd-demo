package br.com.scd.demo.topic;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "topic")
public class TopicEntity extends BaseEntity {

	@Getter
	@Setter
	private String subject;
}
