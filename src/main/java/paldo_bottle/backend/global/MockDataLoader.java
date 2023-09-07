package paldo_bottle.backend.global;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.*;
import paldo_bottle.backend.DAO.embedded.RegionID;
import paldo_bottle.backend.domain.challenge.repository.ChallengeRepository;
import paldo_bottle.backend.domain.challenge.repository.StampChallengeRepository;
import paldo_bottle.backend.domain.member.repository.MemberRepository;
import paldo_bottle.backend.domain.region.repository.RegionRepository;
import paldo_bottle.backend.domain.stamp.repository.StampRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MockDataLoader implements ApplicationRunner {
    private final RegionRepository regionRepository;
    private final StampRepository stampRepository;
    private final MemberRepository userRepository;
    private final ChallengeRepository challengeRepository;

    private final StampChallengeRepository stampChallengeRepository;


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        loadUser();
        loadRegionStamp();
        loadChallenge();
    }

    private void loadUser() {
        User user = new User("paldomaster");
        userRepository.save(user);
    }

    private void loadRegionStamp() {
        List<Region> regions = new ArrayList<>();

        Region jonggu = create_region_stamp(
                "서울시", "중구", "동국대가 있습니다", 10L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Seoul_Jung-gu.png"
        );
        Landmark jonggu_landmark = new Landmark("타임캡슐", "100년 된 캡슐입니다.", 126.9939, 37.5573);
        jonggu.addLandmark(jonggu_landmark);
        regions.add(jonggu);

        Region jecheon = create_region_stamp(
                "충청남도", "제천시", "의림지가 있습니다", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Chungcheongnam-do_Jecheon-si.png"
        );
        Landmark jecheon_landmark = new Landmark("의림지", "의림지입니다.", 128.2105, 37.1732);
        jecheon.addLandmark(jecheon_landmark);
        regions.add(jecheon);

        Region buyeo = create_region_stamp("충청북도", "부여군", "부여입니다", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Chungcheongbuk-do_Buyeo-gun.png");
        Landmark buyeo_landmark = new Landmark("사랑나무", "성흥산에 있습니다", 126.9, 36.1959);
        buyeo.addLandmark(buyeo_landmark);
        regions.add(buyeo);

        Region changyeong = create_region_stamp("경상남도", "창녕군", "창녕입니다.", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Gyeongsangnam-do_Changnyeong-gun.png");
        Landmark changyeong_landmark = new Landmark("화왕산성", "임진왜란 때 군사적 요충지였습니다.", 128.5374, 35.5458);
        changyeong.addLandmark(changyeong_landmark);
        regions.add(changyeong);

        Region namwon = create_region_stamp("전라북도", "남원시", "남원시입니다.", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Jeollabuk-do_Namwon-si.png");
        Landmark namwon_landmark = new Landmark("광한루", "춘향전의 배경입니다", 127.3794, 35.4038);
        namwon.addLandmark(namwon_landmark);
        regions.add(namwon);

        Region wando = create_region_stamp("전라남도", "완도군", "완도군입니다.", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Jeollanam-do_Wando-gun.png");
        Landmark wando_landmark = new Landmark("청해진 유적지", "청해진 유적지입니다.", 126.7352, 34.3586);
        wando.addLandmark(wando_landmark);
        regions.add(wando);

        Region cheorwon = create_region_stamp("강원도", "철원군", "철원군입니다.", 15L,
                "https://stampimage.s3.ap-northeast-2.amazonaws.com/Gangwon-do_Cheorwon-gun.png");
        Landmark cheorwon_landmark = new Landmark("철원 노동당사", "북한이 건설했던 노동당사입니다.", 127.2019, 38.2551);
        cheorwon.addLandmark(cheorwon_landmark);
        regions.add(cheorwon);

        regionRepository.saveAll(regions);
    }

    private void loadChallenge() {
        List<Challenge> challenges = new ArrayList<>();

        Stamp stamp1 = stampRepository.findById(new RegionID("충청남도", "제천시")).get();
        Stamp stamp2 = stampRepository.findById(new RegionID("충청북도", "부여군")).get();
        Stamp se_jong = stampRepository.findById(new RegionID("서울시", "중구")).get();
        Stamp jn_wando = stampRepository.findById(new RegionID("전라남도", "완도군")).get();
        Stamp gang_cheorwon = stampRepository.findById(new RegionID("강원도", "철원군")).get();
        Stamp jb_namwon = stampRepository.findById(new RegionID("전라북도", "남원시")).get();
        Stamp gn_changyeong = stampRepository.findById(new RegionID("경상남도", "창녕군")).get();

        Challenge chungcheongMaster = new Challenge("충청도 마스터", "충청남도 제천시와 충청북도 부여군 방문 시 획득 가능", 100L);
        StampChallenge stampChallenge1 = StampChallenge.createStampChallenge(chungcheongMaster, stamp1);
        StampChallenge stampChallenge2 = StampChallenge.createStampChallenge(chungcheongMaster, stamp2);

        chungcheongMaster.add(stampChallenge1);
        chungcheongMaster.add(stampChallenge2);
        challenges.add(chungcheongMaster);

        Challenge jonggubuyeoMaster = new Challenge("중구 부여 마스터", "서울특별시 중구와 충청북도 부여 방문 시 획득 가능", 150L);
        StampChallenge stampChallenge3 = StampChallenge.createStampChallenge(jonggubuyeoMaster, stamp2);
        StampChallenge stampChallenge4 = StampChallenge.createStampChallenge(jonggubuyeoMaster, se_jong);

        jonggubuyeoMaster.add(stampChallenge3);
        jonggubuyeoMaster.add(stampChallenge4);
        challenges.add(jonggubuyeoMaster);

        challenges.add(
                make_challenge(
                        "전라도 마스터",
                        "전라도 전 지역 방문시 획득",
                        150L,
                        jb_namwon,
                        jn_wando)
        );
        challenges.add(
                make_challenge("서울시 마스터",
                        "서울시 전 지역 방문시 획득",
                        150L,
                        se_jong)
        );
        challenges.add(
                make_challenge(
                        "분단의 아픔",
                        "6.25 관련 시설 전 지역 방문시 획득",
                        150L,
                        gang_cheorwon
                )
        );
        challenges.add(
                make_challenge(
                        "왜군 격퇴",
                        "일본 군의 침략 관련 전 지역 방문시 획득",
                        150L,
                        jn_wando,
                        gn_changyeong
                )
        );

        challengeRepository.saveAll(challenges);
    }

    private Region create_region_stamp(String supDistrict, String district, String region_description
            , Long point, String imageUrl) {
        Region region = Region.builder()
                .supDistrict(supDistrict)
                .district(district)
                .description(region_description)
                .build();
        Stamp stamp = new Stamp(point, imageUrl);
        stamp.setRegion(region);
        region.setStamp(stamp);
        return region;
    }

    private Challenge make_challenge(String challenge_name, String challenge_description, Long challenge_point,
                                    Stamp ... stamps) {
        Challenge challenge = new Challenge(challenge_name, challenge_description, challenge_point);
        for (Stamp stamp : stamps) {
            challenge.add(StampChallenge.createStampChallenge(challenge, stamp));
        }
        return challenge;
    }
}
