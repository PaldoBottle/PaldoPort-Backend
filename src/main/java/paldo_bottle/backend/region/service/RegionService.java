package paldo_bottle.backend.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DTO.GetLandmarkListRes;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.exception.BaseResponseStatus;
import paldo_bottle.backend.region.repository.RegionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {
    private final RegionRepository regionRepository;

    List<GetLandmarkListRes> getLandmarkList() throws BaseException{
        List<Region> regions = regionRepository.findAll();
        Optional<List<GetLandmarkListRes>> result = regions.stream()
                .map((region) ->
                        region.getLandmarkList().stream()
                                .map((landmark) -> new GetLandmarkListRes(
                                        landmark.getSupDistrict(),
                                        landmark.getDistrict(),
                                        landmark.getName(),
                                        landmark.getLongitude(),
                                        landmark.getLatitude()
                                )).collect(Collectors.toList())
                ).reduce((prev, cur) -> {
                    prev.addAll(cur);
                    return prev;
                });
        if (result.isEmpty()) throw new BaseException(BaseResponseStatus.NOT_EXIST_LANDMARK);
        return result.get();
    }
};
