package paldo_bottle.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GetStampDetailRes {
    private Long point;

    @Nullable
    private LocalDateTime publishDate;
    @Nullable
    private Long publishNumber;
}
