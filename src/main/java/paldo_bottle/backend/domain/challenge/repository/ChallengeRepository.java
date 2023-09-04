package paldo_bottle.backend.domain.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paldo_bottle.backend.DAO.Challenge;
import paldo_bottle.backend.domain.challenge.repository.QueryDsl.AchievedChallengeRepo;

public interface ChallengeRepository extends
        JpaRepository<Challenge, String>,
        AchievedChallengeRepo
{}
