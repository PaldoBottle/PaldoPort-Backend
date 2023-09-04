package paldo_bottle.backend.challenge.repository.QueryDsl;

import paldo_bottle.backend.DAO.Challenge;

import java.util.List;

public interface AchievedChallengeRepo {
    public List<Challenge> findAchievedChallengeList(String user_id);

}
