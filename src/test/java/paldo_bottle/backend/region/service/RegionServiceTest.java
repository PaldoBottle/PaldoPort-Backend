package paldo_bottle.backend.region.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Landmark;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DTO.GetLandmarkListRes;
import paldo_bottle.backend.DTO.GetRegionDetailReq;
import paldo_bottle.backend.DTO.GetRegionDetailRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.region.repository.RegionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegionServiceTest {
    private final RegionService regionService;
    private final RegionRepository regionRepository;

    @Autowired
    RegionServiceTest(
            RegionRepository regionRepository,
            RegionService regionService
    ){
        this.regionService = regionService;
        this.regionRepository = regionRepository;
    }


    @Test
    @Transactional
    public void 랜드마크_리스트_가져오기() throws BaseException {
        //given
        Region region = new Region("서울시", "중구", "동국대학교가 있습니다");
        Landmark landmark1 = new Landmark("동국대학교", "명문대학교입니다.", 36.5, 36.5);
        Landmark landmark2 = new Landmark("산타돈부리", "맛집입니다", 36.5, 36.5);
        region.addLandmark(landmark1);
        region.addLandmark(landmark2);
        regionRepository.save(region);
        // when
        List<GetLandmarkListRes> landmarkList = this.regionService.getLandmarkList();
        //then
        Assertions.assertEquals(landmarkList.size(), 2);
    }

    @Test
    public void 세부지역정보_가져오기() throws BaseException {
        //given
        Region region = new Region("서울시", "중구", "동국대학교가 있습니다");
        Landmark landmark1 = new Landmark("동국대학교", "명문대학교입니다.", 36.5, 36.5);
        Landmark landmark2 = new Landmark("산타돈부리", "맛집입니다", 36.5, 36.5);
        region.addLandmark(landmark1);
        region.addLandmark(landmark2);
        regionRepository.save(region);
        // when
        GetRegionDetailRes regionDetail = this.regionService.getRegionDetail(new GetRegionDetailReq("서울시", "중구"));
        //then
        Assertions.assertEquals(regionDetail.getDescription(), "동국대학교가 있습니다");
    }
}