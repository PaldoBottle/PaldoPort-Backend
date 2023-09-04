package paldo_bottle.backend.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paldo_bottle.backend.DAO.User;
import paldo_bottle.backend.DTO.account.LoginParam;
import paldo_bottle.backend.DTO.account.UserInfo;
import paldo_bottle.backend.DTO.member.MemberInfo;
import paldo_bottle.backend.domain.member.service.AccountService;
import paldo_bottle.backend.domain.member.service.MemberService;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.service.JWTService;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final JWTService jwtService;
    private final MemberService memberService;

    @GetMapping("/signin/kakao")
    @ResponseBody
    public ResponseEntity SignIn(@RequestHeader(value = "Authorization_code") String code) {
        // 사용자 정보 가져오기
        UserInfo user_info;
        MemberInfo user;

        user_info = accountService.getProfile(code);

        String user_Id = user_info.getUser_Id();
        String user_profile_image = user_info.getUser_profile_image();

        //서버 자체 토큰 생성
        String JWT_Token = jwtService.makeJwtToken(user_Id);

        //로그인 응답 객체에 생성된 토큰 담기
        LoginParam loginParam= new LoginParam();
        loginParam.setAccessToken(JWT_Token);

        try {
            user = memberService.getMember(user_Id);
            //가입된 사용자가 아닐 때
            if (user == null) {
                //새로운 Member 객체 생성해서 반환
                loginParam.setMemberInfo(memberService.joinMember(user_Id, user_profile_image));
                return new ResponseEntity<>(loginParam, HttpStatus.CREATED);
            }
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }

        //가입된 사용자일 경우
        loginParam.setMemberInfo(user);
        return new ResponseEntity<>(loginParam, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<String> Signup(@RequestBody User user) {
        memberService.joinMember(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}


