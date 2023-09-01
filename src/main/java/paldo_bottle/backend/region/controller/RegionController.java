package paldo_bottle.backend.region.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import paldo_bottle.backend.DTO.GetLandmarkListRes;
import paldo_bottle.backend.DTO.GetRegionDetailReq;
import paldo_bottle.backend.DTO.GetRegionDetailRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.region.service.RegionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/region/landmark/list")
    public ResponseEntity getLandmarkList() {
        try {
            List<GetLandmarkListRes> landmarkList = regionService.getLandmarkList();
            return new ResponseEntity<>(landmarkList, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

    @GetMapping("region/{supDistrict}/{district}/detail")
    public ResponseEntity getRegionDetail(GetRegionDetailReq req) {
        try {
            GetRegionDetailRes regionDetail = regionService.getRegionDetail(req);
            return new ResponseEntity(regionDetail, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity(e.getMessage(), e.getCode());
        }
    }

}
