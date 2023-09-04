package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GetStampListResItem {
    private String  imageUrl;
    private String  supDistrict;
    private String  district;
    private Boolean have;
}
