package br.com.scd.demo.session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.scd.demo.entity.BaseEntity;
import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.topic.TopicEntity;
import br.com.scd.demo.vote.VoteEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name = "session")
public class SessionEntity extends BaseEntity {

	@Setter
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private TopicEntity topic;

	@OneToMany(mappedBy = "session")
	private List<VoteEntity> votes = new ArrayList<>();

	@Setter
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

	public Map<VoteEnum, Long> getTotalVotes() {
		return getVotes().stream().map(VoteEntity::getVote)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	public TopicResultEnum getSessionResult() {

		Map<VoteEnum, Long> totalVotesMap = getTotalVotes();

		if (totalVotesMap.isEmpty()) {
			return TopicResultEnum.NENHUM_VOTO;
		}
		
		if(thereWasTieOfVotes(totalVotesMap)) {
			return TopicResultEnum.EMPATE;
		}

		return getSessionResult(totalVotesMap);
	}

	private TopicResultEnum getSessionResult(Map<VoteEnum, Long> totalVotesMap) {
		return winnerVoteIsYes(totalVotesMap) ? TopicResultEnum.APROVADA : TopicResultEnum.REPROVADA;
	}

	private boolean thereWasTieOfVotes(Map<VoteEnum, Long> totalVotesMap) {
		return allTypesOfVotesWereUsed(totalVotesMap) && totalVotesOfEachTypeAreEquals(totalVotesMap);
	}

	private boolean allTypesOfVotesWereUsed(Map<VoteEnum, Long> totalVotesMap) {
		return totalVotesMap.size() == VoteEnum.values().length;
	}

	private boolean totalVotesOfEachTypeAreEquals(Map<VoteEnum, Long> totalVotesMap) {
		return totalVotesMap.entrySet().stream().mapToLong(Map.Entry::getValue).distinct().count() <= 1l;
	}

	private boolean winnerVoteIsYes(Map<VoteEnum, Long> totalVotesMap) {
		return VoteEnum.SIM.equals(getWinnerVote(totalVotesMap));
	}

	private VoteEnum getWinnerVote(Map<VoteEnum, Long> totalVotesMap) {
		Optional<Entry<VoteEnum, Long>> winnerVote = totalVotesMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue));
		return winnerVote.isPresent() ? winnerVote.get().getKey() : null;
	}
}
