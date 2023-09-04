package paldo_bottle.backend.domain.challenge.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import paldo_bottle.backend.DTO.challenge.AchievedChallengeParam;
import paldo_bottle.backend.DTO.challenge.AllChallengeParam;
import paldo_bottle.backend.domain.challenge.service.ChallengeService;
import paldo_bottle.backend.global.exception.BaseException;
import paldo_bottle.backend.global.service.JWTService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;
    private final JWTService jwtService;

    //달성한 도전과제 목록 요청
    @PostMapping("/achieve")
    @ResponseBody
    public ResponseEntity getAchievedChallengeList(@RequestBody Map<String, String> requestBody) {
        List<AchievedChallengeParam> challengeList;

        try {
            String user_Id = jwtService.doFilterInternal(requestBody.get("authToken"));
            challengeList = challengeService.getAchievedChallenge(user_Id);
        } catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        }
        return new ResponseEntity<>(challengeList, HttpStatus.OK);
    }


    //도전과제 목록 요청
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity getChallengeList(@RequestBody Map<String, String> requestBody) {
        List<AllChallengeParam> challengeList;

        try {
            String user_Id = jwtService.doFilterInternal(requestBody.get("authToken"));
            challengeList = challengeService.getAllChallenge(user_Id);
        }
        catch (BaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getCode());
        } catch (ExpiredJwtException | ServletException | IOException exception) {
            log.warn(exception.getMessage(), exception);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(challengeList, HttpStatus.OK);
    }



}
