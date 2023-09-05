package paldo_bottle.backend.domain.challenge.repository.QueryDsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import paldo_bottle.backend.DAO.Challenge;
import paldo_bottle.backend.DAO.QAchieve;
import paldo_bottle.backend.DAO.QChallenge;
import paldo_bottle.backend.DAO.QUser;

import javax.persistence.EntityManager;
import java.util.List;

import static paldo_bottle.backend.DAO.QOwnStamp.ownStamp;
import static paldo_bottle.backend.DAO.QStampChallenge.stampChallenge;

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
                .where(achieve.userId.id.eq(user_id))
                .fetch();
        return userChallenges;
    }


    public List<Challenge> findChallengesAchievedByUser(String userId) {
        List<Challenge> achievedChallenges = queryFactory
                .selectFrom(challenge)
                .join(stampChallenge).on(challenge.name.eq(stampChallenge.id.challengeName))
                .join(ownStamp).on(stampChallenge.id.location.eq(ownStamp.ownStampID.location))
                .join(user).on(ownStamp.user.id.eq(user.id))
                .where(user.id.eq(userId))
                .fetch();
        return achievedChallenges;
    }
}
