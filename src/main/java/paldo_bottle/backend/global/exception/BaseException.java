package paldo_bottle.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends Exception{
    private final HttpStatus code;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.code = status.getCode();
    }
}
