package paldo_bottle.backend.global.responseType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private T result;
}
