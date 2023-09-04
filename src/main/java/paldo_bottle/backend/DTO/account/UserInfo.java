package paldo_bottle.backend.DTO.account;

import lombok.Data;

@Data
public class UserInfo {
    String user_nickname;
    String user_profile_image;
    String user_Id;

    public UserInfo(String user_nickname, String user_profile_image, String user_id) {
        this.user_nickname = user_nickname;
        this.user_profile_image = user_profile_image;
        this.user_Id = user_id;
    }

    public UserInfo() {}
}
