package paldo_bottle.backend.stamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.stamp.service.StampService;

@Controller
@RequiredArgsConstructor
public class StampUserController {
    private final StampService stampService;

    @GetMapping("/stamp/user/new")
    public ResponseEntity publishStamp(String userId, PublishStampDtoReq dtoReq) {
        try {
            PublishStampDtoRes publishStampDtoRes = this.stampService.publishStamp(userId, dtoReq);
            return new ResponseEntity<>(publishStampDtoRes, HttpStatus.OK);
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }
    }
}
