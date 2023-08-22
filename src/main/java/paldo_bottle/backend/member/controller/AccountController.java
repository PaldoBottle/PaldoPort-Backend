package paldo_bottle.backend.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import paldo_bottle.backend.DTO.KakaoProfile;
import paldo_bottle.backend.DTO.LoginDTO;
import paldo_bottle.backend.member.service.AccountService;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/signin")
    @ResponseBody
    public ResponseEntity<LoginDTO> SignIn(@RequestHeader(value = "Authorization_code") String code) {
        // 사용자 정보 가져오기
        KakaoProfile profile = accountService.getProfile(code);

        String user_Id = profile.getId();
        String user_Name = profile.getProperties().getNickname();
        String user_PFP = profile.getProperties().getProfile_image();

        //서버 자체 토큰 생성
        String JWT_Token = jwtService.makeJwtToken(user_Id);

        //로그인 응답 객체에 생성된 토큰 담기
        LoginDTO loginRes= new LoginDTO();
        loginRes.setAccessToken(JWT_Token);

        //가입된 사용자가 아닐 때
        if (!memberService.getMember(user_Id).isPresent()) {

            //새로운 Member 객체 생성해서 반환
            Member newb = new Member(user_Id, user_Name, user_PFP, JWT_Token);
            loginRes.setNewb(newb);
            return new ResponseEntity<>(loginRes, HttpStatus.CREATED);
        }

        //가입된 사용자일 경우 JWT만 담아서 반환
        return new ResponseEntity<>(loginRes, HttpStatus.OK);
    }

}
