package paldo_bottle.backend.DTO.account;

import lombok.Builder;
import lombok.Data;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DTO.member.MemberInfo;

@Data
public class LoginParam {
    private String accessToken;
    private MemberInfo memberInfo;

    @Builder
    public LoginParam(String accessToken, MemberInfo memberInfo) {
        this.accessToken = accessToken;
        this.memberInfo = memberInfo;
    }

    public LoginParam() {}
}
