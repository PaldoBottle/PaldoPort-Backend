package paldo_bottle.backend.stamp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import paldo_bottle.backend.DTO.GetRegionDetailRes;
import paldo_bottle.backend.DTO.PublishStampDtoReq;
import paldo_bottle.backend.DTO.PublishStampDtoRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.stamp.service.StampService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Tags(value = {
    @Tag(name = "Stamp", description = "스탬프 API Document"),
    @Tag(name = "User", description =  "유저 API Document")
})
public class StampUserController {
    private final StampService stampService;

    @GetMapping("/stamp/user/new")
    @Operation(summary = "스탬프 등록하기", description = "유저에게 스탬프를 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = PublishStampDtoRes.class))),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다.", content = @Content(schema = @Schema(implementation = String.class)))
    })
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
