package paldo_bottle.backend.stamp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.domain.member.repository.MemberRepository;
import paldo_bottle.backend.domain.region.repository.RegionRepository;
import paldo_bottle.backend.domain.stamp.repository.StampRepository;
import paldo_bottle.backend.domain.stamp.service.StampService;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StampRepositoryTest {
    private final StampRepository stampRepository;
    private final StampService stampService;
    private final MemberRepository userRepository;
    private final RegionRepository regionRepository;

    @Autowired
    StampRepositoryTest(StampRepository stampRepository,
                        MemberRepository userRepository,
                        RegionRepository regionRepository,
                        StampService stampService) {
        this.stampRepository = stampRepository;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.stampService = stampService;
    }

    @Test
    @Transactional
    public void 스탬프_유저_조인_테스트_정상() throws Exception {
        //given
        User user = new User("test");
        userRepository.save(user);
        ArrayList<Stamp> stamps = new ArrayList<>();
        ArrayList<String>   supDistricts = new ArrayList<>();
        ArrayList<String>   districts = new ArrayList<>();
        ArrayList<Long>  points = new ArrayList<>();
        supDistricts.add("서울시");
        supDistricts.add("서울시");
        supDistricts.add("서울시");
        districts.add("강남구");
        districts.add("성북구");
        districts.add("중구");
        points.add(10L);
        points.add(10L);
        points.add(10L);
        for (int i = 0 ; i < 3 ; i++) {
            Region region_stamp = create_region_stamp(supDistricts.get(i), districts.get(i), "", points.get(i));
            Region saveRegion = regionRepository.save(region_stamp);
            stamps.add(saveRegion.getStamp());
        }
        // when
        stampService.publishStamp(user.getId(), new PublishStampDtoReq("서울시", "강남구"));
        stampService.publishStamp(user.getId(), new PublishStampDtoReq("서울시", "중구"));
        List<Stamp> allWithUsers = stampRepository.findAllWithUsers(user.getId());
        //then
        allWithUsers.stream().forEach((allWithUser) -> {
            System.out.println(allWithUser.getLocation().getDistrict());
        });

//        assertEquals(allWithUsers.get(0).getOwners().contains(), true);
//        assertEquals(allWithUsers.get(1).getOwners().contains(stamps.get(1)), true);
    }

    private Region create_region_stamp(String supDistrict, String district, String region_description
            , Long point) {
        Region region = Region.builder()
                .supDistrict(supDistrict)
                .district(district)
                .description(region_description)
                .build();
        Stamp stamp = new Stamp(point, "");
        stamp.setRegion(region);
        region.setStamp(stamp);
        return region;
    }
}