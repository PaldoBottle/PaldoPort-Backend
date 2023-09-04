package paldo_bottle.backend.domain.challenge.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import paldo_bottle.backend.DAO.Challenge;
import paldo_bottle.backend.DAO.QAchieve;
import paldo_bottle.backend.DAO.QChallenge;
import paldo_bottle.backend.DAO.QUser;

import javax.persistence.EntityManager;
import java.util.List;

public class AchievedChallengeRepoImpl implements AchievedChallengeRepo {
    private final JPAQueryFactory queryFactory;

    QChallenge challenge = QChallenge.challenge;
    QUser user = QUser.user;
    QAchieve achieve = QAchieve.achieve;

    public AchievedChallengeRepoImpl(EntityManager em) {
        super();
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Challenge> findAchievedChallengeList(String user_id) {
        List<Challenge> userChallenges = null;
        userChallenges = queryFactory
                .select(challenge)
                .from(achieve)
                .join(achieve.challengeName, challenge)
                .where(achieve.userId.eq(user))
                .fetch();
        return userChallenges;
    }
}
