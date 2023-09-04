package paldo_bottle.backend.DTO.account;

import lombok.Data;
import paldo_bottle.backend.DAO.User;

@Data
public class LoginParam {
    private String accessToken;
    private User user;

    public LoginParam() {}
}
