package paldo_bottle.backend.region.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DAO.Stamp;

@SpringBootTest
@Transactional
class RegionRepositoryTest {

    private final RegionRepository regionRepository;
    @Autowired
    RegionRepositoryTest(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Test
    public void 스탬프_생성() throws Exception {
        //given
        Region region = Region.builder()
                .supDistrict("서울")
                .district("성북구")
                .description("근대")
                .build();
        Stamp stamp = new Stamp(10L);
        stamp.setRegion(region);
        region.setStamp(stamp);
        // when
        Region saveRegion = regionRepository.save(region);
        //then
        Assertions.assertEquals(saveRegion.getSupDistrict(), region.getSupDistrict());
    }
}