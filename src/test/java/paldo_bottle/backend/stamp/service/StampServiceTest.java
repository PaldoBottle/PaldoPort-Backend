package paldo_bottle.backend.stamp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.OwnStamp;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.Stamp;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DTO.GetStampListReq;
import paldo_bottle.backend.DTO.GetStampListResItem;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.region.repository.RegionRepository;
import paldo_bottle.backend.stamp.repository.StampRepository;
import paldo_bottle.backend.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StampServiceTest {
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    private final StampService stampService;

    @Autowired
    StampServiceTest(StampService stampService,
                     UserRepository userRepository,
                     RegionRepository regionRepository) {
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
        this.stampService = stampService;
    }

    @Test
    @Transactional
    public void 사용자_도장_등록() throws BaseException {
        try {
            //given
            // test data
            String userId = "test";
            String supDistrict = "서울시", district = "성북구", region_description = "근대";
            Long point = 10L;
            user_region_stamp_load(userId, supDistrict, district, region_description, point);

            PublishStampDtoReq dto = new PublishStampDtoReq(supDistrict, district);
            // when
            PublishStampDtoRes res = this.stampService.publishStamp(userId, dto);

            //then
            Assertions.assertEquals(supDistrict, res.getSupDistrict());
            Assertions.assertEquals(district, res.getDistrict());
            Assertions.assertEquals(0, res.getPublishNumber());
        }catch (BaseException e) {
            Assertions.fail(e.getMessage());
            throw e;
        }
    }



    @Test()
    @Transactional
    public void 사용자_도장_등록_유저없음() throws BaseException {
        //given

        String userId = "test";
        String supDistrict = "서울시", district = "성북구", region_description = "근대";
        Long point = 10L;
        user_region_stamp_load(userId, supDistrict, district, region_description, point);
        PublishStampDtoReq dto = new PublishStampDtoReq("서울시", "성북구");


        BaseException exception = Assertions.assertThrows(BaseException.class, () -> {
            // when
            this.stampService.publishStamp("testtest", dto);
        });
        //then
        Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_USER.getMessage(), exception.getMessage());
    }

    @Test()
    @Transactional
    public void 사용자_도장_등록_스탬프없음() throws BaseException {
        //given

        String userId = "test";
        String supDistrict = "서울시", district = "성북구", region_description = "근대";
        Long point = 10L;
        user_region_stamp_load(userId, supDistrict, district, region_description, point);
        PublishStampDtoReq dto = new PublishStampDtoReq("인천광역시", "서구");


        BaseException exception = Assertions.assertThrows(BaseException.class, () -> {
            // when
            this.stampService.publishStamp("test", dto);
        });
        //then
        Assertions.assertEquals(BaseResponseStatus.NOT_EXIST_STAMP.getMessage(), exception.getMessage());
    }

    @Test()
    @Transactional
    public void 스탬프_목록_사용자_보유유무() throws BaseException {
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
        this.stampService.publishStamp(user.getId(), new PublishStampDtoReq(supDistricts.get(0), districts.get(0)));
        this.stampService.publishStamp(user.getId(), new PublishStampDtoReq(supDistricts.get(2), districts.get(2)));
        //when
        List<GetStampListResItem> stampList = stampService.getStampList(user.getId(), new GetStampListReq());
        //then
        stampList.stream().forEach(stamp -> {
            System.out.println(stamp.getDistrict());
            System.out.println(stamp.getHave());
        });
    }


    private void user_region_stamp_load(String userId, String supDistrict, String district, String region_description
            , Long point) {
        Region region = Region.builder()
                .supDistrict(supDistrict)
                .district(district)
                .description(region_description)
                .build();
        Stamp stamp = new Stamp(point);
        stamp.setRegion(region);
        region.setStamp(stamp);
        userRepository.save(new User(userId));
        regionRepository.save(region);
    }

    private Region create_region_stamp(String supDistrict, String district, String region_description
            , Long point) {
        Region region = Region.builder()
                .supDistrict(supDistrict)
                .district(district)
                .description(region_description)
                .build();
        Stamp stamp = new Stamp(point);
        stamp.setRegion(region);
        region.setStamp(stamp);
        return region;
    }
}