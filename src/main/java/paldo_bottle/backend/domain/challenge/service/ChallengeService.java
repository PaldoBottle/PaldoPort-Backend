package paldo_bottle.backend.domain.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import paldo_bottle.backend.DAO.Challenge;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DTO.challenge.AchievedChallengeParam;
import paldo_bottle.backend.DTO.challenge.AllChallengeParam;
import paldo_bottle.backend.domain.challenge.repository.ChallengeRepository;
import paldo_bottle.backend.domain.stamp.repository.StampRepository;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.global.service.JWTService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class ChallengeService {

    private final ChallengeRepository repository;
    private final JWTService jwtService;
    private final StampRepository stampRepository;

    public ChallengeService(ChallengeRepository repository, JWTService jwtService, StampRepository stampRepository) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.stampRepository = stampRepository;
    }

    //멤버가 획득한 도전과제 목록을 반환
    public List<AchievedChallengeParam> getAchievedChallenge(String user_Id) throws BaseException {
        List<AchievedChallengeParam> achievedChallengeParam = null;
        List<Challenge> achievedChallengeList = repository.findChallengesAchievedByUser(user_Id);

        //멤버가 달성한 도전과제가 없는 경우
        if(achievedChallengeList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_ACHIEVED_CHALLENGE);
        }

            for (Iterator<Challenge> iter = achievedChallengeList.iterator(); iter.hasNext(); ) {
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
        List<AllChallengeParam> allChallengeParamList = new ArrayList<>();
        List<Challenge> allchallengeList;
        List<Challenge> achievedchallengeList;

        allchallengeList = repository.findAll();
        achievedchallengeList = repository.findChallengesAchievedByUser(user_Id);

        //현재 도전과제가 없는 경우
        if(allchallengeList.isEmpty()) {
            log.error("도전과제가 없습니다.");
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

            AllChallengeParam allChallengeParam = AllChallengeParam.builder()
                    .name(challenge.getName())
                    .description(challenge.getDescription())
                    .point(challenge.getPoint())
                    .isAchieved(isAchieved)
                    .build();

            allChallengeParamList.add(allChallengeParam);
        }

        return allChallengeParamList;
    }
}
