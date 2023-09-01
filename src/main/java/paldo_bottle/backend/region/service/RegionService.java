package paldo_bottle.backend.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paldo_bottle.backend.DAO.Region;
import paldo_bottle.backend.DTO.GetLandmarkListRes;
import paldo_bottle.backend.region.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {
    private final RegionRepository regionRepository;

    List<GetLandmarkListRes> getLandmarkList() {
        List<Region> regions = regionRepository.findAll();
        
    }
}
