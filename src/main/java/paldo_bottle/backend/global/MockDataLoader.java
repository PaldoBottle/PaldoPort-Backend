package paldo_bottle.backend.global;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.*;
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
    private final StampChallenge stampChallenge;
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

        regionRepository.saveAll(regions);
    }

    private void loadChallenge() {
        List<Challenge> challenges = new ArrayList<>();

        Challenge chungcheongMaster = new Challenge("충청도 마스터", "충청남도 제역시와 충청북도 부여군 방문 시 획득 가능", 100L);
        challenges.add(chungcheongMaster);

        StampChallenge chungcheongMasterStamp1 = new StampChallenge("충청도 마스터", "충청남도", "제역시");

        StampChallenge chungcheongMasterStamp2 = new StampChallenge("충청도 마스터", "충청북도", "부여군");

        stampChallengeRepository.save(chungcheongMasterStamp1);
        stampChallengeRepository.save(chungcheongMasterStamp2);



        Challenge jonggubuyeoMaster = new Challenge("중구 부여 마스터", "서울특별시 중구와 충청북도 부여 방문 시 획득 가능", 150L);
        challenges.add(jonggubuyeoMaster);


        StampChallenge jonggubuyeoMasterStamp1 = new StampChallenge("중구 부여 마스터", "서울특별시", "중구");
        StampChallenge jonggubuyeoMasterStamp2 = new StampChallenge("중구 부여 마스터", "충청북도", "부여군");

        stampChallengeRepository.save(jonggubuyeoMasterStamp1);
        stampChallengeRepository.save(jonggubuyeoMasterStamp2);

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
}
