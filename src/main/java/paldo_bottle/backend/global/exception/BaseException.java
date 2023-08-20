package paldo_bottle.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception{
    private final BaseResponseStatus status;
}