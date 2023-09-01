package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetRegionDetailReq {
    private String supDistrict;
    private String district;
}
