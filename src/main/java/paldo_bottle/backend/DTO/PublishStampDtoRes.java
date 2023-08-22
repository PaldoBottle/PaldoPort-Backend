package paldo_bottle.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class PublishStampDtoRes {
    private String supDistrict;
    private String district;
    private LocalDateTime publishDate;
    private Long publishNumber;
}
