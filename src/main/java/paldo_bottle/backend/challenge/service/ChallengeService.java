package paldo_bottle.backend.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import paldo_bottle.backend.DAO.Challenge;
import paldo_bottle.backend.DTO.challenge.AchievedChallengeParam;
import paldo_bottle.backend.DTO.challenge.AllChallengeParam;
import paldo_bottle.backend.challenge.repository.ChallengeRepository;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.global.service.JWTService;

import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ChallengeService {

    private final ChallengeRepository repository;
    private final JWTService jwtService;

    public ChallengeService(ChallengeRepository repository, JWTService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    //멤버가 획득한 도전과제 목록을 반환
    public List<AchievedChallengeParam> getAchievedChallenge(String user_Id) throws BaseException {
        List<Challenge> userChallenges = null;
        List<AchievedChallengeParam> achievedChallengeParam = null;

        userChallenges = repository.findAchievedChallengeList(user_Id);

        //멤버가 달성한 도전과제가 없는 경우
        if(userChallenges == null) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_ACHIEVED_CHALLENGE);
        }

            for (Iterator<Challenge> iter = userChallenges.iterator(); iter.hasNext(); ) {
                Challenge challenge = iter.next();
                achievedChallengeParam.add(new AchievedChallengeParam().builder()
                        .name(challenge.getName())
                        .description(challenge.getDescription())
                        .point(challenge.getPoint())
                        .build());
            }
        return achievedChallengeParam;
    }

    //모든 도전과제 목록을 반환
    public List<AllChallengeParam> getAllChallenge(String user_Id) throws BaseException {
        List<AllChallengeParam> allChallengeParam = null;
        List<Challenge> allchallengeList;
        List<Challenge> achievedchallengeList;

        allchallengeList = repository.findAll();
        achievedchallengeList = repository.findAchievedChallengeList(user_Id);

        //현재 도전과제가 없는 경우
        if(allchallengeList == null) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_CHALLENGE);
        }

        for(Iterator<Challenge> iter = allchallengeList.iterator(); iter.hasNext(); ) {
            Challenge challenge = iter.next();
            Boolean isAchieved = false;

            for(Iterator<Challenge> iter2 = achievedchallengeList.iterator(); iter2.hasNext(); ) {
                Challenge achievedChallenge = iter2.next();
                if(challenge.getName().equals(achievedChallenge.getName())) {
                    isAchieved = true;
                }
            }

            allChallengeParam.add(new AllChallengeParam().builder()
                    .name(challenge.getName())
                    .description(challenge.getDescription())
                    .point(challenge.getPoint())
                    .isAchieved(isAchieved)
                    .build());
        }

        return allChallengeParam;
    }
}
