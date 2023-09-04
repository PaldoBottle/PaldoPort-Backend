package paldo_bottle.backend.DTO.member;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberInfo {
    String id;
    Long point;
    String profileImg;

    @Builder
    public MemberInfo(String id, Long point, String profileImg) {
        this.id = id;
        this.point = point;
        this.profileImg = profileImg;
    }

    public MemberInfo() {

    }
}
