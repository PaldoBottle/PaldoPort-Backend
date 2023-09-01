package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetLandmarkListRes {
    private String supDistrict;
    private String district;
    private String name;
    private double longitude;
    private double latitude;
}
