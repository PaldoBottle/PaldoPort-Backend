package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
public class PublishStampDtoRes {
    private String supDistrict;
    private String district;
    private LocalDateTime publishDate;
    private Long publishNumber;
}
