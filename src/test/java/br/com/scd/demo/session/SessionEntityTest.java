package br.com.scd.demo.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.scd.demo.enums.TopicResultEnum;
import br.com.scd.demo.enums.VoteEnum;
import br.com.scd.demo.vote.VoteEntity;

public class SessionEntityTest {

	@Test
	public void shouldGetEndDate() {
		LocalDateTime now = LocalDateTime.now();

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", now);
		sessionEntity.setDurationInMinutes(10);

		assertThat(sessionEntity.getEndDate()).isEqualTo(now.plusMinutes(10));
	}

	@Test
	public void shouldGetIsClosedTrue() {
		LocalDateTime now = LocalDateTime.now();

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", now.minusMinutes(5));
		sessionEntity.setDurationInMinutes(2);

		assertTrue(sessionEntity.isClosed());
	}

	@Test
	public void shouldGetIsClosedFalse() {
		LocalDateTime now = LocalDateTime.now();

		SessionEntity sessionEntity = new SessionEntity();
		ReflectionTestUtils.setField(sessionEntity, "dateAdded", now);
		sessionEntity.setDurationInMinutes(1);

		assertFalse(sessionEntity.isClosed());
	}

	@Test
	public void shouldGetTotalVotes() {

		SessionEntity sessionEntity = new SessionEntity();
		VoteEntity voteX = new VoteEntity();
		voteX.setVote(VoteEnum.SIM);
		VoteEntity voteY = new VoteEntity();
		voteY.setVote(VoteEnum.SIM);
		VoteEntity voteZ = new VoteEntity();
		voteZ.setVote(VoteEnum.NAO);
		ReflectionTestUtils.setField(sessionEntity, "votes", Arrays.asList(voteX, voteY, voteZ));

		Map<VoteEnum, Long> totalVotesExpected = new HashMap<>();
		totalVotesExpected.put(VoteEnum.NAO, 1l);
		totalVotesExpected.put(VoteEnum.SIM, 2l);

		assertThat(sessionEntity.getTotalVotes()).isEqualToComparingFieldByFieldRecursively(totalVotesExpected);
	}

	@Test
	public void shouldGetSessionResultNenhumVoto() {
		assertThat(new SessionEntity().getSessionResult()).isEqualTo(TopicResultEnum.NENHUM_VOTO);
	}

	@Test
	public void shouldGetSessionResultEmpate() {

		SessionEntity sessionEntity = new SessionEntity();
		VoteEntity voteX = new VoteEntity();
		voteX.setVote(VoteEnum.SIM);
		VoteEntity voteZ = new VoteEntity();
		voteZ.setVote(VoteEnum.NAO);
		ReflectionTestUtils.setField(sessionEntity, "votes", Arrays.asList(voteX, voteZ));

		assertThat(sessionEntity.getSessionResult()).isEqualTo(TopicResultEnum.EMPATE);
	}
	
	@Test
	public void shouldGetSessionResultAprovada() {

		SessionEntity sessionEntity = new SessionEntity();
		VoteEntity voteX = new VoteEntity();
		voteX.setVote(VoteEnum.SIM);
		ReflectionTestUtils.setField(sessionEntity, "votes", Arrays.asList(voteX));

		assertThat(sessionEntity.getSessionResult()).isEqualTo(TopicResultEnum.APROVADA);
	}
	
	@Test
	public void shouldGetSessionResultReprovada() {

		SessionEntity sessionEntity = new SessionEntity();
		VoteEntity voteX = new VoteEntity();
		voteX.setVote(VoteEnum.NAO);
		ReflectionTestUtils.setField(sessionEntity, "votes", Arrays.asList(voteX));

		assertThat(sessionEntity.getSessionResult()).isEqualTo(TopicResultEnum.REPROVADA);
	}

}
