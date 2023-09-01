package paldo_bottle.backend.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BaseResponseStatus {

    NOT_EXIST_USER(HttpStatus.NOT_FOUND, "가입하지 않은 사용자입니다."),
    NOT_EXIST_STAMP(HttpStatus.NOT_FOUND, "해당 지역에는 스탬프가 등록되지 않았습니다."),

    NOT_EXIST_LANDMARK(HttpStatus.NOT_FOUND, "랜드마크가 등록이 되지 않았습니다."),
    SUCCESS(HttpStatus.OK, "요청 성공");

    private final HttpStatus code;
    private final String message;
}
