package paldo_bottle.backend.DTO.account;

import lombok.Data;
import paldo_bottle.backend.DAO.User;

@Data
public class LoginDTO {
    private String accessToken;
    private User user;

    public LoginDTO() {}
}
