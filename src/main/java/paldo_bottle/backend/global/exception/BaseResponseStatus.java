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
    NOT_EXIST_ACHIEVED_CHALLENGE(HttpStatus.NOT_FOUND, "달성한 도전과제가 없습니다."),
    NOT_EXIST_CHALLENGE(HttpStatus.NOT_FOUND, "현재 등록된 도전과제가 없습니다."),

    SUCCESS(HttpStatus.OK, "요청 성공");

    private final HttpStatus code;
    private final String message;
}
