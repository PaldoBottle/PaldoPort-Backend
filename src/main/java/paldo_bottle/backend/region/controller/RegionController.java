package paldo_bottle.backend.region.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import paldo_bottle.backend.DTO.GetLandmarkListRes;
import paldo_bottle.backend.DTO.GetRegionDetailReq;
import paldo_bottle.backend.DTO.GetRegionDetailRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.region.service.RegionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Tag(name = "Region", description = "지역 정보 API Document")
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/region/landmark/list")
    @Operation(summary = "랜드마크 정보들", description = "모든 랜드마크 정보들을 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(
                        responseCode = "200",
                        description = "조회 성공",
                        content = @Content(
                                array = @ArraySchema(schema = @Schema(implementation = GetLandmarkListRes.class))
                        )
            ),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다." , content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity getLandmarkList() {
        try {
            List<GetLandmarkListRes> landmarkList = regionService.getLandmarkList();
            return new ResponseEntity<>(landmarkList, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

    @GetMapping("region/{supDistrict}/{district}/detail")
    @Operation(summary = "랜드마크 세부정보", description = "랜드마크 세부정보를 불러옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetRegionDetailRes.class))),
            @ApiResponse(responseCode = "404", description = "리소스가 없습니다." , content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity getRegionDetail(
            @PathVariable("supDistrict") String supDistrict,
            @PathVariable("district") String district)
    {
        try {
            GetRegionDetailRes regionDetail = regionService.getRegionDetail(new GetRegionDetailReq(supDistrict, district));
            return new ResponseEntity(regionDetail, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

}
