package paldo_bottle.backend.stamp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.OwnStamp;
import paldo_bottle.backend.DTO.PublishStampDto;
import paldo_bottle.backend.global.exception.BaseException;

@SpringBootTest
class StampServiceTest {
    private final StampService stampService;

    @Autowired
    StampServiceTest(StampService stampService) {
        this.stampService = stampService;
    }

    @Test
    @Transactional
    public void 사용자_도장_등록() throws BaseException {
        try {
            //given
            PublishStampDto dto = new PublishStampDto("서울시", "성북구");
            // when
            OwnStamp ownStamp = this.stampService.publishStamp("test", dto);

            //then
            Assertions.assertEquals("test", ownStamp.getUser().getId());
            Assertions.assertEquals("서울시", ownStamp.getStamp().getRegion().getLocation().getSupDistrict());
            Assertions.assertEquals("성북구", ownStamp.getStamp().getRegion().getLocation().getDistrict());
            Assertions.assertEquals(10, ownStamp.getUser().getPoint());
            Assertions.assertEquals(1, ownStamp.getStamp().getPublished());
        }catch (BaseException e) {
            Assertions.fail(e.getStatus().getMessage());
            throw e;
        }
    }
    
    @Test()
    @Transactional
    public void 사용자_도장_등록_유저없음() throws BaseException {
        //given
        PublishStampDto dto = new PublishStampDto("서울", "성북구");


        Assertions.assertThrows(BaseException.class, () -> {
            // when
            OwnStamp ownStamp = this.stampService.publishStamp("testtest", dto);
        });
        //then
        Assertions.fail("없는 유저 에러여야 합니다.");
    }
}