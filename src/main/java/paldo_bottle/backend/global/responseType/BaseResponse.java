package paldo_bottle.backend.global.responseType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import paldo_bottle.backend.global.exception.BaseResponseStatus;

@AllArgsConstructor
public class BaseResponse<T> {
    private String message;
    private T result;

    public BaseResponse(BaseResponseStatus status) {
        this.message = status.getMessage();
    }

    public BaseResponse(T result) {
        this.result = result;
        this.message = BaseResponseStatus.SUCCESS.getMessage();
    }
}
