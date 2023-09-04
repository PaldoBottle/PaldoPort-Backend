package paldo_bottle.backend.domain.stamp.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paldo_bottle.backend.DTO.*;
import paldo_bottle.backend.domain.stamp.service.StampService;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.service.JWTService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tags(value = {
    @Tag(name = "Stamp", description = "스탬프 API Document"),
    @Tag(name = "User", description =  "유저 API Document")
})
public class StampUserController {
    private final StampService stampService;
    private final JWTService jwtService;

    @PostMapping("/stamp/user/new")
    @Operation(summary = "스탬프 등록하기", description = "유저에게 스탬프를 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = PublishStampDtoRes.class))),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity publishStamp(@RequestBody Map<String, String> requestBody, PublishStampDtoReq dtoReq) {
        try {
            String userId = jwtService.doFilterInternal(requestBody.get("authToken"));
            PublishStampDtoRes publishStampDtoRes = this.stampService.publishStamp(userId, dtoReq);
            return new ResponseEntity<>(publishStampDtoRes, HttpStatus.OK);
        } catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }
    }

    @PostMapping("/stamp/user/list")
    @Operation(summary = "유저의 스탬프 목록", description = "유저 보유 유무를 포함한 스탬프 목록을 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetStampListResItem.class))),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다.", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity getStampList(@PathVariable Map<String, String> requestBody) {
        try{
            String userId = jwtService.doFilterInternal(requestBody.get("authToken"));
            List<GetStampListResItem> stampList = this.stampService.getStampList(userId, new GetStampListReq());
            return new ResponseEntity(stampList, HttpStatus.OK);
        } catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

    @PostMapping("/stamp/{supDistrict}/{district}/detail")
    @Operation(summary = "스탬프 세부정보", description = "랜드마크 세부정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetStampDetailRes.class))),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다." , content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity getStampDetail(
            @PathVariable("supDistrict") String supDistrict,
            @PathVariable("district") String district,
            @RequestBody Map<String, String> requestBody
    ) {
        try {
            String userId = jwtService.doFilterInternal(requestBody.get("authToken"));
            GetStampDetailRes stampDetail = this.stampService.getStampDetail(userId, supDistrict, district);
            return new ResponseEntity(stampDetail, HttpStatus.OK);
        }  catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }
}
