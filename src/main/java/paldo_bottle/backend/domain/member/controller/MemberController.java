package paldo_bottle.backend.domain.member.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paldo_bottle.backend.DTO.member.MemberInfo;
import paldo_bottle.backend.domain.member.service.MemberService;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.service.JWTService;

import javax.servlet.ServletException;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/memberinfo")
@RequiredArgsConstructor
public class MemberController {

    private final JWTService jwtService;
    private final MemberService memberService;

    @PostMapping
    @ResponseBody
    public ResponseEntity getMemberInfo(@PathVariable String authToken) {
        MemberInfo memberInfo;
        try {
            String user_Id = jwtService.doFilterInternal(authToken);
            memberInfo = memberService.getMember(user_Id);
        } catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }
        return new ResponseEntity<>(memberInfo, HttpStatus.OK);
    }
}