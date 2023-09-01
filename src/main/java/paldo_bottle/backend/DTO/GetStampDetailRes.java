package paldo_bottle.backend.DAO;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class GetStampDetailRes {
    private String name;
    private Long point;
    private Date publishDate;
    private Long publishNumber;
    private String description;
}
