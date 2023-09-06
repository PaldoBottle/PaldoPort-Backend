package paldo_bottle.backend.domain.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import paldo_bottle.backend.DTO.account.*;
import java.io.IOException;

@Slf4j
@Service
public class AccountService {


    // 카카오 인증을 위한 키값 가져오기
    @Value("${kakao.REST_API_KEY}")
    private String REST_API_KEY;
    @Value("${kakao.REDIRECT_URI}")
    private String REDIRECT_URI;
    @Value("${kakao.CLIENT_SECRET}")
    private String CLIENT_SECRET;

    // 카카오 API로부터 엑세스 토큰 받아오기
    private String getToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // Header setting
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Parameter setting
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", REST_API_KEY);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);
        params.add("client_secret", CLIENT_SECRET);

        HttpEntity<MultiValueMap<String, String>> tokenParameter = new HttpEntity<>(params, header);
        ResponseEntity<TokenResponse> res = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                tokenParameter,
                TokenResponse.class);

        // 응답 결과의 AccessToken 반환
        return res.getBody().getAccess_token();
    }

    //인가코드의 대상 정보 반환
    public UserInfo getProfile(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        KakaoProfile profile = new KakaoProfile();
        ObjectMapper mapper = new ObjectMapper();
        UserInfo user_info = new UserInfo();


        // 카카오 API로부터 엑세스 토큰 받아오기
        String Access_token = code;

        header.add("Authorization", "Bearer " + Access_token);
        header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> profilereq = new HttpEntity<>(header);

        // 카카오 API에서 사용자 정보 받아오기
        ResponseEntity<String> res = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                profilereq,
                String.class);


        try {
            // 사용자 정보를 KakaoProfile Entity로 매핑
            profile = mapper.readValue(res.getBody (), KakaoProfile.class);

            //user_profile 추출
            Profile user_profile = profile.getKakao_account().getProfile();

            //user_profile에서 필요한 정보만 추출
            String user_nickname = user_profile.getNickname();
            String user_profile_image = user_profile.getProfile_image_url();
            String user_Id = profile.getId();
            user_info = new UserInfo(user_nickname, user_profile_image, user_Id);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }

        return user_info;
    }


}
