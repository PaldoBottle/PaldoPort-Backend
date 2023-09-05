package paldo_bottle.backend.domain.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.StampChallenge;


public interface StampChallengeRepository extends JpaRepository<StampChallenge, String> {
}
