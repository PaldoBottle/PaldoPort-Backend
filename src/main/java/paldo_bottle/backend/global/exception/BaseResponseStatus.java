package paldo_bottle.backend.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BaseResponseStatus {

    NOT_EXIST_USER(Code.NOT_FOUND, "가입하지 않은 사용자입니다."),
    NOT_EXIST_STAMP(Code.NOT_FOUND, "해당 지역에는 스탬프가 등록되지 않았습니다."),

    SUCCESS(Code.OK, "요청 성공");

    private final Code code;
    private final String message;
}
