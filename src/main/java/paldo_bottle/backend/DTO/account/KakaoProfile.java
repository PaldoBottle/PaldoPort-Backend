package paldo_bottle.backend.DTO.account;

import lombok.Data;

import java.util.Properties;

@Data
public class KakaoProfile {
    private String id;
    private String connected_at;
//    private Properties properties;
    private KakaoAccount kakao_account;

    public KakaoProfile(){}
}
