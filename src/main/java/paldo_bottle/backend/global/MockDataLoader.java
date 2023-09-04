package paldo_bottle.backend.global;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Landmark;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.region.repository.RegionRepository;
import paldo_bottle.backend.stamp.repository.StampRepository;
import paldo_bottle.backend.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MockDataLoader implements ApplicationRunner {
    private final RegionRepository regionRepository;
    private final StampRepository stampRepository;
    private final UserRepository userRepository;



    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        loadUser();
        loadRegionStamp();
    }

    private void loadUser() {
        User user = new User("test");
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
