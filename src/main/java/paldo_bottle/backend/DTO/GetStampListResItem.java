package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStampListResItem {
    private String  supDistrict;
    private String  district;
    private Boolean have;
}
