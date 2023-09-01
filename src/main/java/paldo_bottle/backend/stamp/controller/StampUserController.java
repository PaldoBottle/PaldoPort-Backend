package paldo_bottle.backend.stamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import paldo_bottle.backend.DTO.*;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.stamp.service.StampService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StampUserController {
    private final StampService stampService;

    @PostMapping("/stamp/user/new")
    public ResponseEntity publishStamp(String userId, PublishStampDtoReq dtoReq) {
        try {
            PublishStampDtoRes publishStampDtoRes = this.stampService.publishStamp(userId, dtoReq);
            return new ResponseEntity<>(publishStampDtoRes, HttpStatus.OK);
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }
    }

    @GetMapping("/stamp/user/list")
    public ResponseEntity getStampList(String userId) {
        try{
            List<GetStampListResItem> stampList = this.stampService.getStampList(userId, new GetStampListReq());
            return new ResponseEntity(stampList, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

    @GetMapping("/stamp/{supDistrict}/{district}/detail")
    public ResponseEntity getStampDetail(
            @PathVariable("supDistrict") String supDistrict,
            @PathVariable("district") String district,
            String userId
    ) {
        try {
            GetStampDetailRes stampDetail = this.stampService.getStampDetail(userId, supDistrict, district);
            return new ResponseEntity(stampDetail, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }
}
