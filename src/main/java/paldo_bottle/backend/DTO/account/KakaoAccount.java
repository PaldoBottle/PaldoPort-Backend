package paldo_bottle.backend.DTO.account;

import lombok.Data;

@Data
public class KakaoAccount {
    private Boolean profile_nickname_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private Profile profile;

    public KakaoAccount() {}

}
