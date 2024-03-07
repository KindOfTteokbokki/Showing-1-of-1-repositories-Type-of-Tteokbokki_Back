package halfandhalf.domain.LG0010.controller;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.ValidationException;
import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.LG0010.service.LG0030Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class LG0030Controller {
    private final LG0030Service lg0030Service;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;

    public LG0030Controller(LG0030Service lg0030Service, AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider) {
        this.lg0030Service = lg0030Service;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    //    @MessageMapping("/checkNickname")
//    public void sendMessage(LG0030Dto lg0030Dto, SimpMessageHeaderAccessor accessor) {
//        lg0030Dto.setCheckNickname(lg0030Service.checkIfEnabledNickName(lg0030Dto.getNickname()));
//
//        simpMessagingTemplate.convertAndSend("/sub/checkNickname" + lg0030Dto.getChannelId(), lg0030Dto);
//    }

    /*
     * 닉네임 사용 여부 체크 - true 사용중 false 미사용
     */
    @GetMapping("/checkNickname")
    public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname){
        try {
            return ResponseEntity.ok(lg0030Service.checkIfEnabledNickName(nickname));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     * 닉네임 등록
     */
    @LoginCheckEssential
    @PostMapping("/regiNickname")
    public ResponseEntity<?> regiNickname(@RequestBody LG0020Dto lg0020Dto, HttpServletRequest request){
        try {
            lg0020Dto.setId(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            lg0030Service.registNickname(lg0020Dto);
            return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
        }
        catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseMessage.valueOfCode("Validation").getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     * 닉네임 존재 여부 확인
     */
    @LoginCheckEssential
    @GetMapping("/useCheckNickName")
    public ResponseEntity<?> useCheckNickName(HttpServletRequest request){
        try {
            return ResponseEntity.ok(lg0030Service.userCheckNickName(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
