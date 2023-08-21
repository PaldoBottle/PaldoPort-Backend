package paldo_bottle.backend.stamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import paldo_bottle.backend.DAO.OwnStamp;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.responseType.BaseResponse;
import paldo_bottle.backend.stamp.service.StampService;

@Controller
@RequiredArgsConstructor
public class StampUserController {
    private final StampService stampService;

    @GetMapping("/stamp/user/new")
    public BaseResponse<PublishStampDtoRes> publishStamp(String userId, PublishStampDtoReq dtoReq) {
        try {
            PublishStampDtoRes publishStampDtoRes = this.stampService.publishStamp(userId, dtoReq);
            return new BaseResponse<PublishStampDtoRes>(publishStampDtoRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
