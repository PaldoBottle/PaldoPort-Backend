package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublishStampDtoReq {
    private String supDistrict;
    private String district;
    private String authToken;
}
